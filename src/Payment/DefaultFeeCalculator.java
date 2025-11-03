package Payment;

import Domain.Vehicle;

public class DefaultFeeCalculator implements FeeCalculator {
    @Override
    public double calculateFee(Vehicle vehicle, long durationInSeconds) {
        double baseRate, hourlyRate;

        switch (vehicle.getType()) {
            case CAR:
                baseRate = 30;
                hourlyRate = 20;
                break;
            case TRUCK:
                baseRate = 50;
                hourlyRate = 40;
                break;
            case BUS:
                baseRate = 60;
                hourlyRate = 50;
                break;
            default:
                baseRate = 20;
                hourlyRate = 10;
        }

        double hours = Math.ceil(durationInSeconds / 3600.0);
        return baseRate + (hours - 1) * hourlyRate;
    }
}
