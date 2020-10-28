package OOD.ParkingLot;

import java.util.*;

public class ParkingLot {
    private static final ParkingLot parkingLot = new ParkingLot(Configuration.getConfiguration());
    private List<ParkingSpot> spots = new ArrayList<>();
    private List<Terminal> terminals = new ArrayList<>();
    private ParkingSpotStrategy strategy;
    private Monitor monitor;
    private Set<PaymentMethod> paymentMethods;
    private TariffCalculator tariffCalculator;

    private ParkingLot(Configuration configuration) {
        this.strategy = configuration.getStrategy();
        this.monitor = configuration.getMonitor();
        this.paymentMethods = configuration.getPaymentMethods();
        this.tariffCalculator = configuration.getTariffCalculator();
    }

    public void init() {
        Map<Type, List<int[]>> spotsMap = Configuration.getConfiguration().getSpots();
        Map<String, List<int[]>> terminalsMap = Configuration.getConfiguration().getTerminals();
        for (Type type : spotsMap.keySet()) {
            for (int[] pos : spotsMap.get(type)) {
                switch (type) {
                    case HANDICAPPED:
                        spots.add(new HandicappedParkingSpot(pos[0], pos[1], pos[2]));
                        break;
                    case COMPACT:
                        spots.add(new CompactParkingSpot(pos[0], pos[1], pos[2]));
                        break;
                    case LARGE:
                        spots.add(new LargeParkingSpot(pos[0], pos[1], pos[2]));
                        break;
                    case MOTORCYCLE:
                        spots.add(new MotorcycleParkingSpot(pos[0], pos[1], pos[2]));
                        break;
                }
            }
        }
        for (String s : terminalsMap.keySet()) {
            for (int[] pos : terminalsMap.get(s)) {
                if (s.equals("Entrance")) {
                    terminals.add(new EntranceTerminal(pos[0], pos[1], pos[2]));
                } else if (s.equals("Exit")) {
                    terminals.add(new ExitTerminal(pos[0], pos[1], pos[2]));
                }
            }
        }
        // initialize strategy
        strategy.init();
    }

    public static ParkingLot getParkingLot() {
        return parkingLot;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public ParkingSpotStrategy getStrategy() {
        return strategy;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Set<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public TariffCalculator getTariffCalculator() {
        return tariffCalculator;
    }
}
