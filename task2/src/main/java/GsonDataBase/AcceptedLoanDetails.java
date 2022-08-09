package GsonDataBase;

public class AcceptedLoanDetails {
    String due;
    String id;
    int repaymentAmount;
    String status;
    String type;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public int getRepaymentAmount() {
        return repaymentAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getDue() {
        return due;
    }
}
