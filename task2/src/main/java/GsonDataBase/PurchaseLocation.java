package GsonDataBase;

public class PurchaseLocation {
    private String location;
    private int price;

    public PurchaseLocation(String location, int price) {
        this.location = location;
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public int getPrice() {
        return price;
    }
}
