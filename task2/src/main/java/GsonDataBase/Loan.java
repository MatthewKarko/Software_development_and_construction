package GsonDataBase;

public class Loan {
    int amount;
    String collateralRequired;
    int rate;
    int termInDays;
    String type;


    public int getAmount() {
        return amount;
    }

    public int getRate() {
        return rate;
    }

    public int getTermInDays() {
        return termInDays;
    }

    public String getCollateralRequired() {
        return collateralRequired;
    }

    public String getType() {
        return type;
    }

}
