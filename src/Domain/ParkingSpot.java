package Domain;

public class ParkingSpot {
    private final String id;
    private final SpotType spotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(String id, SpotType spotType) {
        this.id = id;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        // Basic logic (can be enhanced): mapping SpotType to allowed VehicleType
        if (this.spotType == SpotType.LARGE)
            return true; // All vehicles can fit in a large spot
        else if (this.spotType == SpotType.MEDIUM)
            return vehicle.getType() == VehicleType.CAR;
        else // SMALL
            return vehicle.getType() == VehicleType.CAR; // customize logic as needed
    }

    public void parkVehicle(Vehicle vehicle) {
        if (!isOccupied && canFitVehicle(vehicle)) {
            this.parkedVehicle = vehicle;
            this.isOccupied = true;
        }
        // else throw custom exception
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String getId() {
        return id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
