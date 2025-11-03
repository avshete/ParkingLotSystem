package Display;

import Domain.ParkingSpot;
import Domain.ParkingTicket;
import Domain.Vehicle;

/**
 * Displays assignment and ticket info at entry.
 */
public class EntryDisplayPanel extends DisplayPanel {

    private Vehicle vehicle;
    private ParkingSpot spot;
    private ParkingTicket ticket;

    // Set and display assigned spot
    public void displaySpotAssigned(Vehicle vehicle, ParkingSpot spot) {
        this.vehicle = vehicle;
        this.spot = spot;
        System.out.println("Welcome! " + vehicle.getType() +
                " [" + vehicle.getLicenseNumber() + "] assigned to spot " +
                spot.getId() + " (" + spot.getSpotType().name() + ")");
    }

    // Set and display ticket info
    public void displayTicketIssued(ParkingTicket ticket) {
        this.ticket = ticket;
        System.out.println("Your ticket ID: " + ticket.getTicketId());
        System.out.println("Entry time: " + ticket.getEntryTime());
    }

    @Override
    public void display() {
        // Optionally consolidate info after assignment/ticket
        System.out.println("ENTRY PANEL SUMMARY:");
        if (vehicle != null && spot != null) {
            System.out.println("- Vehicle: " + vehicle.getLicenseNumber() + " (" + vehicle.getType() + ")");
            System.out.println("- Spot: " + spot.getId() + " [" + spot.getSpotType() + "]");
        }
        if (ticket != null) {
            System.out.println("- Ticket ID: " + ticket.getTicketId());
        }
    }
}
