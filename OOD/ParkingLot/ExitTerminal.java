package OOD.ParkingLot;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExitTerminal extends Terminal {
    private Set<PaymentMethod> paymentMethods;
    private Map<PaymentMethod, PaymentProcessor> processorMap = new HashMap<>();
    private TariffCalculator tariffCalculator;

    public ExitTerminal(int id, int row, int column) {
        super(id, row, column);
        paymentMethods = ParkingLot.getParkingLot().getPaymentMethods();
        tariffCalculator = ParkingLot.getParkingLot().getTariffCalculator();
        for (PaymentMethod paymentMethod : paymentMethods) {
            switch (paymentMethod) {
                case CreditCard:
                    processorMap.put(PaymentMethod.CreditCard, new CreditCardProcessor());
                    break;
                case Cash:
                    processorMap.put(PaymentMethod.Cash, new CashProcessor());
            }
        }
    }

    public void accept(Ticket ticket) {
        strategy.releaseParkingSpot(ticket.getParkingSpotId());

        logMessage("Released parking spot " + ticket.getParkingSpotId());
    }

    public boolean processPayment(PaymentMethod method, Ticket ticket) {
        Duration duration = Duration.between(ticket.getIssueTime(), LocalTime.now());
        int amount = tariffCalculator.calculateCost(duration, ticket.getParkingSpotType());

        boolean success = true;
        switch (method) {
            case CreditCard:
                success = processorMap.get(PaymentMethod.CreditCard).pay(amount);
                break;
            default:  // default is cash
                success = processorMap.get(PaymentMethod.Cash).pay(amount);
        }
        if (success) {
            logMessage("Successfully payed " + amount);
        } else {
            logMessage("Failed to pay " + amount);
        }
        return success;
    }
}
