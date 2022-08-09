
package model.api;

import model.gsondatabase.CashFlowReports;
import model.gsondatabase.CompanyOverView;
import model.gsondatabase.ListOfBestMatches;

/**
 * This class is responsible for connecting to the input api
 */
public interface InputApi {

    /**
     * Obtain a list of stock tickers
     * @return A list of stock tickers
     */

    ListOfBestMatches bestMatches(String input);

    /**
     * Returns the company overview on the inputted stock symbol
     * @param symbol A stock ticker that is selected
     * @return The company overview information
     */
    CompanyOverView companyOverView(String symbol);

    /**
     * Returns the information in order to create the charts
     * @param symbol The stock that the chart information is being found for
     * @return A class that contains the information that is required to generate the reports
     */
    CashFlowReports cashflowReports(String symbol);


}
