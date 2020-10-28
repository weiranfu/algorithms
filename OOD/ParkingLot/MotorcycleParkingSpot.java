package OOD.ParkingLot;

public class MotorcycleParkingSpot extends ParkingSpot {

    public MotorcycleParkingSpot(int id, int row, int column) {
        super(id, row, column);
        type = Type.MOTORCYCLE;
    }
}
