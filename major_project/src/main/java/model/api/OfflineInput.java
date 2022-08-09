package model.api;

import com.google.gson.Gson;
import model.gsondatabase.CashFlowReports;
import model.gsondatabase.CompanyOverView;
import model.gsondatabase.ListOfBestMatches;


/**
 * This class emulates the input api locally
 */
public class OfflineInput implements InputApi {

    /**
     * Returns a list of best matches
     * @param input The symbol of the stock
     * @return A list of best matches class
     */

    public ListOfBestMatches bestMatches(String input){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String json = "{\n" +
                "    \"bestMatches\": [\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBM\",\n" +
                "            \"2. name\": \"International Business Machines Corp\",\n" +
                "            \"3. type\": \"Equity\",\n" +
                "            \"4. region\": \"United States\",\n" +
                "            \"5. marketOpen\": \"09:30\",\n" +
                "            \"6. marketClose\": \"16:00\",\n" +
                "            \"7. timezone\": \"UTC-04\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"1.0000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBMK\",\n" +
                "            \"2. name\": \"iShares iBonds Dec 2022 Term Muni Bond ETF\",\n" +
                "            \"3. type\": \"ETF\",\n" +
                "            \"4. region\": \"United States\",\n" +
                "            \"5. marketOpen\": \"09:30\",\n" +
                "            \"6. marketClose\": \"16:00\",\n" +
                "            \"7. timezone\": \"UTC-04\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"0.8571\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBML\",\n" +
                "            \"2. name\": \"iShares iBonds Dec 2023 Term Muni Bond ETF\",\n" +
                "            \"3. type\": \"ETF\",\n" +
                "            \"4. region\": \"United States\",\n" +
                "            \"5. marketOpen\": \"09:30\",\n" +
                "            \"6. marketClose\": \"16:00\",\n" +
                "            \"7. timezone\": \"UTC-04\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"0.8571\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBMM\",\n" +
                "            \"2. name\": \"iShares iBonds Dec 2024 Term Muni Bond ETF\",\n" +
                "            \"3. type\": \"ETF\",\n" +
                "            \"4. region\": \"United States\",\n" +
                "            \"5. marketOpen\": \"09:30\",\n" +
                "            \"6. marketClose\": \"16:00\",\n" +
                "            \"7. timezone\": \"UTC-04\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"0.8571\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBMN\",\n" +
                "            \"2. name\": \"iShares iBonds Dec 2025 Term Muni Bond ETF\",\n" +
                "            \"3. type\": \"ETF\",\n" +
                "            \"4. region\": \"United States\",\n" +
                "            \"5. marketOpen\": \"09:30\",\n" +
                "            \"6. marketClose\": \"16:00\",\n" +
                "            \"7. timezone\": \"UTC-04\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"0.8571\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBMO\",\n" +
                "            \"2. name\": \"iShares iBonds Dec 2026 Term Muni Bond ETF\",\n" +
                "            \"3. type\": \"ETF\",\n" +
                "            \"4. region\": \"United States\",\n" +
                "            \"5. marketOpen\": \"09:30\",\n" +
                "            \"6. marketClose\": \"16:00\",\n" +
                "            \"7. timezone\": \"UTC-04\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"0.8571\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBMP\",\n" +
                "            \"2. name\": \"iShares iBonds Dec 2027 Term Muni Bond ETF\",\n" +
                "            \"3. type\": \"ETF\",\n" +
                "            \"4. region\": \"United States\",\n" +
                "            \"5. marketOpen\": \"09:30\",\n" +
                "            \"6. marketClose\": \"16:00\",\n" +
                "            \"7. timezone\": \"UTC-04\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"0.8571\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBM.FRK\",\n" +
                "            \"2. name\": \"International Business Machines Corporation\",\n" +
                "            \"3. type\": \"Equity\",\n" +
                "            \"4. region\": \"Frankfurt\",\n" +
                "            \"5. marketOpen\": \"08:00\",\n" +
                "            \"6. marketClose\": \"20:00\",\n" +
                "            \"7. timezone\": \"UTC+02\",\n" +
                "            \"8. currency\": \"EUR\",\n" +
                "            \"9. matchScore\": \"0.7500\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBM.LON\",\n" +
                "            \"2. name\": \"International Business Machines Corporation\",\n" +
                "            \"3. type\": \"Equity\",\n" +
                "            \"4. region\": \"United Kingdom\",\n" +
                "            \"5. marketOpen\": \"08:00\",\n" +
                "            \"6. marketClose\": \"16:30\",\n" +
                "            \"7. timezone\": \"UTC+01\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"0.7500\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"IBM.DEX\",\n" +
                "            \"2. name\": \"International Business Machines Corporation\",\n" +
                "            \"3. type\": \"Equity\",\n" +
                "            \"4. region\": \"XETRA\",\n" +
                "            \"5. marketOpen\": \"08:00\",\n" +
                "            \"6. marketClose\": \"20:00\",\n" +
                "            \"7. timezone\": \"UTC+02\",\n" +
                "            \"8. currency\": \"EUR\",\n" +
                "            \"9. matchScore\": \"0.6667\"\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";
        Gson gson = new Gson();
        ListOfBestMatches bestMatches = gson.fromJson(json, ListOfBestMatches.class);

        return bestMatches;
    }

