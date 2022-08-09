package GsonDataBase;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ship {
    Cargo[] cargo;
    @SerializedName("class")
    String className;
    String flightPlanId;



    String id;
    String location;
    String manufacturer;
    int maxCargo;
    int plating;
    int spaceAvailable;
    int speed;
    String type;
    int weapons;
    int x;
    int y;

    public Ship(Cargo[] cargo, String className, String id, String location, String manufacturer, int maxCargo, int plating, int spaceAvailable, int speed, String type, int weapons, int x, int y) {
        this.cargo = cargo;
        this.className = className;
        this.id = id;
        this.location = location;
        this.manufacturer = manufacturer;
        this.maxCargo = maxCargo;
        this.plating = plating;
        this.spaceAvailable = spaceAvailable;
        this.speed = speed;
        this.type = type;
        this.weapons = weapons;
        this.x = x;
        this.y = y;
    }

    public Cargo[] getCargo() {
        return cargo;
    }

    public String getClassName() {
        return className;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getMaxCargo() {
        return maxCargo;
    }

    public int getPlating() {
        return plating;
    }

    public int getSpaceAvailable() {
        return spaceAvailable;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public String getFlightPlanId() {
        return flightPlanId;
    }
}
