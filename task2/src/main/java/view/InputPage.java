package view;

import GsonDataBase.*;
import controller.UserFacade;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class InputPage {
    private final UserFacade facade;
    public InputPage(UserFacade facade){
        this.facade = facade;
    }
    private int index;
    private ShipListing selectedShip;
    private Ship shipSelected;
    private String type = "";

    public void claimUsername(String[] container){
        JFrame frame = new JFrame("Claim username and receive token");
        frame.setBounds(450, 150, 450, 350);
        JLabel label = new JLabel("Enter preferred username");
        JButton submitButton = new JButton("Submit");
        JTextField enteredText = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(enteredText);
        panel.add(submitButton);
        panel.add(label);
        frame.add(panel, BorderLayout.CENTER); 
        submitButton.addActionListener(e -> {
            container[0] = enteredText.getText();

            OutputPage output = new OutputPage();
            String username = facade.claimUsername(container[0]);
            output.bigDisplay(username,"Starting user information");
            if(!container[0].equals("")){
                copytoken();
            }

            frame.dispose();

        });
        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> frame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    public void copytoken(){
        JFrame frame = new JFrame("Copy token to clipboard");
        frame.setLayout(null);
        frame.setBounds(50, 50, 450, 350);
        JLabel label = new JLabel("Press submit to copy to clipboard");
        label.setBounds(100,100,300,30);
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100,140,150,30);


        submitButton.addActionListener(e -> {
            OutputPage output = new OutputPage();
            output.smallDisplay(facade.copyTokenToClipboard(), "SUCCESS");
            frame.dispose();

        });
        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> frame.dispose());
        backButton.setBounds(100,250,150,30);

        frame.add(label);
        frame.add(submitButton);
        frame.add(backButton);
        frame.setVisible(true);
    }
    public void loginUser(String[] container){
        JFrame frame = new JFrame("Login");
        frame.setBounds(450, 150, 450, 350);
        JLabel label = new JLabel("Enter token");
        JButton submitButton = new JButton("Submit");
        JTextField enteredText = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(enteredText);
        panel.add(submitButton);
        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(e -> {
            container[0] = enteredText.getText();
            enteredText.setText("Token submitted");
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.loginUser(container[0]),"Login");
            frame.dispose();

        });
        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> frame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void takeLoan(ArrayList<Loan> loans){
        JFrame frame = new JFrame("Take out a loan");
        frame.setBounds(75,75,350, 350);
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(125,130,100,20);
        ArrayList<String> loanTypes = new ArrayList<>();

        for (Loan loan : loans){
            loanTypes.add(loan.getType());
        }
        String[] loanT = new String[loanTypes.size()];
        loanT = loanTypes.toArray(loanT);
        final JComboBox db = new JComboBox(loanT);
        db.setBounds(125,100,90,20);

        submitButton.addActionListener(e -> {

            OutputPage output = new OutputPage();
            output.bigDisplay(facade.takeLoan((String) db.getItemAt(db.getSelectedIndex())),"Loan Details");
            frame.dispose();


        });
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(125,250,100,20);
        backButton.addActionListener(e -> frame.dispose());


        frame.add(submitButton);
        frame.add(backButton);

        frame.add(db);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void listShips(String[] container){
        JFrame frame = new JFrame("listShips");
        frame.setBounds(450, 150, 300, 350);
        JLabel label = new JLabel("Enter optional class type");
        JButton submitButton = new JButton("Submit");
        JTextField enteredText = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(enteredText);
        panel.add(submitButton);
        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);
        submitButton.addActionListener(e -> {
            container[0] = enteredText.getText();
            enteredText.setText("");
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getListOfShips(container[0]),"Ships Available");

        });
        JButton backButton = new JButton("Go Back");
        backButton.addActionListener(e -> frame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }




    public void purchaseShip(ArrayList<ShipListing> ships){

        JFrame frame = new JFrame("Purchase ship");
        frame.setBounds(50,50,600,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select ship referencing to Ship number in the list of ships in the view: ");
        label.setBounds(115,50,500,30);


        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,170,100,30);
        JButton showLocations = new JButton("Select Location");
        showLocations.setBounds(115,130,175,30);
        ArrayList<String> shipNumbers = new ArrayList<>();

        for (ShipListing ship : ships){
            shipNumbers.add("Ship #: "+(ships.indexOf(ship)+1));
        }

        String[] shipNums = new String[shipNumbers.size()];
        shipNums = shipNumbers.toArray(shipNums);
        final JComboBox db = new JComboBox(shipNums);
        db.setBounds(115,100,90,20);
        selectedShip = ships.get(db.getSelectedIndex());
        index = db.getSelectedIndex();


        showLocations.addActionListener(e ->{
                   this.selectedShip = ships.get(db.getSelectedIndex());
                   getLocation(selectedShip);
        } );

        submitButton.addActionListener(e -> {
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.purchaseShip(selectedShip.getPurchaseLocations().get(index).getLocation(), selectedShip.getType()),"Successfully Purchased Ship");
            frame.dispose();
        });
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(115,320,100,20);
        backButton.addActionListener(e -> frame.dispose());


        frame.add(submitButton);
        frame.add(backButton);
        frame.add(label);
        frame.add(db);
        frame.add(showLocations);

        frame.setVisible(true);
    }

    private void getLocation(ShipListing selectedShip){
        JFrame frame = new JFrame("Select location");
        frame.setSize(350,200);
        frame.setLayout(null);
        JButton backButton = new JButton("Submit");
        backButton.setBounds(100,80,100,20);

        ArrayList<String> locationArray = new ArrayList<>();
        for(PurchaseLocation location : selectedShip.getPurchaseLocations()){
            locationArray.add(location.getLocation());
        }
        String[] locations = new String[selectedShip.getPurchaseLocations().size()];
        locations = locationArray.toArray(locations);

        final JComboBox db2 = new JComboBox(locations);
        db2.setBounds(100,50,90,20);


        backButton.addActionListener(e -> {

            this.index = db2.getSelectedIndex();
            frame.dispose();
                }
                );

        frame.add(db2);
        frame.add(backButton);

        frame.setVisible(true);
    }

    public Boolean purchaseShipFuel(ArrayList<Ship> ships){
        if(ships.size() == 0){
            OutputPage output = new OutputPage();
            facade.purchaseShipFuel(null,null);
            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
            return null;
        }
        else if(facade.getApi().getToken().equals("")){
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
            return null;
        }
        JFrame frame = new JFrame("Purchase Fuel");
        frame.setBounds(50,50,600,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select ship referencing to Ship number in the list of ships in the view: ");
        label.setBounds(115,50,500,30);


        ArrayList<String> shipNumbers = new ArrayList<>();
        for (Ship ship : ships){
            shipNumbers.add("Ship #: "+(ships.indexOf(ship)+1));
        }

        String[] shipNums = new String[shipNumbers.size()];
        shipNums = shipNumbers.toArray(shipNums);
        final JComboBox db = new JComboBox(shipNums);
        db.setBounds(115,90,90,20);
        shipSelected = ships.get(db.getSelectedIndex());


        JTextField enteredText = new JTextField("Enter quantity");
        enteredText.setBounds(115,120,200,30);
        enteredText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                enteredText.setText("");
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,250,100,20);
        submitButton.addActionListener(e -> {
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.purchaseShipFuel(shipSelected.getId(), enteredText.getText()), "Successfully Purchased Fuel");
            frame.dispose();

        });
        frame.add(label);
        frame.add(db);
        frame.add(enteredText);
        frame.add(submitButton);
        frame.setVisible(true);
        return true;
    }

    public Boolean locationsList(){

        if(facade.getListOfLocations(type).equals(facade.getApi().getErrorMessage())){
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
            return false;
        }

        JFrame frame = new JFrame("Select location type");
        frame.setBounds(50,50,600,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select type: ");
        label.setBounds(115,50,200,30);
        final JComboBox db = new JComboBox(facade.getTypeOfLocationsDropBox());
        db.setBounds(115,90,90,20);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,250,100,20);
        submitButton.addActionListener(e -> {

            this.type = (String) db.getItemAt(db.getSelectedIndex());
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getListOfLocations(type), "Locations");
            frame.dispose();

        });
        frame.add(label);
        frame.add(db);
        frame.add(submitButton);
        frame.setVisible(true);
        return true;
    }

    public Boolean listLocations(){
        String[] f = facade.getListOfLocationsDropBox();
        if(Arrays.equals(f, new String[0])){
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
            return false;
        }
        JFrame frame = new JFrame("Select location");
        frame.setBounds(50,50,400,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select location symbol: ");
        label.setBounds(115,50,200,30);
        final JComboBox db = new JComboBox(f);
        db.setBounds(115,90,90,20);



        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,160,100,20);
        submitButton.addActionListener(e -> {
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getMarketplaceItems((String) db.getItemAt(db.getSelectedIndex())), "Location Marketplace");
            frame.dispose();

        });
        frame.add(label);
        frame.add(db);
        frame.add(submitButton);
        frame.setVisible(true);
        return true;
    }

    public Boolean purchaseAGood(){
        String[] f = facade.getListOfLocationsDropBox();
        if(Arrays.equals(facade.getListOfLocationsDropBox(), new String[0])){
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
            return false;
        }
        JFrame frame = new JFrame("Select location");
        frame.setBounds(50,50,600,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select location symbol: ");
        label.setBounds(115,50,200,30);
        final JComboBox db = new JComboBox(f);
        db.setBounds(115,90,90,20);


        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,250,100,20);
        submitButton.addActionListener(e -> {
            purchaseGood((String) db.getItemAt(db.getSelectedIndex()));


            frame.dispose();

        });
        frame.add(label);
        frame.add(db);
        frame.add(submitButton);
        frame.setVisible(true);
        return true;
    }



    public Boolean purchaseGood(String location){
        String[] f = facade.getMarketPlaceItemsDropBox(location);
        if(Arrays.equals(f, new String[0])){
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
            return false;
        }
        JFrame frame = new JFrame("Purchase Goods");
        frame.setBounds(50,50,600,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select Ship ID: ");
        label.setBounds(115,50,200,30);
        int count = 1;
        ArrayList<String> shipNumbers = new ArrayList<>();
        for (String ignored : facade.getListOfCurrentShipsDropBox()){
            shipNumbers.add("Ship #: "+count);
            count++;
        }

        String[] shipNums = new String[shipNumbers.size()];
        shipNums = shipNumbers.toArray(shipNums);
        final JComboBox db = new JComboBox(shipNums);
        final JComboBox db3 = new JComboBox(facade.getListOfCurrentShipsDropBox());
//        final JComboBox db = new JComboBox(facade.getListOfCurrentShipsDropBox());
        db.setBounds(115,90,90,20);

        final JComboBox db2 = new JComboBox(f);
        db2.setBounds(115,120,90,20);

        JTextField enteredText = new JTextField("Enter quantity");
        enteredText.setBounds(115,150,200,30);
        enteredText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                enteredText.setText("");
            }
        });


        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,250,100,20);
        submitButton.addActionListener(e -> {
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.purchaseGood((String) db3.getItemAt(db.getSelectedIndex()), (String) db2.getItemAt(db2.getSelectedIndex()), enteredText.getText()), "Successfully Purchased Good");
            frame.dispose();

        });
        frame.add(label);
        frame.add(db);
        frame.add(db2);
        frame.add(enteredText);
        frame.add(submitButton);
        frame.setVisible(true);
        return true;
    }

    public Boolean sellGood(){

        JFrame frame = new JFrame("Sell Goods");
        frame.setBounds(50,50,600,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select Ship ID: ");
        label.setBounds(115,50,200,30);
        int count = 1;
        ArrayList<String> shipNumbers = new ArrayList<>();
        for (String ignored : facade.getListOfCurrentShipsDropBox()){
            shipNumbers.add("Ship #: "+count);
            count++;
        }

        String[] shipNums = new String[shipNumbers.size()];
        shipNums = shipNumbers.toArray(shipNums);
        final JComboBox db = new JComboBox(shipNums);
        final JComboBox db3 = new JComboBox(facade.getListOfCurrentShipsDropBox());
//        final JComboBox db = new JComboBox(facade.getListOfCurrentShipsDropBox());
        db.setBounds(115,90,90,20);

        String[] f = facade.getShipItemDropBox((String) db3.getItemAt(db.getSelectedIndex()));
//        if(Arrays.equals(f, new String[0])){
//            OutputPage output = new OutputPage();
//            System.out.println("here");
//            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
//            return false;
//        }

        final JComboBox db2 = new JComboBox(f);
        db2.setBounds(115,120,90,20);

        JTextField enteredText = new JTextField("Enter quantity");
        enteredText.setBounds(115,150,200,30);
        enteredText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                enteredText.setText("");
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,250,100,20);
        submitButton.addActionListener(e -> {
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.sellGoods((String) db3.getItemAt(db.getSelectedIndex()), (String) db2.getItemAt(db2.getSelectedIndex()), enteredText.getText()), "Successfully Purchased Good");
            frame.dispose();

        });
        frame.add(label);
        frame.add(db);
        frame.add(db2);
        frame.add(enteredText);
        frame.add(submitButton);
        frame.setVisible(true);
        return true;

    }

    public Boolean createFlightPlan(){
        String[] f = facade.getListOfCurrentShipsDropBox();
        if(Arrays.equals(f, new String[0])){
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
            return false;
        }
        JFrame frame = new JFrame("Create flight plan");
        frame.setBounds(50,50,600,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select Ship ID: ");
        label.setBounds(115,50,200,30);
        int count = 1;
        ArrayList<String> shipNumbers = new ArrayList<>();
        for (String ignored : f){
            shipNumbers.add("Ship #: "+count);
            count++;
        }

        String[] shipNums = new String[shipNumbers.size()];
        shipNums = shipNumbers.toArray(shipNums);
        final JComboBox db = new JComboBox(shipNums);
        final JComboBox db3 = new JComboBox(facade.getListOfCurrentShipsDropBox());
//        final JComboBox db = new JComboBox(facade.getListOfCurrentShipsDropBox());
        db.setBounds(115,90,90,20);

        final JComboBox db2 = new JComboBox(facade.getListOfLocationsDropBox());
        db2.setBounds(115,120,90,20);


        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,250,100,20);
        submitButton.addActionListener(e -> {
            OutputPage output = new OutputPage();
            String f1 = facade.getFlightPlanString((String) db3.getItemAt(db.getSelectedIndex()), (String) db2.getItemAt(db2.getSelectedIndex()));
            if(f1.equals(facade.getApi().getErrorMessage())){
                OutputPage output1 = new OutputPage();
                output1.bigDisplay(f1, "Error");
                frame.dispose();
            }
            else{
                output.bigDisplay(f1, "Flight Plan");
                frame.dispose();
            }


        });
        frame.add(label);
        frame.add(db);
        frame.add(db2);
        frame.add(submitButton);
        frame.setVisible(true);
        return true;

    }
    public Boolean viewFlightPlan(){
        if(facade.getListOfCurrentShips().equals(facade.getApi().getErrorMessage())){
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.getApi().getErrorMessage(), "Error");
            return false;
        }
        JFrame frame = new JFrame("View flight plan");
        frame.setBounds(50,50,600,400);
        frame.setLayout(null);
        JLabel label = new JLabel("Select Ship ID: ");
        label.setBounds(115,50,200,30);
        int count = 1;
        ArrayList<String> shipNumbers = new ArrayList<>();
        for (String ignored : facade.getListOfCurrentShipsDropBox()){
            shipNumbers.add("Ship #: "+count);
            count++;
        }

        String[] shipNums = new String[shipNumbers.size()];
        shipNums = shipNumbers.toArray(shipNums);
        final JComboBox db = new JComboBox(shipNums);
        final JComboBox db3 = new JComboBox(facade.getListOfCurrentShipsDropBox());
//        final JComboBox db = new JComboBox(facade.getListOfCurrentShipsDropBox());
        db.setBounds(115,90,90,20);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(115,250,100,20);
        submitButton.addActionListener(e -> {
            OutputPage output = new OutputPage();
            output.bigDisplay(facade.viewFlightPlanString((String) db3.getItemAt(db.getSelectedIndex())), "Current flight plan");
            frame.dispose();

        });
        frame.add(label);
        frame.add(db);
        frame.add(submitButton);
        frame.setVisible(true);
        return true;

    }






}
