package OOD.ParkingLot;

import java.time.Duration;

public class BasicTariffCalculator implements TariffCalculator{

    @Override
    public int calculateCost(Duration duration, Type type) {
        int hours = (int)duration.toHours();
        switch (type) {
            case HANDICAPPED:
            case MOTORCYCLE:
                return hours;
            case COMPACT:
                return 2 * hours;
            case LARGE:
                return 3 * hours;
            default:
                return -1;
        }
    }
}
