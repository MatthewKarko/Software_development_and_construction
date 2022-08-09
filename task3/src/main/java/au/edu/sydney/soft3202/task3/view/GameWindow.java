package au.edu.sydney.soft3202.task3.view;

import au.edu.sydney.soft3202.task3.model.GameBoard;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * This is the overall window scene for the application. It creates and contains the different elements in the
 * top, bottom, center, and right side of the window, along with linking them to the model.
 *
 * Identify the mutable couplings between the View and the Model: This class and the BoardPane are the only 2 View
 * classes that mutate the Model, and all mutations go first to the GameBoard. There is coupling in other ways
 * from the View to the Model, but they are accessor methods only.
 *
 * Also note that while this represents the game window, it *contains* the Scene that JavaFX needs, and does not
 * inherit from Scene. This is true for all JavaFX components in this application, they are contained, not extended.
 */
public class GameWindow {
    private final BoardPane boardPane;
    private final Scene scene;
    private MenuBar menuBar;
    private VBox sideButtonBar;
    private ArrayList<Integer> userIdList = new ArrayList<>();
    private int user_id;
    private String username;

    private final GameBoard model;

    public GameWindow(GameBoard model) {
        this.model = model;

        BorderPane pane = new BorderPane();
        this.scene = new Scene(pane);

        this.boardPane = new BoardPane(model);
        StatusBarPane statusBar = new StatusBarPane(model);
        buildMenu();
        buildSideButtons();
        buildKeyListeners();

        pane.setCenter(boardPane.getPane());
        pane.setTop(menuBar);
        pane.setRight(sideButtonBar);
        pane.setBottom(statusBar.getStatusBar());

    }

    private void buildKeyListeners() {
        // This allows keyboard input. Note that the scene is used, so any time
        // the window is in focus the keyboard input will be registered.
        // More often, keyboard input is more closely linked to a specific
        // node that must have focus, i.e. the Enter key in a text input to submit
        // a form.

        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.isControlDown() && event.getCode() == KeyCode.N) {
                newGameAction();
            }
            if (event.isControlDown() && event.getCode() == KeyCode.S) {
                serialiseAction();
            }
            if (event.isControlDown() && event.getCode() == KeyCode.L) {
                deserialiseAction();
            }
        });
    }

    private void buildSideButtons() {
        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction((event) -> newGameAction());

        Button serialiseBtn = new Button("Save Data");
        serialiseBtn.setOnAction((event) -> serialiseAction());

        Button deserialiseBtn = new Button("Load Data");
        deserialiseBtn.setOnAction((event) -> deserialiseAction());

        this.sideButtonBar = new VBox(newGameBtn, serialiseBtn, deserialiseBtn);
        sideButtonBar.setSpacing(10);
    }

    private void buildMenu() {
        Menu actionMenu = new Menu("Actions");

        MenuItem newGameItm = new MenuItem("New Game");
        newGameItm.setOnAction((event)-> newGameAction());

        MenuItem serialiseItm = new MenuItem("Serialise");
        serialiseItm.setOnAction((event)-> serialiseAction());

        MenuItem deserialiseItm = new MenuItem("Deserialise");
        deserialiseItm.setOnAction((event)-> deserialiseAction());

        actionMenu.getItems().addAll(newGameItm, serialiseItm, deserialiseItm);

        this.menuBar = new MenuBar();
        menuBar.getMenus().add(actionMenu);

        TextInputDialog userName = new TextInputDialog("");
        userName.setTitle("Username");
        userName.setHeaderText("Enter your desired username:");


        userName.showAndWait();
        username = userName.getEditor().getText();
        if (!username.equals("")) {
            if (!model.checkUsername(username)){
                if(!userIdList.contains(user_id)){
                    user_id = generateUserId();
                    userIdList.add(user_id);
                    model.insertUser(user_id, username);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("New user");
                    alert.setHeaderText("New user created!");
                    alert.showAndWait();
                }

            }
            else if (username.equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Field cannot be blank!");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Logged in");
                alert.setHeaderText("Welcome back!");
                user_id = model.getUserID(username);
                alert.showAndWait();
                System.out.println(user_id);
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Field cannot be blank!");
            alert.showAndWait();
            buildMenu();
        }

    }

    private void newGameAction() {
        // Note the separation here between newGameAction and doNewGame. This allows
        // for the validation aspects to be separated from the operation itself.

        if (null == model.getCurrentTurn()) { // no current game
            doNewGame();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Game Warning");
        alert.setHeaderText("Starting a new game now will lose all current progress.");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
         doNewGame();
        }
    }

    private void serialiseAction() {
        // Serialisation is a way of turning some data into a communicable form.
        // In Java it has a library to support it, but here we are just manually converting the field
        // we know we need into a string (in the model). We can then use that string in reverse to get that state back

        if (null == model.getCurrentTurn()) { // no current game
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Serialisation Error");
            alert.setHeaderText("There is no game to serialise!");

            alert.showAndWait();
            return;
        }

//        String serialisation = model.serialise();

        TextInputDialog textInput = new TextInputDialog("");
        textInput.setTitle("Save game state");
        textInput.setHeaderText("Save Data name:");
        textInput.showAndWait();
        String input = textInput.getEditor().getText();
        if (!input.equals("")) {

            if (!model.getSaveDataNames(username).contains(input)){
                model.insertGameSave(user_id, input, model.serialise());
            }

            else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Save name exists, are you sure you want to overwrite?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent()){
                    if(result.get() == ButtonType.OK){
                        model.deleteGameSave(user_id,input);
                        model.insertGameSave(user_id,input,model.serialise());
                    }
                }


            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Field cannot be blank!");
            alert.showAndWait();
        }
    }

    private void deserialiseAction() {
        // Here we take an existing serialisation string and feed it back into the model to retrieve that state.
        // We don't do any validation here, as that would leak model knowledge into the view.

//        TextInputDialog textInput = new TextInputDialog("");
//        textInput.setTitle("Load game Data");
//        textInput.setHeaderText("Enter your serialisation string:");
//
//        Optional<String> input = textInput.showAndWait();
        if(model.getSaveDataNames(username).size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("There is no current save data!");
            alert.showAndWait();
        }
        ChoiceDialog<String> d = new ChoiceDialog(model.getSaveDataNames(username).get(0), model.getSaveDataNames(username));
        d.setHeaderText("List of current saves");
        // set content text
        d.setContentText("Select save to load: ");
        // show the dialog
        d.showAndWait();
        String input = d.getSelectedItem();

        if (!input.equals("")) {
            try {

                model.deserialise(model.getSaveData(input, user_id));
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Serialisation Error");
                alert.setHeaderText(e.getMessage());

                alert.showAndWait();
                return;
            }

            boardPane.updateBoard();
        }
    }

    private void doNewGame() {
        // Here we have an action that we know would likely mutate the state of the model, and so the view should
        // update. Unlike the StatusBarPane that uses the observer pattern to do this, here we can just trigger it
        // because we know the model will mutate as a result of our call to it.
        // Generally speaking the observer pattern is superior - I would recommend using it instead of
        // doing it this way.

        model.newGame();
        boardPane.updateBoard();
    }

    public Scene getScene() {
        return this.scene;
    }

    public Integer generateUserId(){
        Random rand = new Random(); //instance of random class
        int upperbound = 1000;
        //generate random values from 0-24
        return rand.nextInt(upperbound);
    }
}
