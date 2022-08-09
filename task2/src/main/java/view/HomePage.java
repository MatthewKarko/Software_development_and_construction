package view;

import GsonDataBase.Loan;
import GsonDataBase.Ship;
import GsonDataBase.ShipListing;
import controller.UserFacade;


import javax.swing.*;
import java.util.ArrayList;

public class HomePage {
    UserFacade facade;

    public HomePage(UserFacade facade){
        this.facade = facade;

    }
    public void display(){
        JFrame frame = new JFrame("Home Page");
        frame.setBounds(50, 50, 900, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton getServerStatus = new JButton("Get Server status");
        getServerStatus.setBounds(100, 50, 250, 30);
        getServerStatus.addActionListener(e -> {
            OutputPage outputPage  = new OutputPage();
            outputPage.bigDisplay(facade.getServerStatus(), "Server Status");

        });
        JButton createToken = new JButton("Claim a username and get a token");
        createToken.setBounds(100, 100, 250, 30);
        createToken.addActionListener(e -> {
            InputPage page = new InputPage(facade);
            page.claimUsername(new String[1]);

        });
        JButton accInfo = new JButton("View Account Information");
        accInfo.setBounds(100, 150, 250, 30);
        accInfo.addActionListener(e -> {
            OutputPage outputPage  = new OutputPage();
            outputPage.bigDisplay(facade.getAccountInfo(), "Account Information");

        });
        JButton listOfLoans = new JButton("View List of Loans");
        listOfLoans.setBounds(100, 200, 250, 30);
        listOfLoans.addActionListener(e -> {
            OutputPage outputPage  = new OutputPage();
            outputPage.bigDisplay(facade.getListOfLoans(), "List of Loans");

        });
        JButton takeLoan = new JButton("Take out a Loan");
        takeLoan.setBounds(100, 250, 250, 30);
        takeLoan.addActionListener(e -> {
            if(facade.getApi().getListofLoans() == null){
                OutputPage outputPage  = new OutputPage();
                outputPage.bigDisplay(facade.getListOfCurrentLoans(), "Error");
            }
            InputPage page = new InputPage(facade);
            page.takeLoan((ArrayList<Loan>) facade.getApi().getListofLoans());

        });
        JButton listOfCurrentLoans = new JButton("View List of Current Loans");
        listOfCurrentLoans.setBounds(100, 300, 250, 30);
        listOfCurrentLoans.addActionListener(e -> {
            OutputPage outputPage  = new OutputPage();
            outputPage.bigDisplay(facade.getListOfCurrentLoans(), "List of Current Loans");

        });
        JButton listOfShips = new JButton("View List of Ships");
        listOfShips.setBounds(100, 350, 250, 30);
        listOfShips.addActionListener(e -> {
            InputPage page = new InputPage(facade);
            page.listShips(new String[1]);


        });
        JButton purchaseShip = new JButton("Purchase Ship");
        purchaseShip.setBounds(100, 400, 250, 30);
        purchaseShip.addActionListener(e -> {
            if(facade.getApi().getListOfShips("") == null){
                OutputPage output = new OutputPage();
                output.bigDisplay(facade.getApi().getErrorMessage(), "Error");

            }

            InputPage page = new InputPage(facade);
            ArrayList<ShipListing> a1 = new ArrayList<>(facade.getApi().getListOfShips(""));
            page.purchaseShip(a1);




        });
        JButton getListOfYourShips = new JButton("Get list of your ships");
        getListOfYourShips.setBounds(100, 450, 250, 30);
        getListOfYourShips.addActionListener(e -> {
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getListOfCurrentShips(), "Current Ships");


        });

        JButton purchaseFuel = new JButton("Purchase fuel");
        purchaseFuel.setBounds(400,50 , 250, 30);
        purchaseFuel.addActionListener(e -> {
            if(facade.getApi().getListOfCurrentShips() == null){
                InputPage page = new InputPage(facade);
                ArrayList<Ship> a1 = new ArrayList<>();
                page.purchaseShipFuel(a1);
            }
            InputPage page = new InputPage(facade);
            ArrayList<Ship> a1 = new ArrayList<>(facade.getApi().getListOfCurrentShips());
            page.purchaseShipFuel(a1);




        });
        JButton listLocations = new JButton("View List of Nearby Locations");
        listLocations.setBounds(400,100 , 250, 30);
        listLocations.addActionListener(e -> {

            InputPage page = new InputPage(facade);
            page.locationsList();



        });
        JButton locationMarketPlace = new JButton("View Location Marketplace");
        locationMarketPlace.setBounds(400,150 , 250, 30);
        locationMarketPlace.addActionListener(e -> {
            InputPage page = new InputPage(facade);
            page.listLocations();



        });
        JButton purchaseGoods = new JButton("Purchase Good From Marketplace");
        purchaseGoods.setBounds(400,200 , 250, 30);
        purchaseGoods.addActionListener(e -> {
            InputPage page = new InputPage(facade);
            page.purchaseAGood();



        });

        JButton createFlightPlan = new JButton("Create a flight plan");
        createFlightPlan.setBounds(400,250 , 250, 30);
        createFlightPlan.addActionListener(e -> {
            InputPage page = new InputPage(facade);
            page.createFlightPlan();



        });
        JButton viewFlightPlan = new JButton("View current ship flights");
        viewFlightPlan.setBounds(400,300 , 250, 30);
        viewFlightPlan.addActionListener(e -> {
            InputPage page = new InputPage(facade);
            page.viewFlightPlan();



        });
        JButton sellGoods = new JButton("Sell Goods From Ship");
        sellGoods.setBounds(400,350 , 250, 30);
        sellGoods.addActionListener(e -> {
            InputPage page = new InputPage(facade);
            page.sellGood();



        });

        JButton login = new JButton("Login");
        login.setBounds(400,400 , 250, 30);
        login.addActionListener(e -> {
            InputPage page = new InputPage(facade);
            page.loginUser(new String[1]);

        });

        frame.add(getServerStatus);
        frame.add(createToken);
        frame.add(accInfo);
        frame.add(listOfLoans);
        frame.add(takeLoan);
        frame.add(listOfCurrentLoans);
        frame.add(listOfShips);
        frame.add(purchaseShip);
        frame.add(getListOfYourShips);
        frame.add(purchaseFuel);
        frame.add(listLocations);
        frame.add(locationMarketPlace);
        frame.add(purchaseGoods);
        frame.add(createFlightPlan);
        frame.add(viewFlightPlan);
        frame.add(sellGoods);
        frame.add(login);

        frame.setVisible(true);
    }
}
