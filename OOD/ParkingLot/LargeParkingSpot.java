package OOD.ParkingLot;

public class LargeParkingSpot extends ParkingSpot {

    public LargeParkingSpot(int id, int row, int column) {
        super(id, row, column);
        type = Type.Large;
    }
}
