package GsonDataBase;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShipListing {

    @SerializedName("class")
    private String className;
    private String manufacturer;
    private int maxCargo;
    private String plating;
    List<PurchaseLocation> purchaseLocations;
    private int speed;
    private String type;
    private int weapons;

    public ShipListing(String className, String manufacturer, int maxCargo, String plating, List<PurchaseLocation> purchaseLocations, int speed, String type, int weapons) {
        this.className = className;
        this.manufacturer = manufacturer;
        this.maxCargo = maxCargo;
        this.plating = plating;
        this.purchaseLocations = purchaseLocations;
        this.speed = speed;
        this.type = type;
        this.weapons = weapons;
    }



    public String getClassName() {
        return className;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getMaxCargo() {
        return maxCargo;
    }

    public String getPlating() {
        return plating;
    }

    public List<PurchaseLocation> getPurchaseLocations() {
        return purchaseLocations;
    }

    public int getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }

    public int getWeapons() {
        return weapons;
    }
}
