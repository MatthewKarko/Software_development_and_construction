package model.gsondatabase;

/**
 * This class is responsible for storing the Report information from the input API
 */
public class Report {
    String fiscalDateEnding;
    String reportedCurrency;
    String operatingCashflow;
    String capitalExpenditures;
    String profitLoss;
    String dividendPayout;
    String netIncome;

    /**
     * Return the fiscal date
     * @return A string containing the fiscal date ending
     */
    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    /**
     * Returns the currency that it is recorded in
     * @return A String containing the currency
     */
    public String getReportedCurrency() {
        return reportedCurrency;
    }

    /**
     * Returns a string containing the information for the operating cash flow report
     * @return A string containing the operating cash flow report
     */

    public String getOperatingCashflow() {
        return operatingCashflow;
    }

    /**
     * Returns a string containing the information for the capital expenditure report
     * @return A string containing the capital expenditures report
     */

    public String getCapitalExpenditures() {
        return capitalExpenditures;
    }

    /**
     * Returns a string containing the information for the profit loss report
     * @return A string containing the capital profit loss report
     */

    public String getProfitLoss() {
        return profitLoss;
    }

    /**
     * Returns a string containing the information for the dividend payout report
     * @return A string containing the capital dividend payout report
     */

    public String getDividendPayout() {
        return dividendPayout;
    }

    /**
     * Returns a string containing the information for the net income report
     * @return A string containing the capital net income report
     */

    public String getNetIncome() {
        return netIncome;
    }
}