    /**
     * Returns the company overview information
     * @param symbol A stock ticker that is selected
     * @return A class containing company overview information
     */
    @Override
    public CompanyOverView companyOverView(String symbol) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String json = "{\n" +
                "    \"Symbol\": \"IBM\",\n" +
                "    \"AssetType\": \"Common Stock\",\n" +
                "    \"Name\": \"International Business Machines Corporation\",\n" +
                "    \"Description\": \"International Business Machines Corporation (IBM) is an American multinational technology company headquartered in Armonk, New York, with operations in over 170 countries. The company began in 1911, founded in En\n" +
                "dicott, New York, as the Computing-Tabulating-Recording Company (CTR) and was renamed International Business Machines in 1924. IBM is incorporated in New York. IBM produces and sells computer hardware, middleware and software, and p\n" +
                "rovides hosting and consulting services in areas ranging from mainframe computers to nanotechnology. IBM is also a major research organization, holding the record for most annual U.S. patents generated by a business (as of 2020) for\n" +
                " 28 consecutive years. Inventions by IBM include the automated teller machine (ATM), the floppy disk, the hard disk drive, the magnetic stripe card, the relational database, the SQL programming language, the UPC barcode, and dynamic\n" +
                " random-access memory (DRAM). The IBM mainframe, exemplified by the System/360, was the dominant computing platform during the 1960s and 1970s.\",\n" +
                "    \"CIK\": \"51143\",\n" +
                "    \"Exchange\": \"NYSE\",\n" +
                "    \"Currency\": \"USD\",\n" +
                "    \"Country\": \"USA\",\n" +
                "    \"Sector\": \"TECHNOLOGY\",\n" +
                "    \"Industry\": \"COMPUTER & OFFICE EQUIPMENT\",\n" +
                "    \"Address\": \"1 NEW ORCHARD ROAD, ARMONK, NY, US\",\n" +
                "    \"FiscalYearEnd\": \"December\",\n" +
                "    \"LatestQuarter\": \"2022-03-31\",\n" +
                "    \"MarketCapitalization\": \"124678119000\",\n" +
                "    \"EBITDA\": \"12189000000\",\n" +
                "    \"PERatio\": \"21.91\",\n" +
                "    \"PEGRatio\": \"1.53\",\n" +
                "    \"BookValue\": \"21.05\",\n" +
                "    \"DividendPerShare\": \"6.56\",\n" +
                "    \"DividendYield\": \"0.0472\",\n" +
                "    \"EPS\": \"6.35\",\n" +
                "    \"RevenuePerShareTTM\": \"64.01\",\n" +
                "    \"ProfitMargin\": \"0.1\",\n" +
                "    \"OperatingMarginTTM\": \"0.101\",\n" +
                "    \"ReturnOnAssetsTTM\": \"0.0251\",\n" +
                "    \"ReturnOnEquityTTM\": \"0.237\",\n" +
                "    \"RevenueTTM\": \"57351000000\",\n" +
                "    \"GrossProfitTTM\": \"31486000000\",\n" +
                "    \"DilutedEPSTTM\": \"6.35\",\n" +
                "    \"QuarterlyEarningsGrowthYOY\": \"0.696\",\n" +
                "    \"QuarterlyRevenueGrowthYOY\": \"0.692\",\n" +
                "    \"AnalystTargetPrice\": \"144.16\",\n" +
                "    \"TrailingPE\": \"21.91\",\n" +
                "    \"ForwardPE\": \"13.97\",\n" +
                "    \"PriceToSalesRatioTTM\": \"2.174\",\n" +
                "    \"PriceToBookRatio\": \"6.53\",\n" +
                "    \"EVToRevenue\": \"3.185\",\n" +
                "    \"EVToEBITDA\": \"14.6\",\n" +
                "    \"Beta\": \"1.105\",\n" +
                "    \"52WeekHigh\": \"141.88\",\n" +
                "    \"52WeekLow\": \"113.2\",\n" +
                "    \"50DayMovingAverage\": \"128.12\",\n" +
                "    \"200DayMovingAverage\": \"129.74\",\n" +
                "    \"SharesOutstanding\": \"896320000\",\n" +
                "    \"DividendDate\": \"2022-03-10\",\n" +
                "    \"ExDividendDate\": \"2022-02-10\"\n" +
                "}\n";

