package OOD.ParkingLot;

import java.util.*;

public class Configuration {
    private static final Configuration configuration = new Configuration();
    private Map<Type, List<int[]>> spots = new HashMap<>();
    private Map<String, List<int[]>> terminals = new HashMap<>();
    private static int spotId = 0;
    private static int terminalId = 0;
    private ParkingSpotStrategy strategy;
    private Monitor monitor;
    private Set<PaymentMethod> paymentMethods = new HashSet<>();
    private TariffCalculator tariffCalculator;

    private Configuration(){
        paymentMethods.add(PaymentMethod.Cash);
        for (Type type : Type.values()) {
            spots.put(type, new ArrayList<>());
        }
        terminals.put("Entrance", new ArrayList<>());
        terminals.put("Exit", new ArrayList<>());
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public Configuration setSpot(int row, int column, Type type) {
        switch (type) {
            case Handicapped:
                spots.get(Type.Handicapped).add(new int[]{spotId++, row, column});
                break;
            case Compact:
                spots.get(Type.Compact).add(new int[]{spotId++, row, column});
                break;
            case Large:
                spots.get(Type.Large).add(new int[]{spotId++, row, column});
                break;
            case Motorcycle:
                spots.get(Type.Motorcycle).add(new int[]{spotId++, row, column});
                break;
        }
        return this;
    }

    public Configuration setTerminal(int row, int column, String type) {
        if (type.equals("Entrance")) {
            terminals.get("Entrance").add(new int[]{terminalId++, row, column});
        } else if (type.equals("Exit")) {
            terminals.get("Exit").add(new int[]{terminalId++, row, column});
        }
        return this;
    }

    public Configuration setStrategy(ParkingSpotStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public Configuration setMonitor(Monitor monitor) {
        this.monitor = monitor;
        return this;
    }

    public Configuration setTariffCalculator(TariffCalculator tariffCalculator) {
        this.tariffCalculator = tariffCalculator;
        return this;
    }

    public Configuration setPaymentMethods(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
        return this;
    }


    public Map<Type, List<int[]>> getSpots() {
        return spots;
    }

    public Map<String, List<int[]>> getTerminals() {
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
