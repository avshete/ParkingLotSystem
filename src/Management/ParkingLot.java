package Management;

import Domain.ParkingSpot;
import Domain.ParkingTicket;
import Domain.Vehicle;
import Strategy.ParkingStrategy;
import Exception.*;

import java.util.*;

/**
 * ParkingLot is the central aggregate for all floors and tickets.
 * Implements IParkingLot for abstraction and future extensibility.
 */
public class ParkingLot implements IParkingLot {

    private final List<ParkingFloor> floors;
    private ParkingStrategy strategy; // Allows pluggable spot allocation logic
    private final Map<String, ParkingTicket> activeTickets; // ticketId -> Ticket

    /**
     * Constructs a lot with a chosen parking strategy initially.
     */
    public ParkingLot(ParkingStrategy strategy) {
        this.floors = new ArrayList<>();
        this.strategy = strategy;
        this.activeTickets = new HashMap<>();
    }

    /** Add a floor to the lot. */
    @Override
    public void addFloor(ParkingFloor floor) {
        if (floor != null) {
            floors.add(floor);
        }
    }

    /** List all floors. */
    @Override
    public List<ParkingFloor> getFloors() {
        return Collections.unmodifiableList(floors);
    }

    /** Change spot allocation strategy at runtime. */
    @Override
    public void changeStrategy(ParkingStrategy strategy) {
        if (strategy != null) {
            this.strategy = strategy;
        }
    }


    /** Store a ticket in active mapping. */
    @Override
    public void issueTicket(ParkingTicket ticket) {
        if (ticket != null) {
            activeTickets.put(ticket.getTicketId(), ticket);
        }
    }

    /** Retrieve ticket by id (null if not found). */
    @Override
    public ParkingTicket getTicket(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new TicketNotFoundException(ticketId);
        }
        return ticket;
    }

    /** Remove ticket from active tickets. */
    @Override
    public void removeTicket(String ticketId) {
        if (!activeTickets.containsKey(ticketId)) {
            throw new TicketNotFoundException(ticketId);
        }
        activeTickets.remove(ticketId);
    }

    /** Find a parking spot by its unique id (searches all floors). */
    @Override
    public ParkingSpot getSpotById(String spotId) {
        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getAllSpots()) {
                if (spot.getId().equals(spotId)) {
                    return spot;
                }
            }
        }
        throw new SpotNotFoundException(spotId);
    }

    /** Is lot totally full (all floors)? */
    @Override
    public boolean isParkingLotFull() {
        for (ParkingFloor floor : floors) {
            if (!floor.isFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Allocates the best available spot for a vehicle using the current strategy.
     * This is an "extra" helper, not strictly on interface but reasonable for app usage.
     */
    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        return strategy.findSpot(vehicle, this);
    }

    /**
     * Get current parking strategy (e.g., for panels/UIs).
     */
    public ParkingStrategy getStrategy() {
        return strategy;
    }
}
