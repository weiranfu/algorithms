package OOD.ParkingLot;

import java.util.Map;
import java.util.Set;

public interface ParkingSpotStrategy {

    void init();

    int getParkingSpot(Terminal t, Type type);

    void releaseParkingSpot(int id);

    Map<Type, Set<Integer>> getAvailableSpots();

    Map<Type, Set<Integer>> getReservedSpots();
}
