package Exception;

public class SpotNotFoundException extends ParkingLotException {
    public SpotNotFoundException(String spotId) {
        super("No spot found with ID: " + spotId);
    }
}
