package Strategy;

import Domain.ParkingSpot;
import Domain.Vehicle;
import Management.ParkingLot;

public interface ParkingStrategy {
    /**
     * Finds and returns an available ParkingSpot for the given vehicle,
     * or null if no spot can be found.
     */
    ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot);
}