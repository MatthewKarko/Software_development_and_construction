package GsonDataBase;

public class FlightPlan {

    String arrivesAt;
    String departure;
    String destination;
    int distance;
    int fuelConsumed;
    int fuelRemaining;
    String id;
    String shipId;
    String terminatedAt;
    int timeRemainingInSeconds;

    public String getArrivesAt() {
        return arrivesAt;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public int getFuelConsumed() {
        return fuelConsumed;
    }

    public int getFuelRemaining() {
        return fuelRemaining;
    }

    public String getId() {
        return id;
    }

    public String getShipId() {
        return shipId;
    }

    public String getTerminatedAt() {
        return terminatedAt;
    }

    public int getTimeRemainingInSeconds() {
        return timeRemainingInSeconds;
    }
}
