package Display;

import Domain.ParkingSpot;
import Domain.SpotType;

import java.util.Map;
import java.util.Set;

/**
 * Displays available spots for each type on a specific parking floor.
 */
public class FloorDisplayPanel extends DisplayPanel {
    private final String floorId;
    private Map<SpotType, Set<ParkingSpot>> spotMap;
    private boolean underMaintenance;

    public FloorDisplayPanel(String floorId) {
        this.floorId = floorId;
    }

    // Data injection for each display cycle
    public void update(Map<SpotType, Set<ParkingSpot>> spotMap, boolean underMaintenance) {
        this.spotMap = spotMap;
        this.underMaintenance = underMaintenance;
    }

    @Override
    public void display() {
        System.out.println("=== Floor: " + floorId + " ===");
        if (underMaintenance) {
            System.out.println("Floor is currently under maintenance.");
            return;
        }
        for (SpotType type : SpotType.values()) {
            long available = spotMap.get(type).stream().filter(spot -> !spot.isOccupied()).count();
            System.out.println(type.name() + " spots available: " + available);
        }
        System.out.println("=============================");
    }
}
