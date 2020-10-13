package OOD.CallCenter;

public class Manager extends Employee {
    public Manager(CallCenter callCenter) {
        super(callCenter);
        rank = Rank.Manager;
    }
}
