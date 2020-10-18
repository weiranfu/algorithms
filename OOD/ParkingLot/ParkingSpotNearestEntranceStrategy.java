package OOD.ParkingLot;

import java.util.*;

public class ParkingSpotNearestEntranceStrategy implements ParkingSpotStrategy {
    private List<Terminal> terminals;
    private List<ParkingSpot> spots;
    private Map<Integer, ParkingSpot> mapIdToSpots = new HashMap<>();                   // map spotId to spot
    private Map<Integer, TreeMap<Integer, Integer>> mapIdToMinHeap = new HashMap<>();   // map terminal id to TreeMap
    private Map<Type, Set<Integer>> available = new HashMap<>();
    private Map<Type, Set<Integer>> reserved = new HashMap<>();

    @Override
    public void init() {
        terminals = ParkingLot.getParkingLot().getTerminals();
        spots = ParkingLot.getParkingLot().getSpots();
        for (Terminal t : terminals) {
            mapIdToMinHeap.put(t.getId(), new TreeMap<>());
            TreeMap<Integer, Integer> nearestSpots = mapIdToMinHeap.get(t.getId());
            int X = t.getRow(), Y = t.getColumn();
            for (ParkingSpot spot : spots) {
                int x = spot.getRow(), y = spot.getColumn();
                int dist = dist(X, Y, x, y);
                nearestSpots.put(dist, spot.getId());
            }
        }
        for (ParkingSpot spot : spots) {
            int id = spot.getId();
            mapIdToSpots.put(id, spot);
            available.putIfAbsent(spot.getType(), new HashSet<>());
            reserved.putIfAbsent(spot.getType(), new HashSet<>());
            available.get(spot.getType()).add(id);
        }
    }

    @Override
    public int getParkingSpot(Terminal t, Type type) {
        TreeMap<Integer, Integer> nearestSpots = mapIdToMinHeap.get(t.getId());
        if (nearestSpots.isEmpty()) return -1;
        Integer minDist = nearestSpots.firstKey();
        int id = nearestSpots.get(minDist);
        for (TreeMap<Integer, Integer> minHeap : mapIdToMinHeap.values()) {
            minHeap.remove(minDist);
        }
        available.get(type).remove(id);
        reserved.get(type).add(id);
        return id;
    }

    @Override
    public void releaseParkingSpot(int id) {
        ParkingSpot spot = mapIdToSpots.get(id);
        int x = spot.getRow(), y = spot.getColumn();
        for (Terminal t : terminals) {
            int X = t.getRow(), Y = t.getColumn();
            int dist = dist(x, y, X, Y);
            mapIdToMinHeap.get(t.getId()).put(dist, spot.getId());
        }
        available.get(spot.getType()).add(id);
        reserved.get(spot.getType()).remove(id);
    }

    private int dist(int x, int y, int X, int Y) {
        return Math.abs(x - X) + Math.abs(y - Y);
    }

    public Map<Type, Set<Integer>> getAvailableSpots() {
        return available;
    }

    public Map<Type, Set<Integer>> getReservedSpots() {
        return reserved;
    }
}
