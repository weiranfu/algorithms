package OOD.ParkingLot;

public class EntranceTerminal extends Terminal {

    public EntranceTerminal(int id, int row, int column) {
        super(id, row, column);
    }

    public Ticket getTicket(Type type) {
        int id = strategy.getParkingSpot(this, type);
        if (id == -1) {
            logMessage("Failed to assign a ticket");
            return null;
        } else {
            logMessage("Successfully assign a ticket to spot " + id);
            return new Ticket(id, type);
        }
    }
}
