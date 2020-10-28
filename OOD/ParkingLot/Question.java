package OOD.ParkingLot;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Design a parking lot using object-oriented principles.
 *
 * Requirements:
 * 1. Big parking lot: 10k - 30k spots
 * 2. 4 entrances and 4 exits
 * 3. Ticket to a spot assigned at entrance
 * 4. Parking spot should be nearest to the entrance
 * 5. Vehicles in the parking lot should not exceed the capacity
 * 6. Different type of spot for different types of cars(handicapped, compact, large, motorcycle)
 * 7. Parking lot has a hourly parking rate limit
 * 8. Parking fee is based on the total time the car spent in the parking lot.
 * 9. Parking fee supports cash and credit card.
 * 10. Parking lot has a monitoring system which display the cars entering and leaving and available spots.
 *
 * Core classes:
 * 1) Parking Lot System
 * 2) Entry/Exit Terminal
 *    - printer for tickets
 *    - payment processor
 * 3) Parking Spot (handicapped, compact, large, motorcycle)
 * 4) Ticket
 * 5) Monitoring System
 */
public class Question {

    public static void main(String[] args) {

        ParkingSpotStrategy strategy = new ParkingSpotNearestEntranceStrategy();
        Monitor monitor = new Monitor();
        TariffCalculator tariffCalculator = new BasicTariffCalculator();

        Configuration configuration = Configuration.getConfiguration();
        configuration.setSpot(1, 1, Type.MOTORCYCLE)
                .setTerminal(1, 2, "Entrance")
                .setTerminal(1, 2, "Exit")
                .setSpot(1, 3, Type.COMPACT)
                .setSpot(2, 1, Type.HANDICAPPED)
                .setSpot(2, 2, Type.COMPACT)
                .setSpot(2, 3, Type.LARGE)
                .setTerminal(3, 1, "Entrance")
                .setTerminal(3, 1, "Exit")
                .setSpot(3, 2, Type.MOTORCYCLE)
                .setTerminal(3, 3, "Entrance")
                .setTerminal(3, 3, "Exit")
                .setSpot(4, 1, Type.LARGE)
                .setSpot(4, 2, Type.COMPACT)
                .setSpot(4, 3, Type.HANDICAPPED)
                .setPaymentMethods(PaymentMethod.CreditCard)
                .setPaymentMethods(PaymentMethod.Cash)
                .setStrategy(strategy)
                .setMonitor(monitor)
                .setTariffCalculator(tariffCalculator);

        Queue<Ticket> tickets = new LinkedList<>();
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.init();
        List<Terminal> terminals = parkingLot.getTerminals();
        for (Terminal terminal : terminals) {
            if (terminal instanceof EntranceTerminal) {
                Ticket ticket = ((EntranceTerminal) terminal).getTicket(Type.COMPACT);
                ticket.print();
                tickets.add(ticket);
            }
        }
        for (Terminal terminal : terminals) {
            if (tickets.isEmpty()) break;
            if (terminal instanceof ExitTerminal) {
                Ticket ticket = tickets.poll();
                ((ExitTerminal) terminal).accept(ticket);
                ((ExitTerminal) terminal).processPayment(PaymentMethod.CreditCard, ticket);
            }
        }
    }
}
