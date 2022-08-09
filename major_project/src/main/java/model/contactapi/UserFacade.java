package model.contactapi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for creating a connection between the view and the api's
 */
public interface UserFacade {
    /**
     *  Gets a list of best matches from the API
     * @param input the user input
     * @return The list of best matches from the API
     */
    ArrayList<String> getBestMatches(String input);

    /**
     * Obtains the company overview information from the API
     * @param symbol What stock ticker it is getting the information for
     * @param type Type 1 is a API search, Type 2 is cache
     * @return The company overview information
     */
    String companyOverview(String symbol, Boolean type);

    /**
     * Gets the information on the cashflow reports for the API
     * @param symbol The selected stock ticker
     * @return A string based on the state of the API call
     */
    String setCashFlowReport(String symbol);

    /**
     * Obtain the chart information for each chart type
     * @param chartType the user requested chart type
     * @return A map of the chart contents
     * @throws ParseException if there is an issue converting the data
     */
    Map<String, Double> getChartInfo(String chartType) throws ParseException;

    /**
     * Sends a report of the required contents to the pastebin API
     * @param input the contents of the report
     * @return A string based on the state of the API call
     */
    String sendReport(String input);

    /**
     * Check if there is any existing cache data
     * @param symbol the stock ticker symbol
     * @return a true or false whether information is found
     */
    Boolean checkCachedData(String symbol);

    /**
     * Remove all cached data
     */
    void removeCachedData();

    /**
     * Setting the information for the saved chart
     * @param savedSymbol The ticket symbol of the saved chart
     * @param savedCurrentChart the type of chart saved
     * @param savedDate the date of the information saved
     * @param savedValue the value of the saved information
     */
    void setSavedChartInfo(String savedSymbol,String savedCurrentChart, String savedDate, Double savedValue);

    /**
     * Returns information about the saved chart
     * @return A string containing the saved information
     */
    String getSavedChartInfo();

    /**
     * Returns a state if the new company's value is larger than the last
     * @return A string that
     */
    String checkSavedValue();

    /**
     * Returns the currently saved chart
     * @return A string containing the currently saved chart
     */
    String getSavedCurrentChart();

}
