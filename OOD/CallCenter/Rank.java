package OOD.CallCenter;

public enum Rank {
    Respondent(0),
    Manager(1),
    Director(2);

    private final int value;

    Rank(int v) {
        value = v;
    }

    public int value() {
        return value;
    }
}
