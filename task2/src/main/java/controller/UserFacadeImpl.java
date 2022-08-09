package controller;

import GsonDataBase.*;
import model.API;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.datatransfer.Clipboard;

public class UserFacadeImpl implements UserFacade {
    private final API api;
    private String token;
    private String locationString;
    public UserFacadeImpl(API api) {
        this.api = api;
        token = api.getToken();

    }

    public String getServerStatus(){
        return api.getServerStatus();

    }

    public String claimUsername(String username){
        return api.claimUsername(username);
    }
    public String loginUser(String token){
        String login = api.setToken(token);
        if (login.equals(token)){
            return getAccountInfo();
        }
        return login;
    }

    public String getAccountInfo(){
        if(api.getAccountInfo() == null){
            return api.getErrorMessage();
        }
        UserAcc acc = api.getAccountInfo();
        String returnString = "Your username: " + acc.getUsername() + "\n" + "Amount of Credits: " + acc.getCredits() + "\n" + "Joined at: " + acc.getJoinedAt() + "\n" + "Ship count: " + acc.getShipCount() + "\n" + "Structure count: " + acc.getStructureCount() + "\n";
        return returnString;
    }

    @Override
    public String getListOfLoans(){
        if(api.getListofLoans() == null){
            return api.getErrorMessage();
        }
        List<Loan> loans = api.getListofLoans();

        StringBuilder builder = new StringBuilder();
        for (Loan loan : loans){
            builder.append("Amount: ").append(loan.getAmount()).append("\n");
            builder.append("Collateral Required: ").append(loan.getCollateralRequired()).append("\n");
            builder.append("Rate: ").append(loan.getRate()).append("\n");
            builder.append("Term in Days: ").append(loan.getTermInDays()).append("\n");
            builder.append("Type: ").append(loan.getType()).append("\n");
        }
        return builder.toString();
    }
    @Override
    public String getListOfCurrentLoans(){
        if(api.getListOfCurrentLoans() == null){
            return api.getErrorMessage();
        }
        List<AcceptedLoanDetails> loans = api.getListOfCurrentLoans();
        StringBuilder builder = new StringBuilder();
        for (AcceptedLoanDetails loan : loans){
            builder.append("Due: ").append(loan.getDue()).append("\n");
            builder.append("ID: ").append(loan.getId()).append("\n");
            builder.append("Repayment amount: ").append(loan.getRepaymentAmount()).append("\n");
            builder.append("Status: ").append(loan.getStatus()).append("\n");
            builder.append("Type: ").append(loan.getType()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String takeLoan(String type){

        AcceptedLoanDetails loan = api.takeLoan(type);

        if (loan == null){
            return "Error, Please check authorisation token and type.";
        }


        StringBuilder builder = new StringBuilder();

        builder.append("Due: ").append(loan.getDue()).append("\n");
        builder.append("ID: ").append(loan.getId()).append("\n");
        builder.append("Repayment amount: ").append(loan.getRepaymentAmount()).append("\n");
        builder.append("Status: ").append(loan.getStatus()).append("\n");
        builder.append("Type: ").append(loan.getType()).append("\n");

        return builder.toString();
    }

    @Override
    public String getListOfShips(String className){
        if(api.getListOfShips(className) == null){
            return api.getErrorMessage();
        }
        List<ShipListing> ships = api.getListOfShips(className);
        StringBuilder builder = new StringBuilder();
        for (ShipListing ship : ships){
            builder.append("Ship #").append(ships.indexOf(ship) + 1).append("\n");
            builder.append("Class: ").append(ship.getClassName()).append("\n");
            builder.append("Manufacturer: ").append(ship.getManufacturer()).append("\n");
            builder.append("Max cargo: ").append(ship.getMaxCargo()).append("\n");
            builder.append("Plating: ").append(ship.getPlating()).append("\n");
            builder.append("Speed: ").append(ship.getSpeed()).append("\n");
            builder.append("Type: ").append(ship.getType()).append("\n");
            builder.append("Weapons: ").append(ship.getWeapons()).append("\n");
            for (PurchaseLocation location: ship.getPurchaseLocations()){
                builder.append("Location ").append(ship.getPurchaseLocations().indexOf(location) + 1).append(": ").append(location.getLocation()).append(" -- ").append("Price: ").append(location.getPrice()).append("\n");
//                builder.append("\t\n").append("Location: ").append("\n");
//                builder.append("\t\n")
            }
            builder.append("\n\n");
        }
        return builder.toString();
    }

    @Override
    public String purchaseShip(String location, String type){



        ShipDetails shipDetails = api.purchaseShip(location,type);
        if(shipDetails == null){
            return api.getErrorMessage();
        }
        Ship ship = shipDetails.getShip();
        StringBuilder builder = new StringBuilder();


        builder.append("User Credits: ").append(shipDetails.getCredits()).append("\n\n");
        builder.append("--Ship Information--" + "\n");
        builder.append("Cargo: ").append(ship.getCargo().length).append("\n");
        builder.append("Class: ").append(ship.getClassName()).append("\n");
        builder.append("ID: ").append(ship.getId()).append("\n");
        builder.append("Location: ").append(ship.getLocation()).append("\n");
        builder.append("Manufacturer: ").append(ship.getManufacturer()).append("\n");
        builder.append("Max cargo: ").append(ship.getMaxCargo()).append("\n");
        builder.append("Space available: ").append(ship.getSpaceAvailable()).append("\n");
        builder.append("Plating: ").append(ship.getPlating()).append("\n");
        builder.append("Speed: ").append(ship.getSpeed()).append("\n");
        builder.append("Type: ").append(ship.getType()).append("\n");
        builder.append("Weapons: ").append(ship.getWeapons()).append("\n");


        return builder.toString();
    }

    @Override
    public String getListOfCurrentShips(){


        List<Ship> ships = api.getListOfCurrentShips();
        if(ships == null){
            return api.getErrorMessage();
        }else if (ships.size() == 0){
            return "You have no current ships";
        }

        StringBuilder builder = new StringBuilder();
        for (Ship ship : ships){
            Cargo[] cargos = ship.getCargo();
            ArrayList<Cargo> cargoArrayList = new ArrayList<>(Arrays.asList(cargos));

            builder.append("--Ship #").append(ships.indexOf(ship) + 1).append("--").append("\n");
            builder.append("Class: ").append(ship.getClassName()).append("\n");
            builder.append("ID: ").append(ship.getId()).append("\n");
            if(ship.getFlightPlanId() != null){
                builder.append("Flight Plan ID: ").append(ship.getFlightPlanId()).append("\n");
            }
            builder.append("=Cargo Items=").append("\n\n");
            for(Cargo cargo : cargoArrayList){
                builder.append("Cargo Item #").append((cargoArrayList.indexOf(cargo)+1)).append("\n");
                builder.append("Good: ").append(cargo.getGood()).append("\n");
                builder.append("Quantity: ").append(cargo.getQuantity()).append("\n");
                builder.append("Total volume: ").append(cargo.getTotalVolume()).append("\n");
                builder.append("\n");
            }
            builder.append("\n");
            builder.append("Location: ").append(ship.getLocation()).append("\n");
            builder.append("Manufacturer: ").append(ship.getManufacturer()).append("\n");
            builder.append("Max cargo: ").append(ship.getMaxCargo()).append("\n");
            builder.append("Space available: ").append(ship.getSpaceAvailable()).append("\n");
            builder.append("Plating: ").append(ship.getPlating()).append("\n");
            builder.append("Speed: ").append(ship.getSpeed()).append("\n");
            builder.append("Type: ").append(ship.getType()).append("\n");
            builder.append("Weapons: ").append(ship.getWeapons()).append("\n");
            builder.append("X coord: ").append(ship.getX()).append("\n");
            builder.append("Y coord: ").append(ship.getY()).append("\n");
           
            builder.append("\n\n");
        }
        return builder.toString();
    }
    @Override
    public String[] getListOfCurrentShipsDropBox(){
        List<Ship> ships = api.getListOfCurrentShips();
        if(ships == null){
            return new String[0];
        }
        ArrayList<Ship> a1 = new ArrayList<>(ships);
        ArrayList<String> dropboxItems = new ArrayList<>();
        for (Ship ship : a1){
            dropboxItems.add(ship.getId());
        }
        String[] dropBox = new String[dropboxItems.size()];
        dropBox = dropboxItems.toArray(dropBox);
        return dropBox;

    }

    @Override
    public String purchaseShipFuel(String shipId, String quantity){
        if(api.purchaseShipFuel(shipId,quantity) == null || api.getListOfCurrentShips().size() == 0){
            return api.getErrorMessage();
        }

        PurchaseGood purchaseShipFuel = api.purchaseShipFuel(shipId,quantity);
        StringBuilder builder = new StringBuilder();
        Ship ship = purchaseShipFuel.getShip();
        Cargo[] cargos = purchaseShipFuel.getShip().getCargo();
        Order order = purchaseShipFuel.getOrder();
//        ArrayList<Cargo> cargoArrayList = (ArrayList<Cargo>) Arrays.asList(cargos);
        ArrayList<Cargo> cargoArrayList = new ArrayList<>(Arrays.asList(cargos));
        builder.append("User Credits: ").append(purchaseShipFuel.getCredits()).append("\n\n");
        builder.append("--Order Information--" + "\n");
        builder.append("Good: ").append(order.getGood()).append("\n");
        builder.append("Quantity: ").append(order.getQuantity()).append("\n");
        builder.append("Total volume: ").append(order.getTotal()).append("\n");
        builder.append("\n");
        builder.append("--Ship Information--" + "\n");
        builder.append("Cargo Items: ").append("\n\t");
        for(Cargo cargo : cargoArrayList){
            builder.append("Cargo Item #").append((cargoArrayList.indexOf(cargo)+1)).append("\n");
            builder.append("Good: ").append(cargo.getGood()).append("\n");
            builder.append("Quantity: ").append(cargo.getQuantity()).append("\n");
            builder.append("Total volume: ").append(cargo.getTotalVolume()).append("\n");
        }
        builder.append("Class: ").append(ship.getClassName()).append("\n");
        builder.append("ID: ").append(ship.getId()).append("\n");
        builder.append("Location: ").append(ship.getLocation()).append("\n");
        builder.append("Manufacturer: ").append(ship.getManufacturer()).append("\n");
        builder.append("Max cargo: ").append(ship.getMaxCargo()).append("\n");
        builder.append("Space available: ").append(ship.getSpaceAvailable()).append("\n");
        builder.append("Plating: ").append(ship.getPlating()).append("\n");
        builder.append("Speed: ").append(ship.getSpeed()).append("\n");
        builder.append("Type: ").append(ship.getType()).append("\n");
        builder.append("Weapons: ").append(ship.getWeapons()).append("\n");
        builder.append("X coord: ").append(ship.getX()).append("\n");
        builder.append("Y coord: ").append(ship.getY()).append("\n");


        return builder.toString();
    }

    @Override
    public String getListOfLocations(String type){
        ArrayList<Location> locations = api.getListOfLocations(type);
        if (locations == null){
            return api.getErrorMessage();
        }
        StringBuilder builder = new StringBuilder();
        for(Location location : locations){
            builder.append("Location ").append(locations.indexOf(location) + 1).append(": ").append("\n");
            builder.append("Name: ").append(location.getName()).append("\n");
            builder.append("Symbol: ").append(location.getSymbol()).append("\n");
            builder.append("Allows construction: ").append(location.getAllowsConstruction()).append("\n");
            builder.append("Type: ").append(location.getType()).append("\n");
            builder.append("X coord: ").append(location.getX()).append("\n");
            builder.append("Y coord: ").append(location.getY()).append("\n");
            builder.append("\n");


        }
        return builder.toString();

    }
    @Override
    public String[] getTypeOfLocationsDropBox(){
        ArrayList<Location> locations = api.getListOfLocations("");
        if(locations == null){
            return  new String[0];
        }
        ArrayList<String> dropboxItems = new ArrayList<>();
        dropboxItems.add("");
        for(Location location: locations){
            if(!dropboxItems.contains(location.getType())){
                dropboxItems.add(location.getType());
            }

        }
        String[] dropBox = new String[dropboxItems.size()];
        dropBox = dropboxItems.toArray(dropBox);
        return dropBox;

    }
    @Override
    public String[] getListOfLocationsDropBox(){

        ArrayList<Location> locations = api.getListOfLocations("");
        if(locations == null){
            return new String[0];
        }
        ArrayList<String> dropboxItems = new ArrayList<>();
        for(Location location: locations){
            dropboxItems.add(location.getSymbol());
        }
        String[] dropBox = new String[dropboxItems.size()];
        dropBox = dropboxItems.toArray(dropBox);
        return dropBox;

    }

    @Override
    public String getMarketplaceItems(String location){
        ArrayList<Good> goods = api.getMarketplaceListings(location);
        if(goods == null){
            return api.getErrorMessage();
        }

        StringBuilder builder = new StringBuilder();
        for(Good good : goods){
            builder.append("--Good #").append(goods.indexOf(good) + 1).append("-- ").append("\n");
            builder.append("Price per unit: ").append(good.getPricePerUnit()).append("\n");
            builder.append("Quantity available: ").append(good.getQuantityAvailable()).append("\n");
            builder.append("Symbol: ").append(good.getSymbol()).append("\n");
            builder.append("Volume per unit: ").append(good.getVolumePerUnit()).append("\n");
            builder.append("\n");


        }
        return builder.toString();

    }

    @Override
    public String[] getMarketPlaceItemsDropBox(String location){
        ArrayList<Good> goods = api.getMarketplaceListings(location);
        if(goods == null){
            return new String[0];
        }

        ArrayList<String> dropboxItems = new ArrayList<>();
        for(Good good: goods){
            dropboxItems.add(good.getSymbol());
        }
        String[] dropBox = new String[dropboxItems.size()];
        dropBox = dropboxItems.toArray(dropBox);
        return dropBox;

    }

    @Override
    public String purchaseGood(String shipId, String good, String quantity){



        PurchaseGood purchaseGood = api.purchaseGoods(shipId,good,quantity);
        if(purchaseGood == null){
            return api.getErrorMessage();
        }
        StringBuilder builder = new StringBuilder();
        Ship ship = purchaseGood.getShip();
        Cargo[] cargos = purchaseGood.getShip().getCargo();
        Order order = purchaseGood.getOrder();
//        ArrayList<Cargo> cargoArrayList = (ArrayList<Cargo>) Arrays.asList(cargos);
        ArrayList<Cargo> cargoArrayList = new ArrayList<>(Arrays.asList(cargos));
        builder.append("User Credits: ").append(purchaseGood.getCredits()).append("\n\n");
        builder.append("--Order Information--" + "\n");
        builder.append("Good: ").append(order.getGood()).append("\n");
        builder.append("Price per unit: ").append(order.getPricePerUnit()).append("\n");
        builder.append("Quantity: ").append(order.getQuantity()).append("\n");
        builder.append("Total volume: ").append(order.getTotal()).append("\n");
        builder.append("\n");
        builder.append("--Ship Information--" + "\n");
        builder.append("Cargo Items: ").append("\n\t");
        for(Cargo cargo : cargoArrayList){
            builder.append("Cargo Item #").append((cargoArrayList.indexOf(cargo)+1)).append("\n");
            builder.append("Good: ").append(cargo.getGood()).append("\n");
            builder.append("Quantity: ").append(cargo.getQuantity()).append("\n");
            builder.append("Total volume: ").append(cargo.getTotalVolume()).append("\n");
            builder.append("\n\n");
        }
        builder.append("Class: ").append(ship.getClassName()).append("\n");
        builder.append("ID: ").append(ship.getId()).append("\n");
        builder.append("Location: ").append(ship.getLocation()).append("\n");
        builder.append("Manufacturer: ").append(ship.getManufacturer()).append("\n");
        builder.append("Max cargo: ").append(ship.getMaxCargo()).append("\n");
        builder.append("Space available: ").append(ship.getSpaceAvailable()).append("\n");
        builder.append("Plating: ").append(ship.getPlating()).append("\n");
        builder.append("Speed: ").append(ship.getSpeed()).append("\n");
        builder.append("Type: ").append(ship.getType()).append("\n");
        builder.append("Weapons: ").append(ship.getWeapons()).append("\n");
        builder.append("X coord: ").append(ship.getX()).append("\n");
        builder.append("Y coord: ").append(ship.getY()).append("\n");


        return builder.toString();
    }

    @Override
    public String[] getShipItemDropBox(String shipId){
        List<Ship> s = api.getListOfCurrentShips();
        if(s == null){
            return new String[0];
        }
        ArrayList<Ship> ships = new ArrayList<>(s);
        Ship finalShip = null;

        for(Ship ship : ships){
            if(ship.getId().equals(shipId)){
                finalShip = ship;
            }
        }

        ArrayList<String> dropboxItems = new ArrayList<>();
        for(Cargo cargo: finalShip.getCargo()){
            dropboxItems.add(cargo.getGood());
        }
        String[] dropBox = new String[dropboxItems.size()];
        dropBox = dropboxItems.toArray(dropBox);
        return dropBox;

    }
    @Override
    public String sellGoods(String shipId, String good, String quantity){

        PurchaseGood soldGood = api.sellGoods(shipId,good,quantity);
        if(soldGood == null) {
            return api.getErrorMessage();
        }
        StringBuilder builder = new StringBuilder();
        Ship ship = soldGood.getShip();
        Cargo[] cargos = soldGood.getShip().getCargo();
        Order order = soldGood.getOrder();
//        ArrayList<Cargo> cargoArrayList = (ArrayList<Cargo>) Arrays.asList(cargos);
        ArrayList<Cargo> cargoArrayList = new ArrayList<>(Arrays.asList(cargos));
        builder.append("User Credits: ").append(soldGood.getCredits()).append("\n\n");
        builder.append("--Order Information--" + "\n");
        builder.append("Good: ").append(order.getGood()).append("\n");
        builder.append("Quantity: ").append(order.getQuantity()).append("\n");
        builder.append("Total volume: ").append(order.getTotal()).append("\n");
        builder.append("\n");
        builder.append("--Ship Information--" + "\n");
        builder.append("Cargo Items: ").append("\n\t");
        for(Cargo cargo : cargoArrayList){
            builder.append("Cargo Item #").append((cargoArrayList.indexOf(cargo)+1)).append("\n");
            builder.append("Good: ").append(cargo.getGood()).append("\n");
            builder.append("Quantity: ").append(cargo.getQuantity()).append("\n");
            builder.append("Total volume: ").append(cargo.getTotalVolume()).append("\n");
            builder.append("\n\n");
        }
        builder.append("Class: ").append(ship.getClassName()).append("\n");
        builder.append("ID: ").append(ship.getId()).append("\n");
        builder.append("Location: ").append(ship.getLocation()).append("\n");
        builder.append("Manufacturer: ").append(ship.getManufacturer()).append("\n");
        builder.append("Max cargo: ").append(ship.getMaxCargo()).append("\n");
        builder.append("Space available: ").append(ship.getSpaceAvailable()).append("\n");
        builder.append("Plating: ").append(ship.getPlating()).append("\n");
        builder.append("Speed: ").append(ship.getSpeed()).append("\n");
        builder.append("Type: ").append(ship.getType()).append("\n");
        builder.append("Weapons: ").append(ship.getWeapons()).append("\n");
        builder.append("X coord: ").append(ship.getX()).append("\n");
        builder.append("Y coord: ").append(ship.getY()).append("\n");


        return builder.toString();
    }
    @Override
    public String getFlightPlanString(String shipId, String destination){

        FlightPlan flightPlan = api.createFlightPLan(shipId,destination);
        if(flightPlan == null){
            return api.getErrorMessage();
        }
        StringBuilder builder = new StringBuilder();

        builder.append("--Flight Plan Info--").append("\n");
        builder.append("Arrival time: ").append(flightPlan.getArrivesAt()).append("\n");
        builder.append("Departure time: ").append(flightPlan.getDeparture()).append("\n");
        builder.append("Destination: ").append(flightPlan.getDestination()).append("\n");
        builder.append("Distance: ").append(flightPlan.getDistance()).append("\n");
        builder.append("Fuel Consumed: ").append(flightPlan.getFuelConsumed()).append("\n");
        builder.append("Fuel Remaining: ").append(flightPlan.getFuelRemaining()).append("\n");
        builder.append("Flight ID: ").append(flightPlan.getId()).append("\n");
        builder.append("Ship ID: ").append(flightPlan.getShipId()).append("\n");
        builder.append("Terminated At: ").append(flightPlan.getTerminatedAt()).append("\n");
        builder.append("Time remaining (Seconds): ").append(flightPlan.getTimeRemainingInSeconds()).append("\n");
        builder.append("\n");



        return builder.toString();

    }

    @Override
    public String viewFlightPlanString(String shipId){

        ArrayList<Ship> ships = new ArrayList<>(api.getListOfCurrentShips());
        String flightId = "";
        System.out.println(ships.get(0).getFlightPlanId());
        for(Ship ship : ships){
            System.out.println(ship.getFlightPlanId());
            if(ship.getId().equals(shipId)){
                flightId = ship.getFlightPlanId();
            }
        }
        FlightPlan flightPlan = api.viewFlightPlan(flightId);
        if(flightPlan == null){
            return api.getErrorMessage();
        }
        StringBuilder builder = new StringBuilder();

        builder.append("--Flight Plan Info--").append("\n");
        builder.append("Arrival time: ").append(flightPlan.getArrivesAt()).append("\n");
        builder.append("Departure time: ").append(flightPlan.getDeparture()).append("\n");
        builder.append("Destination: ").append(flightPlan.getDestination()).append("\n");
        builder.append("Distance: ").append(flightPlan.getDistance()).append("\n");
        builder.append("Fuel Consumed: ").append(flightPlan.getFuelConsumed()).append("\n");
        builder.append("Fuel Remaining: ").append(flightPlan.getFuelRemaining()).append("\n");
        builder.append("Flight ID: ").append(flightPlan.getId()).append("\n");
        builder.append("Ship ID: ").append(flightPlan.getShipId()).append("\n");
        builder.append("Terminated At: ").append(flightPlan.getTerminatedAt()).append("\n");
        builder.append("Time remaining (Seconds): ").append(flightPlan.getTimeRemainingInSeconds()).append("\n");
        builder.append("\n");



        return builder.toString();

    }


    @Override
    public API getApi() {
        return api;
    }

    @Override
    public String getToken(){
        return api.getToken();
    }

    @Override
    public String copyTokenToClipboard(){
        Clipboard clipboard = getSystemClipboard();
        clipboard.setContents(new StringSelection(getToken()),null);
        return "SUCCESS";
    }

    private static Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        return defaultToolkit.getSystemClipboard();
    }
}