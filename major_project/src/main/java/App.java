
import model.contactapi.UserFacade;
import model.contactapi.UserFacadeImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.api.*;
import model.SqlDatabase;
import view.MainPage;

/**
 * This class is responsible for building the application
 */
public class App extends Application {

    private static MainPage view;

    /**
     * Runs the application
     * @param args input arguments
     */
    public static void main(String[] args) {
        UserFacade facade;
        InputApi inputAPI;
        OutputAPI outputAPI;
        SqlDatabase database = null;


        if (args.length != 2) {
            throw new RuntimeException("Incorrect number of arguments given");
        }
        if (args[0].equals("online")) {
            inputAPI = new OnlineInput();
            database = new SqlDatabase();
        }
        else if (args[0].equals("offline")) {
            inputAPI = new OfflineInput();
        }
        else {
            throw new RuntimeException("Input API must be online or offline");
        }

        if (args[1].equals("online")) {
            outputAPI = new OnlineOutput();
        }
        else if (args[1].equals("offline")) {
            outputAPI = new OfflineOutput();
        }
        else {
            throw new RuntimeException("Output API must be online or offline");
        }

        facade = new UserFacadeImpl(inputAPI, outputAPI, database);
        view = new MainPage(facade);
        launch(args);
    }

    /**
     * Starts the application
     * @param primaryStage the stage to initialise
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Alpha Vantage");
        primaryStage.show();
    }
}