package OOD.ParkingLot;

public class HandicappedParkingSpot extends ParkingSpot {

    public HandicappedParkingSpot(int id, int row, int column) {
        super(id, row, column);
        type = Type.Handicapped;
    }
}