        Gson gson = new Gson();
        CompanyOverView companyOverView = gson.fromJson(json, CompanyOverView.class);

        return companyOverView;
    }

    /**
     * Returns the information for the cash flow reports
     * @param symbol The stock that the chart information is being found for
     * @return A class containing the cash flow report information
     */

    @Override
    public CashFlowReports cashflowReports(String symbol) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String json = "{\n" +
                "    \"symbol\": \"IBM\",\n" +
                "    \"annualReports\": [\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2021-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"12796000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"2655000000\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"85000000\",\n" +
                "            \"changeInOperatingAssets\": \"-1510000000\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"6417000000\",\n" +
                "            \"capitalExpenditures\": \"2062000000\",\n" +
                "            \"changeInReceivables\": \"-1372000000\",\n" +
                "            \"changeInInventory\": \"-138000000\",\n" +
                "            \"profitLoss\": \"5743000000\",\n" +
                "            \"cashflowFromInvestment\": \"-5975000000\",\n" +
                "            \"cashflowFromFinancing\": \"-13354000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-40000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"None\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"None\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"5869000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"5869000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"522000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-319000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-6533000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"5743000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2020-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"18197000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"3406000000\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"138000000\",\n" +
                "            \"changeInOperatingAssets\": \"-5088000000\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"6695000000\",\n" +
                "            \"capitalExpenditures\": \"2618000000\",\n" +
                "            \"changeInReceivables\": \"-5297000000\",\n" +
                "            \"changeInInventory\": \"209000000\",\n" +
                "            \"profitLoss\": \"5590000000\",\n" +
                "            \"cashflowFromInvestment\": \"-3028000000\",\n" +
                "            \"cashflowFromFinancing\": \"-9721000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-853000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"None\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"None\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"5797000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"5797000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"10504000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-302000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"5448000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"5590000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2019-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"14770000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"3234000000\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"-503000000\",\n" +
                "            \"changeInOperatingAssets\": \"-569000000\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"6059000000\",\n" +
                "            \"capitalExpenditures\": \"2286000000\",\n" +
                "            \"changeInReceivables\": \"-502000000\",\n" +
                "            \"changeInInventory\": \"-67000000\",\n" +
                "            \"profitLoss\": \"9431000000\",\n" +
                "            \"cashflowFromInvestment\": \"-26936000000\",\n" +
                "            \"cashflowFromFinancing\": \"9042000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-2597000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"1361000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"1361000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"5707000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"5707000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"31825000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-1361000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-3124000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"9431000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2018-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"15247000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"1423000000\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"126000000\",\n" +
                "            \"changeInOperatingAssets\": \"-879000000\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"4480000000\",\n" +
                "            \"capitalExpenditures\": \"3395000000\",\n" +
                "            \"changeInReceivables\": \"-1006000000\",\n" +
                "            \"changeInInventory\": \"127000000\",\n" +
                "            \"profitLoss\": \"8728000000\",\n" +
                "            \"cashflowFromInvestment\": \"-4913000000\",\n" +
                "            \"cashflowFromFinancing\": \"-10469000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"1341000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"4443000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"4443000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"5666000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"5666000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"6891000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-4443000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-135000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"8728000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2017-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"16724000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"1208000000\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"47000000\",\n" +
                "            \"changeInOperatingAssets\": \"-1315000000\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"4541000000\",\n" +
                "            \"capitalExpenditures\": \"3229000000\",\n" +
                "            \"changeInReceivables\": \"-1297000000\",\n" +
                "            \"changeInInventory\": \"-18000000\",\n" +
                "            \"profitLoss\": \"5753000000\",\n" +
                "            \"cashflowFromInvestment\": \"-7081000000\",\n" +
                "            \"cashflowFromFinancing\": \"-6418000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"620000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"4340000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"4340000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"5506000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"5506000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"9643000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-4165000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"4146000000\",\n" +
                "            \"changeInExchangeRate\": \"937000000\",\n" +
                "            \"netIncome\": \"5753000000\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"quarterlyReports\": [\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2021-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"2544000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1381000000\",\n" +
                "            \"capitalExpenditures\": \"450000000\",\n" +
                "            \"changeInReceivables\": \"1372000000\",\n" +
                "            \"changeInInventory\": \"138000000\",\n" +
                "            \"profitLoss\": \"2333000000\",\n" +
                "            \"cashflowFromInvestment\": \"-675000000\",\n" +
                "            \"cashflowFromFinancing\": \"-2692000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-880000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1474000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1474000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"128000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-823000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"2333000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2021-09-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"2713000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1684000000\",\n" +
                "            \"capitalExpenditures\": \"558000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"138000000\",\n" +
                "            \"profitLoss\": \"1130000000\",\n" +
                "            \"cashflowFromInvestment\": \"-629000000\",\n" +
                "            \"cashflowFromFinancing\": \"-1748000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"908000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1471000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1471000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"151000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"1130000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1130000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2021-06-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"2625000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1680000000\",\n" +
                "            \"capitalExpenditures\": \"560000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"138000000\",\n" +
                "            \"profitLoss\": \"1325000000\",\n" +
                "            \"cashflowFromInvestment\": \"-2671000000\",\n" +
                "            \"cashflowFromFinancing\": \"-3131000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"21000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1467000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1467000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"192000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"1325000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1325000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2021-03-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"4914000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1672000000\",\n" +
                "            \"capitalExpenditures\": \"494000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"138000000\",\n" +
                "            \"profitLoss\": \"955000000\",\n" +
                "            \"cashflowFromInvestment\": \"-2000000000\",\n" +
                "            \"cashflowFromFinancing\": \"-5783000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-89000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1457000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1457000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"51000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-2869000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"955000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2020-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"5860000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1699000000\",\n" +
                "            \"capitalExpenditures\": \"678000000\",\n" +
                "            \"changeInReceivables\": \"5297000000\",\n" +
                "            \"changeInInventory\": \"-209000000\",\n" +
                "            \"profitLoss\": \"1356000000\",\n" +
                "            \"cashflowFromInvestment\": \"-558000000\",\n" +
                "            \"cashflowFromFinancing\": \"-6293000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-386000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1454000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1454000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"167000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-4000000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1356000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2020-09-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"4285000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1683000000\",\n" +
                "            \"capitalExpenditures\": \"725000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"-209000000\",\n" +
                "            \"profitLoss\": \"1698000000\",\n" +
                "            \"cashflowFromInvestment\": \"-332000000\",\n" +
                "            \"cashflowFromFinancing\": \"-1689000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-18000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1453000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1453000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"147000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"1698000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1698000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2020-06-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"3576000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1679000000\",\n" +
                "            \"capitalExpenditures\": \"585000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"-209000000\",\n" +
                "            \"profitLoss\": \"1361000000\",\n" +
                "            \"cashflowFromInvestment\": \"-1236000000\",\n" +
                "            \"cashflowFromFinancing\": \"-1624000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-1035000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1450000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1450000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"4135000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"1361000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1361000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2020-03-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"4476000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1634000000\",\n" +
                "            \"capitalExpenditures\": \"630000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"-209000000\",\n" +
                "            \"profitLoss\": \"1175000000\",\n" +
                "            \"cashflowFromInvestment\": \"-902000000\",\n" +
                "            \"cashflowFromFinancing\": \"-115000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"586000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1440000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1440000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"6055000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"3459000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1175000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2019-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"3451000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"2073000000\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1650000000\",\n" +
                "            \"capitalExpenditures\": \"576000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"67000000\",\n" +
                "            \"profitLoss\": \"3670000000\",\n" +
                "            \"cashflowFromInvestment\": \"128000000\",\n" +
                "            \"cashflowFromFinancing\": \"-5675000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-457000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"0\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1438000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1438000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"329000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"0\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-2096000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"3670000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2019-09-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"3619000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"395000000\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1669000000\",\n" +
                "            \"capitalExpenditures\": \"588000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"67000000\",\n" +
                "            \"profitLoss\": \"1672000000\",\n" +
                "            \"cashflowFromInvestment\": \"-30373000000\",\n" +
                "            \"cashflowFromFinancing\": \"-8177000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-6733000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"125000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"125000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1436000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1436000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"247000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-125000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"1672000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1672000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2019-06-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"2941000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"307000000\",\n" +
                "            \"capitalExpenditures\": \"583000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"67000000\",\n" +
                "            \"profitLoss\": \"2498000000\",\n" +
                "            \"cashflowFromInvestment\": \"4162000000\",\n" +
                "            \"cashflowFromFinancing\": \"21031000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"4572000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"316000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"316000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1436000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1436000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"25270000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-316000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"2498000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"2498000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2019-03-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"4759000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1446000000\",\n" +
                "            \"capitalExpenditures\": \"539000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"67000000\",\n" +
                "            \"profitLoss\": \"1591000000\",\n" +
                "            \"cashflowFromInvestment\": \"-853000000\",\n" +
                "            \"cashflowFromFinancing\": \"1863000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"21000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"920000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"920000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1397000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1397000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"5979000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-920000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"5769000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1591000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2018-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"4119000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1112000000\",\n" +
                "            \"capitalExpenditures\": \"782000000\",\n" +
                "            \"changeInReceivables\": \"1006000000\",\n" +
                "            \"changeInInventory\": \"-127000000\",\n" +
                "            \"profitLoss\": \"1951000000\",\n" +
                "            \"cashflowFromInvestment\": \"455000000\",\n" +
                "            \"cashflowFromFinancing\": \"-4605000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"965000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"2050000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"2050000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1416000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1416000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"2177000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-2050000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-31000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1951000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2018-09-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"4232000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"348000000\",\n" +
                "            \"capitalExpenditures\": \"812000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"-127000000\",\n" +
                "            \"profitLoss\": \"2694000000\",\n" +
                "            \"cashflowFromInvestment\": \"-2969000000\",\n" +
                "            \"cashflowFromFinancing\": \"-436000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-21000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"626000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"626000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1431000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1431000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"2208000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-626000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"2692000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"2694000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2018-06-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"2294000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"342000000\",\n" +
                "            \"capitalExpenditures\": \"931000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"-127000000\",\n" +
                "            \"profitLoss\": \"2404000000\",\n" +
                "            \"cashflowFromInvestment\": \"-635000000\",\n" +
                "            \"cashflowFromFinancing\": \"-2519000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-15000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"990000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"990000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1437000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1437000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"336000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-990000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"2403000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"2404000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2018-03-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"4602000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1114000000\",\n" +
                "            \"capitalExpenditures\": \"870000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"-127000000\",\n" +
                "            \"profitLoss\": \"1679000000\",\n" +
                "            \"cashflowFromInvestment\": \"-1764000000\",\n" +
                "            \"cashflowFromFinancing\": \"-2909000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"412000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"777000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"777000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1382000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1382000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"2170000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-777000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-71000000\",\n" +
                "            \"changeInExchangeRate\": \"None\",\n" +
                "            \"netIncome\": \"1679000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2017-12-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"5733000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"653000000\",\n" +
                "            \"capitalExpenditures\": \"956000000\",\n" +
                "            \"changeInReceivables\": \"1297000000\",\n" +
                "            \"changeInInventory\": \"18000000\",\n" +
                "            \"profitLoss\": \"-1054000000\",\n" +
                "            \"cashflowFromInvestment\": \"-3810000000\",\n" +
                "            \"cashflowFromFinancing\": \"-919000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"1414000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"666000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"666000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1387000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1387000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"288000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-628000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"1057000000\",\n" +
                "            \"changeInExchangeRate\": \"62000000\",\n" +
                "            \"netIncome\": \"-1054000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2017-09-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"3570000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"383000000\",\n" +
                "            \"capitalExpenditures\": \"848000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"None\",\n" +
                "            \"profitLoss\": \"2725000000\",\n" +
                "            \"cashflowFromInvestment\": \"-1906000000\",\n" +
                "            \"cashflowFromFinancing\": \"-2756000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"179000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"949000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"949000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1395000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1395000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"3520000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-909000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"-781000000\",\n" +
                "            \"changeInExchangeRate\": \"328000000\",\n" +
                "            \"netIncome\": \"2726000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2017-06-30\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"3466000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"388000000\",\n" +
                "            \"capitalExpenditures\": \"685000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"None\",\n" +
                "            \"profitLoss\": \"2332000000\",\n" +
                "            \"cashflowFromInvestment\": \"-1668000000\",\n" +
                "            \"cashflowFromFinancing\": \"-609000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-93000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"1432000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"1432000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1403000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1403000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"2948000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-1389000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"1600000000\",\n" +
                "            \"changeInExchangeRate\": \"447000000\",\n" +
                "            \"netIncome\": \"2331000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"fiscalDateEnding\": \"2017-03-31\",\n" +
                "            \"reportedCurrency\": \"USD\",\n" +
                "            \"operatingCashflow\": \"3955000000\",\n" +
                "            \"paymentsForOperatingActivities\": \"None\",\n" +
                "            \"proceedsFromOperatingActivities\": \"None\",\n" +
                "            \"changeInOperatingLiabilities\": \"None\",\n" +
                "            \"changeInOperatingAssets\": \"None\",\n" +
                "            \"depreciationDepletionAndAmortization\": \"1257000000\",\n" +
                "            \"capitalExpenditures\": \"740000000\",\n" +
                "            \"changeInReceivables\": \"None\",\n" +
                "            \"changeInInventory\": \"None\",\n" +
                "            \"profitLoss\": \"1750000000\",\n" +
                "            \"cashflowFromInvestment\": \"303000000\",\n" +
                "            \"cashflowFromFinancing\": \"-2134000000\",\n" +
                "            \"proceedsFromRepaymentsOfShortTermDebt\": \"-880000000\",\n" +
                "            \"paymentsForRepurchaseOfCommonStock\": \"1293000000\",\n" +
                "            \"paymentsForRepurchaseOfEquity\": \"1293000000\",\n" +
                "            \"paymentsForRepurchaseOfPreferredStock\": \"None\",\n" +
                "            \"dividendPayout\": \"1321000000\",\n" +
                "            \"dividendPayoutCommonStock\": \"1321000000\",\n" +
                "            \"dividendPayoutPreferredStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfCommonStock\": \"None\",\n" +
                "            \"proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet\": \"2887000000\",\n" +
                "            \"proceedsFromIssuanceOfPreferredStock\": \"None\",\n" +
                "            \"proceedsFromRepurchaseOfEquity\": \"-1239000000\",\n" +
                "            \"proceedsFromSaleOfTreasuryStock\": \"None\",\n" +
                "            \"changeInCashAndCashEquivalents\": \"2270000000\",\n" +
                "            \"changeInExchangeRate\": \"100000000\",\n" +
                "            \"netIncome\": \"1750000000\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Gson gson = new Gson();
        CashFlowReports cashFlowReports = gson.fromJson(json, CashFlowReports.class);
        return cashFlowReports;

    }
}
