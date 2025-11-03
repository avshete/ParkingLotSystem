package Payment;

import Domain.Vehicle;

public interface FeeCalculator {
    /**
     * Calculates the parking fee for a vehicle,
     * given its type and parking duration (in seconds).
     */
    double calculateFee(Vehicle vehicle, long durationInSeconds);
}
