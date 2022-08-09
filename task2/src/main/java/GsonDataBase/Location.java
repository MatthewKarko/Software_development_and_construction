package GsonDataBase;

public class Location {
    Boolean allowsConstruction;
    String name;
    String symbol;
    String type;
    int x;
    int y;

    public String getAllowsConstruction() {
        return allowsConstruction.toString();
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
