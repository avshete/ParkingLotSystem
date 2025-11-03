//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Display.EntryDisplayPanel;
import Display.ExitDisplayPanel;
import Display.FloorDisplayPanel;
import Domain.*;
import Management.ParkingFloor;
import Management.ParkingLot;
import Panel.EntryPanel;
import Panel.ExitPanel;
import Payment.DefaultFeeCalculator;
import Payment.FeeCalculator;
import Payment.PaymentProcessor;
import Payment.PaymentProcessorFactory;
import Strategy.NearestAvailableSpotStrategy;
import Strategy.ParkingStrategy;

import java.util.*;

public class Main {

    // Maps ticketId to Ticket (for demo purposes)
    private static final Map<String, ParkingTicket> issuedTickets = new HashMap<>();

    public static void main(String[] args) {
        // --- Setup System Objects
        ParkingStrategy strategy = new NearestAvailableSpotStrategy();
        ParkingLot lot = new ParkingLot(strategy);

        // Add one floor with various spots for demo
        ParkingFloor floor1 = new ParkingFloor("F1");
        floor1.addSpot(new ParkingSpot("F1-S1", SpotType.SMALL));
        floor1.addSpot(new ParkingSpot("F1-M1", SpotType.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", SpotType.LARGE));
        lot.addFloor(floor1);

        // (Optional) Add more floors/spots here...

        // Payment and Fee
        FeeCalculator feeCalculator = new DefaultFeeCalculator();
        Scanner scanner = new Scanner(System.in);

        // Allow user to pick payment method at runtime
        System.out.println("Select payment method (cash/card): ");
        String paymentType = scanner.nextLine();
        PaymentProcessor paymentProcessor =
                PaymentProcessorFactory.createProcessor(paymentType);

        // Panels & displays
        EntryPanel entryPanel = new EntryPanel(lot);
        ExitPanel exitPanel = new ExitPanel(lot, paymentProcessor, feeCalculator);
        EntryDisplayPanel entryDisplay = new EntryDisplayPanel();
        ExitDisplayPanel exitDisplay = new ExitDisplayPanel();
        FloorDisplayPanel floorDisplay = new FloorDisplayPanel("F1");

        // Main Menu Loop
        while (true) {
            System.out.println("\n==== Smart Parking Lot ====");
            System.out.println("1. Park Vehicle (Entry)");
            System.out.println("2. Unpark Vehicle (Exit)");
            System.out.println("3. Show Floor Availability");
            System.out.println("4. List Active Tickets");
            System.out.println("5. Quit");
            System.out.print("Your choice: ");
            int choice = safeIntInput(scanner);

            switch (choice) {
                case 1:
                    // --- Entry flow ---
                    System.out.print("Enter vehicle license number: ");
                    String license = scanner.nextLine();
                    VehicleType vtype = askVehicleType(scanner);
                    Vehicle vehicle = new Vehicle(license, vtype);

                    try {
                        // Park and assign spot
                        ParkingTicket ticket = entryPanel.parkVehicle(vehicle);
                        ParkingSpot spot =
                                lot.getSpotById(ticket.getSpotId());
                        entryDisplay.displaySpotAssigned(vehicle, spot);
                        entryDisplay.displayTicketIssued(ticket);
                        issuedTickets.put(ticket.getTicketId(), ticket);
                    } catch (Exception e) {
                        System.out.println("Parking error: " + e.getMessage());
                    }
                    break;
                case 2:
                    // --- Exit flow ---
                    System.out.print("Enter your ticket ID: ");
                    String ticketId = scanner.nextLine();
                    try {
                        ParkingTicket ticket =
                                lot.getTicket(ticketId);
                        Vehicle v = ticket.getVehicle();
                        exitPanel.unparkVehicle(ticketId);
                        // For advanced version: show payment result on display panel
                        // exitDisplay.displayCost(v, ...fee...);
                        issuedTickets.remove(ticketId);
                    } catch (Exception e) {
                        System.out.println("Exit error: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Show current floor spot availability via display panel
                    // Assumes demo has only F1 floor for now
                    floorDisplay.update(floor1.getSpotMap(), floor1.isUnderMaintenance());
                    floorDisplay.display();
                    break;
                case 4:
                    // Print active tickets/ticket IDs
                    System.out.println("Active tickets:");
                    for (String tid : issuedTickets.keySet()) {
                        System.out.println("- " + tid);
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Helper function to parse vehicle type
    private static VehicleType askVehicleType(Scanner scanner) {
        while (true) {
            System.out.print("Enter vehicle type (CAR/BUS/TRUCK): ");
            String type = scanner.nextLine().toUpperCase();
            try {
                return VehicleType.valueOf(type);
            } catch (Exception ignored) {
                System.out.println("Invalid type, try again.");
            }
        }
    }

    // Safe integer input
    private static int safeIntInput(Scanner scanner) {
        while (true) {
            try {
                String val = scanner.nextLine();
                return Integer.parseInt(val);
            } catch (Exception ignored) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}