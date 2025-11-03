package Strategy;

import Domain.ParkingSpot;
import Domain.Vehicle;
import Management.ParkingFloor;
import Management.ParkingLot;
import Exception.*;

import java.util.List;

/**
 * Assigns the first available spot that can fit the vehicle,
 * searching floor by floor (lowest to highest in the floors list).
 */
public class NearestAvailableSpotStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot) {
        // Scan floors in order (could be lowest to highest)
        List<ParkingFloor> floors = parkingLot.getFloors();
        for (ParkingFloor floor : floors) {
            if (!floor.isUnderMaintenance() && !floor.isFull()) {
                ParkingSpot spot = floor.getAvailableSpot(vehicle);
                if (spot != null) {
                    return spot;
                }
            }
        }
        // No spot found
        throw new NoSpotAvailableException(vehicle.getType().name());
    }
}
