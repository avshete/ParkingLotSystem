package Management;

import Domain.*;
import java.util.*;

/**
 * ParkingFloor represents a single floor in the parking lot.
 * It contains and manages a map of spots by SpotType for efficient lookup.
 * Implements IParkingFloor interface.
 */
public class ParkingFloor implements IParkingFloor {

    private final String floorId;
    // SpotType (SMALL, MEDIUM, LARGE) -> Set of spots of that type
    private final Map<SpotType, Set<ParkingSpot>> spotMap;
    private boolean underMaintenance;

    /**
     * Constructor initializes spot map for every type.
     * @param floorId unique id for this floor
     */
    public ParkingFloor(String floorId) {
        this.floorId = floorId;
        this.spotMap = new HashMap<>();
        for (SpotType type : SpotType.values()) {
            this.spotMap.put(type, new HashSet<>());
        }
        this.underMaintenance = false;
    }

    /**
     * Adds a spot to this floor in the collection for its spot type.
     * @param spot the spot to add
     */
    @Override
    public void addSpot(ParkingSpot spot) {
        Set<ParkingSpot> spots = spotMap.get(spot.getSpotType());
        if (spots == null) {
            spots = new HashSet<>();
            spotMap.put(spot.getSpotType(), spots);
        }
        spots.add(spot);
    }

    /**
     * @return floorId for this ParkingFloor
     */
    @Override
    public String getFloorId() {
        return floorId;
    }

    /**
     * Checks if floor is currently under maintenance.
     */
    @Override
    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    /**
     * Allows marking a floor as under maintenance (no parking allowed).
     * @param status true if under maintenance, false otherwise
     */
    @Override
    public void setUnderMaintenance(boolean status) {
        this.underMaintenance = status;
    }

    /**
     * Finds an available (unoccupied) spot that can fit the given vehicle.
     * Iterates by SpotType priority.
     * @param vehicle the vehicle that needs a spot
     * @return suitable ParkingSpot or null if none available
     */
    @Override
    public ParkingSpot getAvailableSpot(Vehicle vehicle) {
        // Simple priority order: LARGE -> MEDIUM -> SMALL for maximal flexibility.
        for (SpotType type : SpotType.values()) {
            Set<ParkingSpot> spotsOfType = spotMap.get(type);
            if (spotsOfType == null) continue;
            for (ParkingSpot spot : spotsOfType) {
                if (!spot.isOccupied() && spot.canFitVehicle(vehicle)) {
                    return spot;
                }
            }
        }
        return null;
    }

    /**
     * Checks if all spots are occupied (full floor).
     * @return true if all spots are occupied OR under maintenance
     */
    @Override
    public boolean isFull() {
        if (underMaintenance) return true;
        for (Set<ParkingSpot> spots : spotMap.values()) {
            for (ParkingSpot spot : spots) {
                if (!spot.isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * (Advanced) Optional helper to get ALL spots on this floor (for admins, UIs, etc)
     */
    public Set<ParkingSpot> getAllSpots() {
        Set<ParkingSpot> all = new HashSet<>();
        for (Set<ParkingSpot> group : spotMap.values()) {
            all.addAll(group);
        }
        return all;
    }

    /**
     * (Advanced) Return spots by type. Useful for display/status panels.
     */
    public Map<SpotType, Set<ParkingSpot>> getSpotMap() {
        return Collections.unmodifiableMap(spotMap);
    }
}
