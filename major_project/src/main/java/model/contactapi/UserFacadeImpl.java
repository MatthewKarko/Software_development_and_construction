package model.contactapi;

import com.google.gson.Gson;
import model.api.InputApi;
import model.api.OutputAPI;
import model.gsondatabase.*;
import model.SqlDatabase;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is responsible for contacting the api and returning the information in a desired format
 */
public class UserFacadeImpl implements UserFacade {
    final InputApi inputAPI;
    final OutputAPI outputAPI;
    CashFlowReports cashFlowReports;
    final StringBuilder returnJson = new StringBuilder();
    final Gson gson = new Gson();
    SqlDatabase database = null;
    String savedSymbol = "";
    String savedDate = "";
    String savedCurrentChart = "";
    Double savedValue = 0.0;

    public UserFacadeImpl(InputApi inputAPI, OutputAPI outputAPI, SqlDatabase database){
        this.inputAPI = inputAPI;
        this.outputAPI = outputAPI;
        if(database != null){
            this.database = database;
            this.database.createDB();
            this.database.setupDB();
        }
    }


    /**
     * Returns an array list of best matches
     * @param input the user input
     * @return An arraylist of best matches based on the input
     */

    @Override
    public ArrayList<String> getBestMatches(String input) {
        ArrayList<String> suggestions = new ArrayList<>();
        ListOfBestMatches bm =  inputAPI.bestMatches(input);
        ArrayList<BestMatch> bestMatches = bm.getBestMatches();

        if(bm.getErrorMessage() != null){
            suggestions.add(bm.getErrorMessage());
            return suggestions;
        }
        if(bm.getNote() != null){
            suggestions.add(bm.getNote());
            return suggestions;
        }

        if(bestMatches.size() == 0){
            suggestions.add("Could not find any matches");
            return suggestions;
        }


        for (BestMatch bms: bestMatches){
            suggestions.add(bms.getSymbol());
        }
        return suggestions;
    }

    /**
     * Returns a string with the company information
     * @param symbol What stock ticker it is getting the information for
     * @param type Type 1 is a API search, Type 2 is cache
     * @return A string with the company information
     */
    @Override
    public String companyOverview(String symbol, Boolean type){
        StringBuilder builder = new StringBuilder();
        if (database == null){
            if(!type){
                return "nocache";
            }
            CompanyOverView companyOverView = inputAPI.companyOverView(symbol);
            if(companyOverView.getErrorMessage() != null){
                return companyOverView.getErrorMessage();
            }
            if(companyOverView.getNote() != null){
                return companyOverView.getNote();
            }

            if(companyOverView.getSymbol() == null){
                return "Stock information was not found, ticker selected does not have any information in API";
            }
            builder.append("Symbol: ").append(companyOverView.getSymbol()).append("\n");
            builder.append("Name: ").append(companyOverView.getName()).append("\n");
            builder.append("\n\t\t\t\t\t\t\t\t\t\t\t").append("--Description--").append("\n");
            builder.append(companyOverView.getDescription()).append("\n");
            return builder.toString();
        }
        if(!type){
            if(checkCachedData(symbol)){
                return "cache";
            }
            return "nocache";
        }

        else{
            if (checkCachedData(symbol)){
                ArrayList<String> info = database.getStockInfo(symbol);
                if(info.size() != 0){
                    builder.append("Symbol: ").append(info.get(0)).append("\n");
                    builder.append("Name: ").append(info.get(1)).append("\n");
                    builder.append("\n\t\t\t\t\t\t\t\t\t\t\t").append("--Description--").append("\n");
                    builder.append(info.get(2)).append("\n");
                }
                else{
                    builder.append("Error Cached Data not found");
                }
            }

            else{
                CompanyOverView companyOverView = inputAPI.companyOverView(symbol);
                if(companyOverView.getErrorMessage() != null){
                    return companyOverView.getErrorMessage();
                }
                if(companyOverView.getNote() != null){
                    return companyOverView.getNote();
                }

                if(companyOverView.getSymbol() == null){
                    return "Stock information was not found, ticker selected does not have any information in API";
                }
                if(database.getStockInfo(symbol).size() == 0) {
                    database.insertStockInfo(companyOverView.getSymbol(), companyOverView.getName(), companyOverView.getDescription());
                }
                builder.append("Symbol: ").append(companyOverView.getSymbol()).append("\n");
                builder.append("Name: ").append(companyOverView.getName()).append("\n");
                builder.append("\n\t\t\t\t\t\t\t\t\t\t\t").append("--Description--").append("\n");
                builder.append(companyOverView.getDescription()).append("\n");
            }
        }
        return builder.toString();

    }

    /**
     * Returns a boolean if there is cached data
     * @param symbol the stock ticker symbol
     * @return A boolean representing if there is cached data
     */
    @Override
    public Boolean checkCachedData(String symbol){
        if(database == null){
            return false;
        }
        ArrayList<String> info = database.getStockInfo(symbol);
        if(info.size() ==0){
            return false;
        }
        return true;
    }

