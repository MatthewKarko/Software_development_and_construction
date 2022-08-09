package controller;

import model.API;

public interface UserFacade {

    String getServerStatus();
    String claimUsername(String username);
    String getAccountInfo();
    String getListOfLoans();
    String takeLoan(String type);
    String getListOfCurrentLoans();
    String getListOfShips(String className);
    String purchaseShip(String location, String type);
    String getListOfCurrentShips();
    String purchaseShipFuel(String shipId, String quantity);
    API getApi();
    String getListOfLocations(String type);
    String[] getTypeOfLocationsDropBox();
    String[] getListOfLocationsDropBox();
    String getMarketplaceItems(String location);
    String[] getMarketPlaceItemsDropBox(String location);
    String purchaseGood(String shipId, String good, String quantity);
    String[] getListOfCurrentShipsDropBox();
    String getFlightPlanString(String shipId, String destination);
    String viewFlightPlanString(String shipId);
    String sellGoods(String shipId, String good, String quantity);
    String[] getShipItemDropBox(String shipId);
    String loginUser(String token);
    String getToken();
    String copyTokenToClipboard();
}
