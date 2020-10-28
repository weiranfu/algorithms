package OOD.ParkingLot;

public class CompactParkingSpot extends ParkingSpot {

    public CompactParkingSpot(int id, int row, int column) {
        super(id, row, column);
        type = Type.COMPACT;
    }
}
