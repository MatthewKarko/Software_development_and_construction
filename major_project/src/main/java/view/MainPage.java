package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.contactapi.UserFacade;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.Optional;

/**
 * This class is responsible for creating and maintaing the home screen view
 */
public class MainPage {
    private final Scene scene;
    BorderPane borderPane;
    UserFacade facade;
    ComboBox<String> combo = new ComboBox<>();
    ScrollPane scrollPane = new ScrollPane();
    SearchWindow searchWindow;
    ChartsWindow chartsWindow;
    BackgroundMusic music = new BackgroundMusic();
    VBox vBox = new VBox();
    VBox lvBox = new VBox();
    /**
     * Creating the Landing Page for the user and all of the contents of the application
     * @param facade A way to contact with the model
     */
    public MainPage(UserFacade facade){
        this.facade = facade;
        borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        Menubar menuBar = new Menubar(borderPane);
        searchWindow = new SearchWindow(borderPane, facade, combo);
        chartsWindow = new ChartsWindow(borderPane,facade,combo);
        menuBar.buildMenubar();
        landingPage();
        sideMenuButtons();
        setBufferVbox();
        setLogoVBox();

        this.scene = new Scene(borderPane, 1000, 600);

    }

    private void landingPage(){
        Label label = new Label("Welcome to Alpha Vantage");
        label.setFont(Font.font("Cambria", 32));
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(150,50,50,200));
        scrollPane.setContent(label);
    }

    private void sideMenuButtons() {
        VBox sideBox = new VBox();
        Button searchandView = new Button("Search Company and View Info");
        Button viewCharts = new Button("View Company Charts");
        Button sendReport = new Button("Send Report to Pastebin");
        Button musicControl = new Button("Turn Background On/Off");

        sideBox.setSpacing(10);
        sideBox.getChildren().addAll(searchandView,viewCharts,sendReport,musicControl);
        sideBox.setAlignment(Pos.CENTER);
        borderPane.setLeft(sideBox);

        searchandView.setOnAction(event -> {
            scrollPane.setContent(searchWindow.searchWindow());
        });
        viewCharts.setOnAction(event -> {
            if (!searchWindow.getSearched()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Error");
                alert.setContentText("Error: No item has been searched and selected yet!");
                borderPane.setRight(lvBox);
                alert.showAndWait();
                return;
            }
            borderPane.setRight(vBox);
            new Thread(() -> {
                String cashflowInfo = facade.setCashFlowReport(combo.getEditor().getText());
                Platform.runLater(()->{

                    if(!cashflowInfo.equals("")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("ERROR");
                        alert.setTitle("Error");
                        alert.setContentText(cashflowInfo);
                        borderPane.setRight(lvBox);
                        alert.showAndWait();
                        scrollPane.setContent(searchWindow.searchWindow());
                        return;
                    }

                    scrollPane.setContent(chartsWindow.chartsWindow());
                    borderPane.setRight(lvBox);
                });
            }).start();

        });
        sendReport.setOnAction(event -> {
            if (!searchWindow.getSearched()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Error");
                alert.setContentText("Error: No item has been searched and selected yet!");
                borderPane.setRight(lvBox);
                alert.showAndWait();
                return;
            }
            borderPane.setRight(vBox);
            new Thread(() -> {
                String link =  facade.sendReport(combo.getEditor().getText());

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    ButtonType buttonTypeOne = new ButtonType("Copy link to clipboard");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.setTitle("Send Report");
                    alert.setHeaderText("Report contents");
                    alert.setContentText(link);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
                    borderPane.setRight(lvBox);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne){
                        Clipboard clipboard = getSystemClipboard();
                        clipboard.setContents(new StringSelection(link),null);
                    }
                    else{
                        alert.hide();
                    }
                });
            }).start();

        });
        musicControl.setOnAction(event -> {
            music.songPlayAndPause();
        });

    }



    private void setBufferVbox(){
        Image image = new Image(new File("src/main/resources/load-loading.gif").toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        vBox.getChildren().add(imageView);
        vBox.setAlignment(Pos.CENTER_RIGHT);
    }
    private void setLogoVBox(){
        Image image = new Image(new File("src/main/resources/logo.jpg").toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        lvBox.getChildren().add(imageView);
        lvBox.setAlignment(Pos.CENTER_RIGHT);
        borderPane.setRight(lvBox);
    }

    private static Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        return defaultToolkit.getSystemClipboard();
    }

    /**
     * Returns the current scene
     * @return A scene object
     */
    public Scene getScene() {
        return this.scene;
    }



}

