package model;

import GsonDataBase.*;

import java.util.ArrayList;
import java.util.List;

public interface API {

    String getServerStatus();
    String claimUsername(String username);
    String getToken();
    UserAcc getAccountInfo();
//    String setToken(String token);
    List<Loan> getListofLoans();
    AcceptedLoanDetails takeLoan(String type);
    List<AcceptedLoanDetails> getListOfCurrentLoans();
    List<ShipListing> getListOfShips(String className);
    ShipDetails purchaseShip(String location, String type);
    List<Ship> getListOfCurrentShips();
    PurchaseGood purchaseShipFuel(String shipID, String quantity);
    ArrayList<Location> getListOfLocations(String type);
    ArrayList<Good> getMarketplaceListings(String location);
    PurchaseGood purchaseGoods(String shipID, String good, String quantity);
    FlightPlan createFlightPLan(String shipID, String destination);
    FlightPlan viewFlightPlan(String flightPlanId);
    PurchaseGood sellGoods(String shipID, String good, String quantity);
    String getErrorMessage();
    String setToken(String token);
}
