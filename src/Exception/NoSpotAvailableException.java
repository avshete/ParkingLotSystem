package Exception;

public class NoSpotAvailableException extends ParkingLotException {
    public NoSpotAvailableException(String vehicleType) {
        super("No available spot for vehicle type: " + vehicleType);
    }
}
