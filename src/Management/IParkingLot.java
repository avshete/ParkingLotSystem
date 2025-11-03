package Management;

import Domain.ParkingSpot;
import Domain.ParkingTicket;
import Strategy.ParkingStrategy;

import java.util.List;

public interface IParkingLot {
    void addFloor(ParkingFloor floor);
    List<ParkingFloor> getFloors();
    void changeStrategy(ParkingStrategy strategy);
    void issueTicket(ParkingTicket ticket);
    ParkingTicket getTicket(String ticketId);
    ParkingSpot getSpotById(String spotId);
    void removeTicket(String ticketId);
    boolean isParkingLotFull();
}
