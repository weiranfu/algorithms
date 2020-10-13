package OOD.CallCenter;

public class Director extends Employee {
    public Director(CallCenter callCenter) {
        super(callCenter);
        rank = Rank.Director;
    }
}
