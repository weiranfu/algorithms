package OOD.ParkingLot;

import java.time.Duration;

public class BasicTariffCalculator implements TariffCalculator{

    @Override
    public int calculateCost(Duration duration, Type type) {
        int hours = (int)duration.toHours();
        switch (type) {
            case Handicapped:
            case Motorcycle:
                return hours;
            case Compact:
                return 2 * hours;
            case Large:
                return 3 * hours;
            default:
                return -1;
        }
    }
}
