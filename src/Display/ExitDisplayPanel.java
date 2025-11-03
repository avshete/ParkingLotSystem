package Display;

import Domain.Vehicle;

/**
 * Displays cost and farewell info at exit.
 */
public class ExitDisplayPanel extends DisplayPanel {
    private Vehicle vehicle;
    private double cost;

    // Show cost for this vehicle on exit
    public void displayCost(Vehicle vehicle, double cost) {
        this.vehicle = vehicle;
        this.cost = cost;
        System.out.println("Thank you! " +
                "Vehicle [" + vehicle.getLicenseNumber() + "]" +
                " owes Rs. " + String.format("%.2f", cost));
    }

    @Override
    public void display() {
        if (vehicle != null) {
            System.out.println("EXIT SUMMARY for Vehicle: " + vehicle.getLicenseNumber());
            System.out.println("- Total amount owed: Rs. " + String.format("%.2f", cost));
        }
    }
}
