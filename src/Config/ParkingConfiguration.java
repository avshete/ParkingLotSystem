package Config;

public class ParkingConfiguration {
    public static final double BASE_RATE = 20.0; // Adjust as needed
    public static final double HOURLY_RATE = 10.0;

    public static double calculateCost(long durationInSeconds) {
        double hours = Math.ceil(durationInSeconds / 3600.0);
        return BASE_RATE + (hours - 1) * HOURLY_RATE; // 1st hour at base, rest hourly rate
    }
}
