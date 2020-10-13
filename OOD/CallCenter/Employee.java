package OOD.CallCenter;

/* Employee is a super class for the Director, Manager, and Respondent classes.
 */
public abstract class Employee {
    protected Rank rank;
    protected Call call;
    protected CallCenter callCenter;

    public Employee(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    public boolean isFree() {
        return call == null;
    }

    public Rank getRank() {
        return rank;
    }

    public void receiveCall(Call call) {
        this.call = call;
    }

    /* the issue is resolved, finish the call */
    public void completeCall() {
        call = null;
        /* Check if there is a call waiting in queue */
        assignNewCall();
    }

    /*
     * The issue has not been resolved. Escalate the call, and assign a new call
     * to the employee.
     */
    public void escalateAndReassign() {
        if (call != null) {
            call.increaseRank();
            callCenter.dispatchCall(call);
            call = null;
        }
        /* Check if there is a call waiting in queue */
        assignNewCall();
    }

    /* Assign a new call to an employee, if the employee is free. */
    public boolean assignNewCall() {
        if (!isFree()) return true;
        return callCenter.assignCall(this);
    }

}
