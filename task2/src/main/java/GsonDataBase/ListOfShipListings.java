package GsonDataBase;

import java.util.List;

public class ListOfShipListings {
    private ShipListing[] shipListings;

    public ListOfShipListings(ShipListing[] shipListings) {
        this.shipListings = shipListings;
    }

    public ShipListing[] getShipListings() {
        return shipListings;
    }
}
