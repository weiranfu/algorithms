package OOD.CallCenter;

public class Caller {
    private int userId;
    private String name;

    public Caller(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
