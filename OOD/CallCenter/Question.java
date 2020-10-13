package OOD.CallCenter;

import java.util.ArrayList;
import java.util.List;

/*
 * Imagine you have a call center with three levels of employees: respondent, manager, and director.
 * An incoming telephone call must be first allocated to a respondent who is free.
 * If the respondent can't handle the call, he or she must escalate the call to a manager.
 * If the manager is not free or not able to handle it, then the call should be escalated to a director.
 *
 * Design the classes and data structures for this problem.
 * Implement a method dispatchCall() which assigns a call to the first available employee.
 *
 * enum Rank
 * -----------------
 * Respondent: 0
 * Manager: 1
 * Director: 2
 *
 * Abstract Employee
 * -----------------
 * # rank: Rank
 * # call: Call
 * # callCenter: CallCenter
 * -----------------
 * + isFree(): boolean
 * + getRank(): Rank
 * + receiveCall(call: Call): void
 * + completeCall(): void
 * + escalateAndReassign(): void
 * - assignNewCall(): boolean
 *
 * Respondent extends Employee
 * -----------------
 * -----------------
 *
 * Manager extends Employee
 * -----------------
 * -----------------
 *
 * Director extends Employee
 * -----------------
 * -----------------
 *
 * Caller
 * -----------------
 * - userId: int
 * - name: String
 *
 * Call
 * -----------------
 * - caller: Caller
 * - handler: Employee
 * - rank: Rank
 * -----------------
 * + increaseRank(): void
 * + getRank(): Rank
 * + connect(Employee emp): void
 * + disconnect(): void
 * + reply(msg: String): void
 *
 * CallCenter
 * -----------------
 * - numOfRespondents: int
 * - numOfManagers: int
 * - numOfDirectors: int
 * - employees: List<Employee>[]
 * - callQueues: List<Call>[]
 * - callerCallMap: Map<Caller, Call>
 * -----------------
 * + assignCall(emp: Employee): boolean
 * + dispatchCall(caller: Caller): void
 * + dispatchCall(call: Call): void
 * - findHandler(call: Call): Employee
 * + finishCall(caller: Caller): void
 */
public class Question {
    public static void main(String[] args) {
        CallCenter callCenter = new CallCenter(3, 2, 1);
        List<Caller> callers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Caller caller = new Caller(i, "Caller " + i);
            callers.add(caller);
        }
        for (int i = 0; i < 10; i++) {
            callCenter.dispatchCall(callers.get(i));
        }
        for (int i = 0; i < 6; i++) {
            callCenter.finishCall(callers.get(i));
        }
    }
}
