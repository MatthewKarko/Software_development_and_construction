package GsonDataBase;

public class ShipDetails {
    String credits;
    Ship ship;

    public ShipDetails(String credits, Ship ship) {
        this.credits = credits;
        this.ship = ship;
    }

    public String getCredits() {
        return credits;
    }

    public Ship getShip() {
        return ship;
    }
}
