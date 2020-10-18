package OOD.ParkingLot;

public abstract class ParkingSpot implements Position {
    protected int id;
    protected int row, column;
    protected boolean isFree;
    protected Type type;

    public ParkingSpot(int id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
        isFree = true;
    }

    public int getId() {
        return id;
    }

    public boolean isFree() {
        return isFree;
    }

    public Type getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
