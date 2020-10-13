package OOD.CallCenter;

import java.util.*;

public class CallCenter {
    private static final int LEVELS = 3;
    private int numOfRespondents;
    private int numOfManagers;
    private int numOfDirectors;
    private List<Employee>[] employees;
    private Deque<Call>[] callQueues;
    private Map<Caller, Call> callerCallMap;

    public CallCenter(int numOfRespondents, int numOfManagers, int numOfDirectors) {
        this.numOfRespondents = numOfRespondents;
        this.numOfManagers = numOfManagers;
        this.numOfDirectors = numOfDirectors;
        employees = new List[LEVELS];
        callQueues = new ArrayDeque[LEVELS];
        callerCallMap = new HashMap<>();
        for (int i = 0; i < LEVELS; i++) {
            employees[i] = new ArrayList<>();
            if (i == Rank.Respondent.value()) {
                for (int j = 0; j < numOfRespondents; j++) {
                    employees[i].add(new Respondent(this));
                }
            } else if (i == Rank.Manager.value()) {
                for (int j = 0; j < numOfManagers; j++) {
                    employees[i].add(new Manager(this));
                }
            } else {
                for (int j = 0; j < numOfDirectors; j++) {
                    employees[i].add(new Director(this));
                }
            }
            callQueues[i] = new ArrayDeque<>();
        }
    }

    /* Receives a call from a caller, then routes the call to a handler. */
    public void dispatchCall(Caller caller) {
        Call call = new Call(caller);
        callerCallMap.put(caller, call);
        dispatchCall(call);
    }

    /* Routes the call to an available employee, or saves in a queue if no employee available. */
    public void dispatchCall(Call call) {
        Employee emp = findHandler(call);
        if (emp != null) {
            call.connect(emp);
        } else {
            /* Place the call into corresponding call queue according to its rank. */
            call.reply("Please wait for free employee to reply");
            callQueues[call.getRank().value()].addLast(call);
        }
    }
    /* Gets the first available employee who can handle this call. */
    private Employee findHandler(Call call) {
        for (int i = call.getRank().value(); i < LEVELS; i++) {
            for (Employee emp : employees[i]) {
                if (emp.isFree()) return emp;
            }
        }
        return null;
    }

    /* An employee got free. Look for a waiting call that he/she can serve. Return true
     * if we were able to assign a call, false otherwise. */
    public boolean assignCall(Employee emp) {
        /* Check the queues, starting from the highest rank this employee can serve. */
        for (int i = emp.getRank().value(); i >= 0; i--) {
            Deque<Call> q = callQueues[i];
            if (q.size() != 0) {
                Call call = q.pollFirst();
                if (call != null) {
                    call.connect(emp);
                    return true;
                }
            }
        }
        return false;
    }

    public void finishCall(Caller caller) {
        Call call = callerCallMap.get(caller);
        call.disconnect();
        callerCallMap.remove(caller);
    }
}