    /**
     * Removes the cached data
     */
    @Override
    public void removeCachedData(){
        if(database == null){
            return;
        }
        database.removeCachedData();
    }

    /**
     * Returns a string of the cash flow report information
     * @param symbol The selected stock ticker
     * @return A string containing report information
     */
    @Override
    public String setCashFlowReport(String symbol){
        cashFlowReports = inputAPI.cashflowReports(symbol);
        if(cashFlowReports.getErrorMessage() != null){
            return cashFlowReports.getErrorMessage();
        }

        if(cashFlowReports.getNote() != null){
            return cashFlowReports.getNote();
        }

        if(cashFlowReports.getQuarterlyReports() == null){
            return "Stock information was not found, ticker selected does not have any information in API";
        }

        return "";
    }

    /**
     * Returns the chart information specific to request
     * @param chartType the user requested chart type
     * @return A Map of the chart content
     */
    @Override
    public Map<String, Double> getChartInfo(String chartType) {
        Map<String, Double> info = new HashMap<>();
        ArrayList<Report> reports = cashFlowReports.getQuarterlyReports();


        switch (chartType) {
            case "Operating Cash Flow":
                for (Report report : reports) {
                    info.put(report.getFiscalDateEnding(), (Double.parseDouble(report.getOperatingCashflow()) / 1000000000));
                }
                break;
            case "Capital Expenditures":
                for (Report report : reports) {
                    info.put(report.getFiscalDateEnding(), (Double.parseDouble(report.getCapitalExpenditures()) / 1000000000));
                }
                break;
            case "Profit/Loss":
                for (Report report : reports) {
                    info.put(report.getFiscalDateEnding(), (Double.parseDouble(report.getProfitLoss()) / 1000000000));
                }
                break;
            case "Dividend Payout":
                for (Report report : reports) {
                    info.put(report.getFiscalDateEnding(), (Double.parseDouble(report.getDividendPayout()) / 1000000000));
                }
                break;
            case "Net Income":
                for (Report report : reports) {
                    info.put(report.getFiscalDateEnding(), (Double.parseDouble(report.getNetIncome()) / 1000000000));
                }
                break;
            default:
                return null;
        }
        return info;
    }

    /**
     * Sends a report of the required contents to the pastebin API
     * @param input the contents of the report
     * @return A string based on the state of the API call
     */
    @Override
    public String sendReport(String input){
        if(input.equals("")){
            return "Error, No current stock is selected";
        }

        CompanyOverView companyOverView = inputAPI.companyOverView(input);
        CashFlowReports cfReports = inputAPI.cashflowReports(input);

        if(getBestMatches(input).size() == 1  && cfReports.getQuarterlyReports() == null){
            return "Error, selected stock has no valid information on API.";
        }
        returnJson.append(gson.toJson(companyOverView)).append("\n");
        returnJson.append("{" +  '"' + "charts" + '"' + " : " ).append(gson.toJson(cfReports.getQuarterlyReports())).append("\n}").append("\n");


        return outputAPI.sendReport(returnJson.toString());
    }

    /**
     * Setting the information for the saved chart
     * @param savedSymbol The ticket symbol of the saved chart
     * @param savedCurrentChart the type of chart saved
     * @param savedDate the date of the information saved
     * @param savedValue the value of the saved information
     */

    @Override
    public void setSavedChartInfo(String savedSymbol, String savedCurrentChart,  String savedDate, Double savedValue){
        this.savedSymbol = savedSymbol;
        this.savedDate = savedDate;
        this.savedCurrentChart = savedCurrentChart;
        this.savedValue = savedValue;

    }

    /**
     * Returns a state if the new company's value is larger than the last
     * @return A string that
     */
    @Override
    public String checkSavedValue(){
        if(savedCurrentChart.equals("")){
            return "";
        }
        Map<String, Double> chartInfo = getChartInfo(savedCurrentChart);
        ArrayList<String> sortedInfo = null;
        try {
            sortedInfo = sortedChartInformation(chartInfo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String finalKey = sortedInfo.get(sortedInfo.size()-1);
        Double finalValue = chartInfo.get(finalKey);
        if(finalValue > savedValue){
            return savedCurrentChart;
        }
        else{
            return "";
        }

    }

    /**
     * Returns information about the saved chart
     * @return A string containing the saved information
     */
    @Override
    public String getSavedChartInfo(){
        StringBuilder b = new StringBuilder();
        b.append("Symbol of saved chart data: ").append(savedSymbol).append("\n");
        b.append("Saved chart data last value: ").append(savedValue).append(" Billion(USD)").append("\n");
        return b.toString();
    }

    /**
     * Returns the currently saved chart
     * @return A string containing the currently saved chart
     */
    @Override
    public String getSavedCurrentChart(){
        return savedCurrentChart;
    }

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
