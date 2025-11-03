package Panel;

import Domain.ParkingSpot;
import Domain.ParkingTicket;
import Domain.Vehicle;
import Management.ParkingLot;

import java.util.UUID;

/**
 * EntryPanel handles the check-in process:
 * - Allocates a spot using the lot's strategy
 * - Issues a ticket and parks the vehicle
 */
public class EntryPanel {
    private final ParkingLot parkingLot;

    public EntryPanel(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    /**
     * Parks a vehicle — allocates a spot, issues a ticket, and returns it.
     * Throws NoSpotAvailableException if no spot is found.
     *
     * @param vehicle the vehicle to park
     * @return the issued ParkingTicket
     */
    public ParkingTicket parkVehicle(Vehicle vehicle) {
        // Uses ParkingLot logic — will throw if no spot is available
        ParkingSpot spot = parkingLot.findSpotForVehicle(vehicle);

        // Park the vehicle in the spot
        spot.parkVehicle(vehicle);

        // Generate ticket details
        String ticketId = generateTicketId();
        ParkingTicket ticket = new ParkingTicket(
                ticketId, vehicle, spot.getId(), spot.getSpotType().name());

        // Register ticket in lot management
        parkingLot.issueTicket(ticket);

        return ticket;
    }

    private String generateTicketId() {
        // Simple UUID, customize as desired
        return UUID.randomUUID().toString();
    }
}
