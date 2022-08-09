package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * This class is responsible for creating and maintaining the menu bar
 */
public class Menubar {
    BorderPane borderPane;
    public Menubar(BorderPane borderPane){
        this.borderPane = borderPane;
    }

    /**
     * This class is responsible for building and maintaining the menubar
     */

    public void buildMenubar(){
        javafx.scene.control.MenuBar menuBar = new javafx.scene.control.MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem about = new MenuItem("About");

        menu.getItems().addAll(about);
        menuBar.getMenus().addAll(menu);


        about.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About Us");
            alert.setHeaderText("Information on team");
            alert.setContentText(aboutUsString());
            alert.getDialogPane().setPrefHeight(250);
            alert.getDialogPane().setPrefWidth(550);
            alert.showAndWait();


        });

        borderPane.setTop(menuBar);
    }

    private String aboutUsString(){
        StringBuilder builder = new StringBuilder();

        builder.append("\t\tApplication name: Alpha Vantage Application").append("\n");
        builder.append("\t\tDeveloper name: Matthew Karko").append("\n");
        builder.append("\t\tReferences used: \n\t\thttps://gist.github.com/yelken/46063302acdc230f446a \n\t\thttps://www.youtube.com/watch?v=UttTfT4pdHo&ab_channel=BetterTracks\n\t\thttps://tenor.com/view/load-loading-waiting-gif-5662595\n\t\thttps://www.pngfind.com/mpng/hTRhhTx_aa-logo-png-letter-a-logo-png-transparent/").append("\n");
        return builder.toString();
    }
}
