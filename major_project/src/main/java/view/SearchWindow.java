package view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.contactapi.UserFacade;

import java.io.File;
import java.util.Optional;

/**
 * This class is responsible for creating and maintaining the search functionality
 */
public class SearchWindow {
    BorderPane borderPane;
    VBox vBox = new VBox();
    VBox lvBox = new VBox();
    ComboBox<String> combo;
    UserFacade facade;
    Boolean isSearched = false;
    public SearchWindow(BorderPane borderPane, UserFacade facade, ComboBox<String> combo){
        this.borderPane = borderPane;
        this.facade = facade;
        this.combo = combo;
        setBufferVbox();
        setLogoVBox();
    }

    /**
     * Returns a VBox with all the contents to allow the user to complete the search and display functionality
     * @return A VBox with search window page contents
     */
    public VBox searchWindow(){
        VBox centreWindow = new VBox();
        GridPane centreGridPane = new GridPane();
        GridPane bottomGridPane = new GridPane();

        Label heading = new Label("Alpha Vantage");
        heading.setFont(Font.font("Cambria", 32));
        heading.setAlignment(Pos.CENTER);

        Label label = new Label("Search: ");
        Button searchButton = new Button("Search");
        Button clearSearchButton = new Button("Clear Search");
        Label description = new Label("");
        description.setMinHeight(300);
        description.setMaxHeight(300);
        description.setMaxWidth(650);
        description.setWrapText(true);
        description.setAlignment(Pos.TOP_LEFT);
        Button companyInfo = new Button("View company info");
        Button clearCachedData = new Button("Clear Cached Data");
        new AutoCompleteComboBoxListener<>(combo);
        centreGridPane.add(label,0,0);
        centreGridPane.add(combo,1,0);
        centreGridPane.add(searchButton,2,0);
        centreGridPane.add(clearSearchButton,3,0);
//        centreGridPane.add(companyInfo,2,1);
        centreGridPane.setHgap(10);
        centreGridPane.setVgap(5);
        bottomGridPane.add(companyInfo,0,0);
        bottomGridPane.add(clearCachedData,1,0);
        bottomGridPane.setHgap(10);
        centreWindow.setSpacing(20);
        centreWindow.setPadding(new Insets(10,0,0,25));
        centreWindow.getChildren().addAll(heading, centreGridPane, description, bottomGridPane);


        searchButton.setOnAction(event -> {
            isSearched = true;
            borderPane.setRight(vBox);
            new Thread(() -> {
                ObservableList<String> items = FXCollections.observableArrayList(facade.getBestMatches(combo.getEditor().getText()));
                Platform.runLater(() -> {
                    if(items.get(0).startsWith("Invalid")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("ERROR");
                        alert.setTitle("Error");
                        alert.setContentText(items.get(0));
                        borderPane.setRight(lvBox);
                        alert.showAndWait();
                        return;
                    }
                    borderPane.setRight(lvBox);
                    combo.setItems(items);
                    combo.show();
                });
            }).start();

        });

        clearSearchButton.setOnAction(event -> {
            combo.getEditor().setText("");
            combo.setItems(null);
            combo.hide();


        });

        clearCachedData.setOnAction(event -> {
            removeCachedData();
        });


        companyInfo.setOnAction(event -> {
            if (!isSearched){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Error");
                alert.setContentText("Error: No item has been searched and selected yet!");
                borderPane.setRight(lvBox);
                alert.showAndWait();
                return;
            }
            new Thread(() -> {

                String isCached = facade.companyOverview(combo.getEditor().getText(), false);
                Platform.runLater(() -> {
                    showCompanyInfoCache(isCached, description);
                });
            }).start();
        });


        return centreWindow;

    }

    /**
     * Returns the searched state
     * @return A boolean value for the search state
     */
    public Boolean getSearched(){
        return isSearched;
    }

    private void showCompanyInfoCache(String state, Label description){
        if(state.equals("nocache")){
            borderPane.setRight(vBox);
            new Thread(() -> {
                String companyInfo = facade.companyOverview(combo.getEditor().getText(), true);
                Platform.runLater(() ->{
                    description.setText(companyInfo);
                    borderPane.setRight(lvBox);
                });
            }).start();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Company Overview");
            alert.setHeaderText("Company information");
            alert.getDialogPane().setPrefHeight(400);
            alert.getDialogPane().setPrefWidth(720);

            //Cached or New Data
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Cache hit for this Data");
            alert1.setHeaderText("Select if you would like to use cached data or request new data");
            ButtonType buttonTypeOne = new ButtonType("Use Cached Data");
            ButtonType buttonTypeTwo = new ButtonType("Request API Data");
            ButtonType buttonTypeCancel = new ButtonType("Cancel");

            alert1.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == buttonTypeOne){
                new Thread(() -> {
                    String companyInfo = facade.companyOverview(combo.getEditor().getText(), true);
                    Platform.runLater(()->{
                        description.setText(companyInfo);
                    });
                }).start();

            } else if (result.get() == buttonTypeTwo) {
                borderPane.setRight(vBox);
                new Thread(() -> {
                    String companyInfo = facade.companyOverview(combo.getEditor().getText(), true);
                    Platform.runLater(()->{
                        description.setText(companyInfo);
                        borderPane.setRight(lvBox);
                    });
                }).start();
            }
        }
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

    private void removeCachedData(){
        facade.removeCachedData();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Successfully removed cached data");
        alert.showAndWait();
    }
}
