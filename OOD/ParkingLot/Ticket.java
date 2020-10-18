package OOD.ParkingLot;

import java.time.LocalTime;

public class Ticket {
    private static int startId = 0;
    private int id;
    private int parkingSpotId;
    private Type parkingSpotType;
    private LocalTime issueTime;

    public Ticket(int parkingSpotId, Type parkingSpotType) {
        this.id = startId++;
        this.parkingSpotId = parkingSpotId;
        this.parkingSpotType = parkingSpotType;
        this.issueTime = LocalTime.now();
    }

    public int getId() {
        return id;
    }

    public int getParkingSpotId() {
        return parkingSpotId;
    }

    public Type getParkingSpotType() {
        return parkingSpotType;
    }

    public LocalTime getIssueTime() {
        return issueTime;
    }

    public String print() {
        return "Id: " + id + ", parkingSpotId: " + parkingSpotId + ", parkingSpotType " + parkingSpotType + ", issueTime " + issueTime.toString();
    }
}
