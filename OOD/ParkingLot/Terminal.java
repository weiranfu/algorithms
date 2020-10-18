package OOD.ParkingLot;

import java.time.LocalTime;

public abstract class Terminal implements Position {
    protected int id;
    protected int row, column;
    protected ParkingSpotStrategy strategy;
    protected Monitor monitor;

    public Terminal(int id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
        strategy = ParkingLot.getParkingLot().getStrategy();
        monitor = ParkingLot.getParkingLot().getMonitor();
    }

    public int getId() {
        return id;
    }

    public LocalTime getTime() {
        return LocalTime.now();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void logMessage(String message) {
        monitor.logMessage(message);
    }
}
