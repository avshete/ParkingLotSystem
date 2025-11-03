package Exception;

public class TicketNotFoundException extends ParkingLotException {
    public TicketNotFoundException(String ticketId) {
        super("No ticket found with ID: " + ticketId);
    }
}
