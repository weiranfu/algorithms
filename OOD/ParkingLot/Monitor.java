package OOD.ParkingLot;

import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

public class Monitor {

    public void logMessage(String message) {
        System.out.println(message);
    }

    public void showAvailableSpots() {
        Map<Type, Set<Integer>> availableSpots = ParkingLot.getParkingLot().getStrategy().getAvailableSpots();
        for (Type type : availableSpots.keySet()) {
            System.out.println(type + " spots have " + availableSpots.get(type).size() + " available");
        }
    }
}
