package Strategy;

import Domain.ParkingSpot;
import Domain.Vehicle;
import Management.ParkingFloor;
import Management.ParkingLot;
import Exception.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Selects a random available spot from all possible options.
 */
public class RandomSpotStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot) {
        List<ParkingSpot> candidates = new ArrayList<>();
        for (ParkingFloor floor : parkingLot.getFloors()) {
            if (!floor.isUnderMaintenance() && !floor.isFull()) {
                for (ParkingSpot spot : floor.getAllSpots()) {
                    if (!spot.isOccupied() && spot.canFitVehicle(vehicle)) {
                        candidates.add(spot);
                    }
                }
            }
        }
        if (candidates.isEmpty()) {
            throw new NoSpotAvailableException(vehicle.getType().name());
        }
        // Shuffle for true randomness
        Collections.shuffle(candidates);
        return candidates.get(0);
    }
}
