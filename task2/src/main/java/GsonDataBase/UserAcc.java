package GsonDataBase;

public class UserAcc {
    private int credits;
    private String joinedAt;
    private int shipCount;
    private int structureCount;
    private String username;

    public UserAcc(int credits, String joinedAt, int shipCount, int structureCount, String username){
        this.credits = credits;
        this.joinedAt = joinedAt;
        this.shipCount = shipCount;
        this.structureCount = structureCount;
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public int getCredits() {
        return credits;
    }

    public int getShipCount() {
        return shipCount;
    }

    public int getStructureCount() {
        return structureCount;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

}
