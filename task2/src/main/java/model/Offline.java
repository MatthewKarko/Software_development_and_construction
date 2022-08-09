package model;

import GsonDataBase.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offline implements API{


    @Override
    public String getServerStatus() {
        String json = "{\"status\":\"spacetraders is currently online and available to play\"}\n";
        Gson gson = new Gson();
        ServerStatus status = gson.fromJson(json, ServerStatus.class);
        System.out.println(status);
        return status.getServerStatus();
    }

    @Override
    public String claimUsername(String username) {
        String json = "{\"token\":\"7f68c9ba-5817-4186-abe3-4070185ad2f2\",\"user\":{\"username\":\"1423425435\",\"credits\":0,\"ships\":[],\"loans\":[]}}";
        Gson gson = new Gson();
        claimUsername user = gson.fromJson(json, claimUsername.class);
        return "Your token is: " + user.getToken();

    }

    @Override
    public String getToken() {
        return "7f68c9ba-5817-4186-abe3-4070185ad2f2";
    }

    @Override
    public UserAcc getAccountInfo() {
        String json = "{\"user\":{\"username\":\"1423425435\",\"shipCount\":0,\"structureCount\":0,\"joinedAt\":\"2022-04-10T07:54:09.634Z\",\"credits\":0}}\n";
        Gson gson = new Gson();
        AccountInfo accountInfo = gson.fromJson(json, AccountInfo.class);
        System.out.println(accountInfo.getUserAcc().getUsername());
        return accountInfo.getUserAcc();
    }

    @Override
    public List<Loan> getListofLoans() {
        String json = "{\"loans\":[{\"type\":\"STARTUP\",\"amount\":200000,\"rate\":40,\"termInDays\":2,\"collateralRequired\":false}]}";
        Gson gson = new Gson();
        LoanInfo loanInfo = gson.fromJson(json, LoanInfo.class);

        return loanInfo.getLoans();
    }

    @Override
    public AcceptedLoanDetails takeLoan(String type) {
        String json = "{\"credits\":200000,\"loan\":{\"id\":\"cl1t00i9z107100215s6clm3n0jr\",\"due\":\"2022-04-12T08:02:51.142Z\",\"repaymentAmount\":280000,\"status\":\"CURRENT\",\"type\":\"STARTUP\"}}";
        Gson gson = new Gson();
        AcceptedLoan takenLoan = gson.fromJson(json, AcceptedLoan.class);
        return takenLoan.getLoan();

    }

    @Override
    public List<AcceptedLoanDetails> getListOfCurrentLoans() {
        String json = "{\"loans\":[{\"id\":\"cl1t00i9z107100215s6clm3n0jr\",\"due\":\"2022-04-12T08:02:51.142Z\",\"repaymentAmount\":280000,\"status\":\"CURRENT\",\"type\":\"STARTUP\"}]}\n";
        Gson gson = new Gson();
        CurrentLoans loans = gson.fromJson(json, CurrentLoans.class);

        return loans.getLoans();

    }

    @Override
    public List<ShipListing> getListOfShips(String className) {
        String json = "{\"shipListings\":[{\"type\":\"JW-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"loadingSpeed\":25,\"speed\":1,\"manufacturer\":\"Jackshaw\",\"plating\":5,\"weapons\":5,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-PM-TR\",\"price\":21125}\n" +
                "]},{\"type\":\"JW-MK-II\",\"class\":\"MK-II\",\"maxCargo\":100,\"loadingSpeed\":25,\"speed\":2,\"manufacturer\":\"Jackshaw\",\"plating\":10,\"weapons\":10,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-PM-TR\",\"price\":83300}]},{\"type\":\"GR-MK-I\",\"class\n" +
                "\":\"MK-I\",\"maxCargo\":100,\"loadingSpeed\":100,\"speed\":1,\"manufacturer\":\"Gravager\",\"plating\":10,\"weapons\":5,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-PM-TR\",\"price\":42650}]},{\"type\":\"ZA-MK-II\",\"class\":\"MK-II\",\"maxCargo\":100,\"lo\n" +
                "adingSpeed\":100,\"speed\":2,\"manufacturer\":\"Zetra\",\"plating\":5,\"weapons\":5,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-PM-TR\",\"price\":72700},{\"system\":\"OE\",\"location\":\"OE-UC-OB\",\"price\":72700}]},{\"type\":\"ZA-MK-III\",\"class\":\"MK-\n" +
                "III\",\"maxCargo\":300,\"loadingSpeed\":100,\"speed\":2,\"manufacturer\":\"Zetra\",\"plating\":10,\"weapons\":10,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-PM-TR\",\"price\":213900},{\"system\":\"OE\",\"location\":\"OE-UC-OB\",\"price\":213900}]},{\"typ\n" +
                "e\":\"EM-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"loadingSpeed\":25,\"speed\":2,\"manufacturer\":\"Electrum\",\"plating\":5,\"weapons\":10,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-PM-TR\",\"price\":37750}]},{\"type\":\"HM-MK-I\",\"class\":\"MK-I\",\"ma\n" +
                "xCargo\":50,\"loadingSpeed\":25,\"speed\":3,\"manufacturer\":\"Hermes\",\"plating\":20,\"weapons\":5,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-PM-TR\",\"price\":57525}]},{\"type\":\"GR-MK-II\",\"class\":\"MK-II\",\"maxCargo\":300,\"loadingSpeed\":500,\n" +
                "\"speed\":1,\"manufacturer\":\"Gravager\",\"plating\":10,\"weapons\":5,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-UC-AD\",\"price\":125550},{\"system\":\"OE\",\"location\":\"OE-NY\",\"price\":125550}]},{\"type\":\"GR-MK-III\",\"class\":\"MK-III\",\"maxCarg\n" +
                "o\":500,\"loadingSpeed\":500,\"speed\":1,\"manufacturer\":\"Gravager\",\"plating\":10,\"weapons\":10,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-UC-AD\",\"price\":203210},{\"system\":\"OE\",\"location\":\"OE-NY\",\"price\":203210}]},{\"type\":\"HM-MK-III\n" +
                "\",\"class\":\"MK-III\",\"maxCargo\":300,\"loadingSpeed\":100,\"speed\":4,\"manufacturer\":\"Hermes\",\"plating\":20,\"weapons\":5,\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-UC-AD\",\"price\":445200}]},{\"type\":\"TD-MK-I\",\"class\":\"MK-I\",\"maxCargo\":\n" +
                "3000,\"loadingSpeed\":1000,\"speed\":2,\"manufacturer\":\"Tiddalik\",\"plating\":10,\"weapons\":5,\"restrictedGoods\":[\"FUEL\"],\"purchaseLocations\":[{\"system\":\"OE\",\"location\":\"OE-UC-AD\",\"price\":473600}]}]}\n";
        Gson gson = new Gson();
        ListOfShipListings listOfShipListings = gson.fromJson(json, ListOfShipListings.class);

        return Arrays.asList(listOfShipListings.getShipListings());

    }

    @Override
    public ShipDetails purchaseShip(String location, String type) {
        String json = "{\"credits\":157750,\"ship\":{\"id\":\"cl1t057tt108614515s6yhaieppz\",\"location\":\"OE-PM-TR\",\"x\":-20,\"y\":5,\"cargo\":[],\"spaceAvailable\":50,\"type\":\"JW-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"loadingSpeed\":25,\"speed\":1,\"manufactur\n" +
                "er\":\"Jackshaw\",\"plating\":5,\"weapons\":5}}\n";
        Gson gson = new Gson();
        return gson.fromJson(json, ShipDetails.class);

    }

    @Override
    public List<Ship> getListOfCurrentShips() {
        String json = "{\"ships\":[{\"id\":\"cl1t057d2108609815s64cs7zh89\",\"location\":\"OE-PM-TR\",\"x\":-20,\"y\":5,\"cargo\":[],\"spaceAvailable\":50,\"type\":\"JW-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"loadingSpeed\":25,\"speed\":1,\"manufacturer\":\"Jackshaw\",\n" +
                "\"plating\":5,\"weapons\":5},{\"id\":\"cl1t057tt108614515s6yhaieppz\",\"location\":\"OE-PM-TR\",\"x\":-20,\"y\":5,\"cargo\":[],\"spaceAvailable\":50,\"type\":\"JW-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"loadingSpeed\":25,\"speed\":1,\"manufacturer\":\"Jackshaw\",\"pl\n" +
                "ating\":5,\"weapons\":5}]}\n";
        Gson gson = new Gson();
        MyShips ships = gson.fromJson(json, MyShips.class);
        return Arrays.asList(ships.getShips());
    }

    @Override
    public PurchaseGood purchaseShipFuel(String shipID, String quantity) {
        String json = "{\"credits\":157630,\"order\":{\"good\":\"FUEL\",\"quantity\":20,\"pricePerUnit\":3,\"total\":60},\"ship\":{\"id\":\"cl1t057d2108609815s64cs7zh89\",\"location\":\"OE-PM-TR\",\"x\":-20,\"y\":5,\"cargo\":[{\"good\":\"FUEL\",\"quantity\":40,\"totalVolume\n" +
                "\":40}],\"spaceAvailable\":10,\"type\":\"JW-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"loadingSpeed\":25,\"speed\":1,\"manufacturer\":\"Jackshaw\",\"plating\":5,\"weapons\":5}}\n";
        Gson gson = new Gson();
        PurchaseGood purchaseFuel = gson.fromJson(json, PurchaseGood.class);
        return purchaseFuel;

    }

    @Override
    public ArrayList<Location> getListOfLocations(String type) {
        String json = "{\"locations\":[{\"symbol\":\"OE-PM\",\"type\":\"PLANET\",\"name\":\"Prime\",\"x\":-19,\"y\":3,\"allowsConstruction\":false,\"traits\":[\"METAL_ORES\",\"SOME_NATURAL_CHEMICALS\"]},{\"symbol\":\"OE-PM-TR\",\"type\":\"MOON\",\"name\":\"Tritus\",\"x\":-20,\"\n" +
                "y\":5,\"allowsConstruction\":false,\"traits\":[\"NATURAL_CHEMICALS\"]},{\"symbol\":\"OE-CR\",\"type\":\"PLANET\",\"name\":\"Carth\",\"x\":4,\"y\":-13,\"allowsConstruction\":false,\"traits\":[\"METAL_ORES\",\"NATURAL_CHEMICALS\",\"ABUNDANT_RARE_METAL_ORES\",\"ABUNDAN\n" +
                "T_TECHNOLOGICAL_RUINS\"]},{\"symbol\":\"OE-KO\",\"type\":\"PLANET\",\"name\":\"Koria\",\"x\":-49,\"y\":1,\"allowsConstruction\":false,\"traits\":[\"SOME_NATURAL_CHEMICALS\",\"SOME_RARE_METAL_ORES\"]},{\"symbol\":\"OE-UC\",\"type\":\"PLANET\",\"name\":\"Ucarro\",\"x\":-17\n" +
                ",\"y\":-72,\"allowsConstruction\":false,\"traits\":[\"SOME_METAL_ORES\",\"ARABLE_LAND\"]},{\"symbol\":\"OE-UC-AD\",\"type\":\"MOON\",\"name\":\"Ado\",\"x\":-15,\"y\":-73,\"allowsConstruction\":false,\"traits\":[\"SOME_METAL_ORES\",\"SOME_RARE_METAL_ORES\",\"TECHNOLOG\n" +
                "ICAL_RUINS\"]},{\"symbol\":\"OE-UC-OB\",\"type\":\"MOON\",\"name\":\"Obo\",\"x\":-17,\"y\":-74,\"allowsConstruction\":false,\"traits\":[\"SOME_METAL_ORES\",\"NATURAL_CHEMICALS\",\"SOME_TECHNOLOGICAL_RUINS\"]},{\"symbol\":\"OE-NY\",\"type\":\"ASTEROID\",\"name\":\"Nyon\",\n" +
                "\"x\":43,\"y\":-46,\"allowsConstruction\":true,\"traits\":[\"SOME_NATURAL_CHEMICALS\",\"RARE_METAL_ORES\"]},{\"symbol\":\"OE-BO\",\"type\":\"GAS_GIANT\",\"name\":\"Bo\",\"x\":-59,\"y\":60,\"allowsConstruction\":true,\"traits\":[\"SOME_HELIUM_3\",\"TECHNOLOGICAL_RUINS\n" +
                "\"]},{\"symbol\":\"OE-W-XV\",\"type\":\"WORMHOLE\",\"name\":\"Wormhole\",\"x\":5,\"y\":-101,\"allowsConstruction\":false,\"traits\":[],\"messages\":[\"Extensive research has revealed a partially functioning warp gate harnessing the power of an unstable but\n" +
                " traversable wormhole.\",\"The scientific community has determined a means of stabilizing the ancient structure.\",\"Enter at your own risk.\",\"GET https://api.spacetraders.io/locations/OE-W-XV/structures\",\"POST https://api.spacetraders.\n" +
                "io/structures/:structureId/deposit shipId=:shipId good=:goodSymbol quantity=:quantity\",\"POST https://api.spacetraders.io/my/warp-jumps shipId=:shipId\"]}]}";
        Gson gson = new Gson();
        ListOfLocations locations = gson.fromJson(json, ListOfLocations.class);
        return new ArrayList<>(Arrays.asList(locations.getLocations()));

    }

    @Override
    public ArrayList<Good> getMarketplaceListings(String location) {
        String json = "{\"marketplace\":[{\"symbol\":\"METALS\",\"volumePerUnit\":1,\"pricePerUnit\":4,\"spread\":1,\"purchasePricePerUnit\":5,\"sellPricePerUnit\":3,\"quantityAvailable\":89522},{\"symbol\":\"CONSTRUCTION_MATERIALS\",\"volumePerUnit\":1,\"priceP\n" +
                "erUnit\":102,\"spread\":3,\"purchasePricePerUnit\":105,\"sellPricePerUnit\":99,\"quantityAvailable\":21316},{\"symbol\":\"FUSION_REACTORS\",\"volumePerUnit\":6,\"pricePerUnit\":20400,\"spread\":196,\"purchasePricePerUnit\":20596,\"sellPricePerUnit\":20204\n" +
                ",\"quantityAvailable\":0},{\"symbol\":\"FUEL\",\"volumePerUnit\":1,\"pricePerUnit\":3,\"spread\":1,\"purchasePricePerUnit\":4,\"sellPricePerUnit\":2,\"quantityAvailable\":85333}]}\n";

        Gson gson = new Gson();
        Marketplace marketplace = gson.fromJson(json, Marketplace.class);
        ArrayList<Good> a1 = new ArrayList<>(Arrays.asList(marketplace.getGoods()));
        return a1;
    }

    @Override
    public PurchaseGood purchaseGoods(String shipID, String good, String quantity) {
        String json = "{\"credits\":157530,\"order\":{\"good\":\"METALS\",\"quantity\":10,\"pricePerUnit\":5,\"total\":50},\"ship\":{\"id\":\"cl1t057tt108614515s6yhaieppz\",\"location\":\"OE-PM-TR\",\"x\":-20,\"y\":5,\"cargo\":[{\"good\":\"METALS\",\"quantity\":20,\"totalVo\n" +
                "lume\":20}],\"spaceAvailable\":30,\"type\":\"JW-MK-I\",\"class\":\"MK-I\",\"maxCargo\":50,\"loadingSpeed\":25,\"speed\":1,\"manufacturer\":\"Jackshaw\",\"plating\":5,\"weapons\":5}}\n";
        Gson gson = new Gson();
        PurchaseGood purchaseGood = gson.fromJson(json, PurchaseGood.class);
        return purchaseGood;
    }

    @Override
    public FlightPlan createFlightPLan(String shipID, String destination) {
        String json = "{\"flightPlan\":{\"id\":\"cl1t0s2wi114247115s6o1xyunu0\",\"shipId\":\"cl1t0re0i114077215s69stmw6o2\",\"createdAt\":\"2022-04-10T08:24:17.586Z\",\"arrivesAt\":\"2022-04-10T08:26:17.584Z\",\"destination\":\"OE-CR\",\"departure\":\"OE-PM-TR\",\n" +
                "\"distance\":30,\"fuelConsumed\":5,\"fuelRemaining\":20,\"terminatedAt\":null,\"timeRemainingInSeconds\":119}}\n";
        Gson gson = new Gson();
        FlightList flightList = gson.fromJson(json, FlightList.class);
        return flightList.getFlightPlan();

    }

    @Override
    public FlightPlan viewFlightPlan(String flightPlanId) {
        String json = "{\"flightPlan\":{\"id\":\"cl1t1bo5f119919315s6mbtshjw1\",\"shipId\":\"cl1t1b1v2119712415s6my1r76cs\",\"createdAt\":\"2022-04-10T08:39:31.587Z\",\"arrivesAt\":\"2022-04-10T08:40:07.585Z\",\"destination\":\"OE-PM\",\"departure\":\"OE-PM-TR\",\n" +
                "\"distance\":2,\"fuelConsumed\":1,\"fuelRemaining\":25,\"terminatedAt\":null,\"timeRemainingInSeconds\":30}}\n";
        Gson gson = new Gson();
        FlightList flightList = gson.fromJson(json, FlightList.class);
        return flightList.getFlightPlan();
    }

    @Override
    public PurchaseGood sellGoods(String shipID, String good, String quantity) {
        String json = "{\"credits\":157710,\"order\":{\"good\":\"METALS\",\"quantity\":10,\"pricePerUnit\":3,\"total\":30},\"ship\":{\"id\":\"cl1t23dc6127380815s6rgpiexd0\",\"location\":\"OE-PM-TR\",\"x\":-20,\"y\":5,\"cargo\":[],\"spaceAvailable\":50,\"type\":\"JW-MK-I\",\n" +
                "\"class\":\"MK-I\",\"maxCargo\":50,\"loadingSpeed\":25,\"speed\":1,\"manufacturer\":\"Jackshaw\",\"plating\":5,\"weapons\":5}}\n";
        Gson gson = new Gson();
        PurchaseGood purchaseGood = gson.fromJson(json, PurchaseGood.class);
        return purchaseGood;

    }

    @Override
    public String getErrorMessage() {
        return "Ship has insufficient fuel for flight plan. You require 1 more FUEL";
    }

    @Override
    public String setToken(String token) {
        String json = "{\"user\":{\"username\":\"426346346346\",\"shipCount\":0,\"structureCount\":0,\"joinedAt\":\"2022-04-10T11:00:52.753Z\",\"credits\":0}}\n";
        Gson gson = new Gson();
        AccountInfo accountInfo = gson.fromJson(json, AccountInfo.class);
        return "33426f22-38d8-49ef-be8f-40219c80a154";

    }
}
