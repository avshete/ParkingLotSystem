package Panel;

import Config.ParkingConfiguration;
import Domain.ParkingSpot;
import Domain.ParkingTicket;
import Domain.Vehicle;
import Management.ParkingLot;
import Payment.FeeCalculator;
import Payment.PaymentProcessor;

import java.time.Instant;

/**
 * ExitPanel handles the check-out process:
 * - Looks up the ticket
 * - Calculates parking fee
 * - Vacates the spot
 * - Removes ticket from management
 */

public class ExitPanel {

    private final ParkingLot parkingLot;
    private final PaymentProcessor paymentProcessor;
    private final FeeCalculator feeCalculator;

    public ExitPanel(ParkingLot parkingLot, PaymentProcessor paymentProcessor, FeeCalculator feeCalculator) {
        this.parkingLot = parkingLot;
        this.paymentProcessor = paymentProcessor;
        this.feeCalculator = feeCalculator;
    }

    public void unparkVehicle(String ticketId) {
        ParkingTicket ticket = parkingLot.getTicket(ticketId); // Throws if not found
        Vehicle vehicle = ticket.getVehicle();

        long exitTime = Instant.now().toEpochMilli();
        long durationInSeconds = (exitTime - ticket.getEntryTime()) / 1000;

        double fee = feeCalculator.calculateFee(vehicle, durationInSeconds);

        PaymentProcessor processor = this.paymentProcessor;

        boolean success = processor.processPayment(fee);
        if (!success) {
            System.out.println("Payment failed. Please try again.");
            return;
        }
        ParkingSpot spot = parkingLot.getSpotById(ticket.getSpotId());
        spot.removeVehicle();

        parkingLot.removeTicket(ticketId);

        System.out.println("Vehicle [" + vehicle.getLicenseNumber() + "] has exited. Amount paid: Rs. " + String.format("%.2f", fee));
    }
}
