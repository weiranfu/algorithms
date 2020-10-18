package OOD.ParkingLot;

import java.time.Duration;

public interface TariffCalculator {

    int calculateCost(Duration duration, Type type);

}
