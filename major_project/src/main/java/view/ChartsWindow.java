package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.contactapi.UserFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class responsible for maintaining the charts window
 */
public class ChartsWindow {
    BorderPane borderPane;
    UserFacade facade;
    ComboBox<String> combo;
    String savedCurrentChart = "";
    public ChartsWindow(BorderPane borderPane, UserFacade facade, ComboBox<String> combo){
        this.borderPane = borderPane;
        this.facade = facade;
        this.combo = combo;

    }

    /**
     * Returns a VBox with the window contents for chart displaying
     * @return A vbox containing the display for showing the user the charts
     */
    public VBox chartsWindow(){
        VBox window = new VBox();
        Label savedDataInfo = new Label("");
        savedDataInfo.setMinHeight(100);
        savedDataInfo.setMaxHeight(100);
        savedDataInfo.setMaxWidth(650);
        savedDataInfo.setWrapText(true);
        savedDataInfo.setAlignment(Pos.TOP_LEFT);
        GridPane centreGridPane = new GridPane();
        HBox hBox = new HBox();
        Label heading = new Label("Alpha Vantage");
        heading.setFont(Font.font("Cambria", 32));
        heading.setAlignment(Pos.CENTER);
        Button buttonOne = new Button("Operating Cash flow");
        Button buttonTwo = new Button("Capital Expenditures");
        Button buttonThree = new Button("Profit/Loss");
        Button buttonFour = new Button("Net income");
        Button buttonFive = new Button("Dividend Payout");
        Button buttonSix = new Button("Save Current Chart");
        centreGridPane.add(buttonOne, 0,0);
        centreGridPane.add(buttonTwo,1,0);
        centreGridPane.add(buttonThree,2,0);
        centreGridPane.add(buttonFour,3,0);
        centreGridPane.add(buttonFive,4,0);
        centreGridPane.setHgap(10);
        centreGridPane.setPadding(new Insets(10,0,0,50));
        VBox chart = new VBox();
        chart.setPadding(new Insets(0,0,0,50));
        chart.setAlignment(Pos.CENTER);
        hBox.getChildren().add(buttonSix);
        hBox.setAlignment(Pos.CENTER);
        window.setSpacing(15);
        if(!facade.checkSavedValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("ALERT");
            alert.setTitle("Saved Chart Detected");
            alert.setContentText("This company's " + facade.getSavedCurrentChart() + " is bigger!");
            alert.showAndWait();
            Button b = new Button("View chart selections for new company");
            savedDataInfo.setText(facade.getSavedChartInfo());
            window.getChildren().addAll(heading, savedDataInfo, b);
            b.setOnAction(event -> {
                window.getChildren().removeAll(window.getChildren());
                window.getChildren().addAll(heading,centreGridPane, chart, hBox);
            });
        }
        else{
            window.getChildren().addAll(heading,centreGridPane, chart, hBox);
        }


        buttonOne.setOnAction(event -> {
            try {
                chart.getChildren().removeAll(chart.getChildren());
                chart.getChildren().add(createChart("Operating Cash Flow"));
                savedCurrentChart = "Operating Cash Flow";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        buttonTwo.setOnAction(event -> {
            try {
                chart.getChildren().removeAll(chart.getChildren());
                chart.getChildren().add(createChart("Capital Expenditures"));
                savedCurrentChart = "Capital Expenditures";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        buttonThree.setOnAction(event -> {
            try {
                chart.getChildren().removeAll(chart.getChildren());
                chart.getChildren().add(createChart("Profit/Loss"));
                savedCurrentChart = "Profit/Loss";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        buttonFour.setOnAction(event -> {
            try {
                chart.getChildren().removeAll(chart.getChildren());
                chart.getChildren().add(createChart("Net Income"));
                savedCurrentChart = "Net Income";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        buttonFive.setOnAction(event -> {
            try {
                chart.getChildren().removeAll(chart.getChildren());
                chart.getChildren().add(createChart("Dividend Payout"));
                savedCurrentChart = "Dividend Payout";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        buttonSix.setOnAction(event -> {
            try {
                if(!savedCurrentChart.equals("")){
                    Map<String, Double> chartInfo = facade.getChartInfo(savedCurrentChart);
                    ArrayList<String> sortedChartInformation = sortedChartInformation(chartInfo);
                    String savedDate = sortedChartInformation.get(sortedChartInformation.size() - 1);
                    Double savedValue = chartInfo.get(savedDate);
                    facade.setSavedChartInfo(combo.getEditor().getText(), savedCurrentChart,savedDate,savedValue);
                    savedCurrentChart = "";
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR");
                    alert.setTitle("Error");
                    alert.setContentText("Error: No chart is selected!");
                    alert.showAndWait();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });


        return window;
    }
    /**
     * Creates and displays the chart selected
     * @param chartType The type of chart the user has requested
     * @throws ParseException If the information cannot be found
     */
    private LineChart<String,Number> createChart(String chartType) throws ParseException {
        Map<String, Double> chartInfo = facade.getChartInfo(chartType);
        ArrayList<String> sortedChartInformation = sortedChartInformation(chartInfo);
        Stage stage = new Stage();
        stage.setTitle("Cash flow line chart");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Fiscal Date Ending");
        yAxis.setLabel("Billion(USD)");

        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle(chartType);

        XYChart.Series series = new XYChart.Series();
        for ( String key : sortedChartInformation) {
            series.getData().add(new XYChart.Data(key, chartInfo.get(key)));

        }
        series.setName("Reported Value");
        lineChart.getData().add(series);
        lineChart.setMinSize(600,420);
        return lineChart;
    }

    /**
     * Responsible for sorting the chart information to display in chronological order
     * @param chartInfo The chart information
     * @return An arraylist of the sorted chart information
     * @throws ParseException Throws if there is an issue with getting the information
     */
    private ArrayList<String> sortedChartInformation(Map<String, Double> chartInfo) throws ParseException {
        ArrayList<String> returnInfo = new ArrayList<>();
        ArrayList<Date> dates = new ArrayList<>();
        Set<String> keys = chartInfo.keySet();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(String key : keys){
            Date d = simpleDateFormat.parse(key);
            dates.add(d);
        }
        Collections.sort(dates);

        for(Date date : dates){
            returnInfo.add(simpleDateFormat.format(date));
        }

        return returnInfo;
    }


}
