package Management;

import Domain.*;

public interface IParkingFloor {
    void addSpot(ParkingSpot spot);
    ParkingSpot getAvailableSpot(Vehicle vehicle);
    String getFloorId();
    boolean isUnderMaintenance();
    void setUnderMaintenance(boolean status);
    boolean isFull();
}
