import model.SqlDatabase;
import model.api.InputApi;
import model.api.OutputAPI;
import model.contactapi.UserFacade;
import model.contactapi.UserFacadeImpl;
import model.gsondatabase.CompanyOverView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class InputApiTest {
    InputApi inputAPI;
    OutputAPI outputAPI;
    UserFacade facade;
    SqlDatabase database;
    TestReturnStrings testReturnStrings = new TestReturnStrings();




    @BeforeEach
    void setup(){
        inputAPI = mock(InputApi.class);
        outputAPI = mock(OutputAPI.class);
        database = mock(SqlDatabase.class);
        facade = new UserFacadeImpl(inputAPI, outputAPI, database);
    }

    @Test
    public void testGetListofBestMatches(){
        when(inputAPI.bestMatches("IBM")).thenReturn(testReturnStrings.positiveMatches());
        assertEquals(10, facade.getBestMatches("IBM").size());
        verify(inputAPI,times(1)).bestMatches("IBM");
    }
    @Test
    public void testGetListofBestMatchesNoSymbol(){
        when(inputAPI.bestMatches("")).thenReturn(testReturnStrings.negativeMatches());
        assertEquals(1, facade.getBestMatches("").size());
        verify(inputAPI,times(1)).bestMatches("");
    }

    @Test
    public void testNoBestMatchesFound(){
        when(inputAPI.bestMatches("hkldfgnfglhj124")).thenReturn(testReturnStrings.noBestMatches());
        assertEquals(1, facade.getBestMatches("hkldfgnfglhj124").size());
        assertEquals("Could not find any matches", facade.getBestMatches("hkldfgnfglhj124").get(0));
        verify(inputAPI,times(2)).bestMatches("hkldfgnfglhj124");
    }

    @Test
    public void testCompanyOverview(){
        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        assertNotNull(facade.companyOverview("IBM", true));
        verify(inputAPI,times(1)).companyOverView("IBM");
    }
    @Test
    public void testCompanyOverviewNoSymbol(){
        when(inputAPI.companyOverView("")).thenReturn(testReturnStrings.companyOverviewNoSymbol());
        String overview = facade.companyOverview("", true);
        assertEquals("Invalid API call. Please retry or visit the documentation (https://www.alphavantage.co/documentation/) for OVERVIEW.",overview);
        verify(inputAPI,times(1)).companyOverView(anyString());
    }
    @Test
    public void testCompanyOverviewIncorrectSymbol(){
        when(inputAPI.companyOverView("random")).thenReturn(new CompanyOverView());
        String overview = facade.companyOverview("random", true);
        assertEquals("Stock information was not found, ticker selected does not have any information in API", overview);
        verify(inputAPI,times(1)).companyOverView(anyString());
    }

    @Test
    public void testCashFlowReports(){
        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        assertEquals("", facade.setCashFlowReport("IBM"));
        verify(inputAPI,times(1)).cashflowReports("IBM");
    }

    @Test
    public void testCashFlowReportsNoSymbol(){
        when(inputAPI.cashflowReports("")).thenReturn(testReturnStrings.cashflowReportsNoSymbol());
        assertNotNull(facade.setCashFlowReport(""));
        verify(inputAPI,times(1)).cashflowReports(anyString());
    }

    @Test
    public void testCashFlowReportsIncorrectSymbol(){
        when(inputAPI.cashflowReports("random")).thenReturn(testReturnStrings.cashflowReportsNoSymbol());
        assertNotNull(facade.setCashFlowReport("random"));
        verify(inputAPI,times(1)).cashflowReports(anyString());
    }

    @Test
    public void testCashFlowChartInfo() throws ParseException {
        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        assertEquals("", facade.setCashFlowReport("IBM"));
        assertNotNull(facade.getChartInfo("Operating Cash Flow"));
        assertNotNull(facade.getChartInfo("Capital Expenditures"));
        assertNotNull(facade.getChartInfo("Profit/Loss"));
        assertNotNull(facade.getChartInfo("Dividend Payout"));
        verify(inputAPI,times(1)).cashflowReports("IBM");

    }
    @Test
    public void testGetListofBestMatchesAndViewInfo(){
        when(inputAPI.bestMatches("IBM")).thenReturn(testReturnStrings.positiveMatches());
        assertEquals(10, facade.getBestMatches("IBM").size());
        verify(inputAPI,times(1)).bestMatches("IBM");

        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        assertNotNull(facade.companyOverview("IBM", true));
        verify(inputAPI,times(1)).companyOverView("IBM");
    }
    @Test
    public void testGetListOfBestMatchesThenCashFlowReports(){
        when(inputAPI.bestMatches("IBM")).thenReturn(testReturnStrings.positiveMatches());
        assertEquals(10, facade.getBestMatches("IBM").size());
        verify(inputAPI,times(1)).bestMatches("IBM");

        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        assertEquals("", facade.setCashFlowReport("IBM"));
        verify(inputAPI,times(1)).cashflowReports("IBM");
    }

    @Test
    public void testGetListOfBestMatchesThenSendReport(){
        when(inputAPI.bestMatches("IBM")).thenReturn(testReturnStrings.positiveMatches());
        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        assertEquals(10, facade.getBestMatches("IBM").size());
        verify(inputAPI,times(1)).bestMatches("IBM");

        when(outputAPI.sendReport(anyString())).thenReturn("https://pastebin.com/SkZqRdG8");
        assertNotNull(facade.sendReport("IBM"));
        verify(outputAPI, times(1)).sendReport(anyString());
    }

    @Test
    public void testEveryFunction(){
        when(inputAPI.bestMatches("IBM")).thenReturn(testReturnStrings.positiveMatches());
        assertEquals(10, facade.getBestMatches("IBM").size());
        verify(inputAPI,times(1)).bestMatches("IBM");

        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        assertNotNull(facade.companyOverview("IBM", true));
        verify(inputAPI,times(1)).companyOverView("IBM");

        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        assertEquals("", facade.setCashFlowReport("IBM"));
        verify(inputAPI,times(1)).cashflowReports("IBM");

        when(outputAPI.sendReport(anyString())).thenReturn("https://pastebin.com/SkZqRdG8");
        assertNotNull(facade.sendReport("IBM"));
        verify(outputAPI, times(1)).sendReport(anyString());
    }

    @Test
    public void testSetSavedInformation(){
        facade.setSavedChartInfo("test","CF","1998-01-01", 14.5);
        assertEquals("CF",facade.getSavedCurrentChart());
    }
    @Test
    public void testGetSavedChartInfo(){
        facade.setSavedChartInfo("test","CF","1998-01-01", 14.5);
        assertEquals("Symbol of saved chart data: test\nSaved chart data last value: 14.5 Billion(USD)\n",facade.getSavedChartInfo());

    }

    @Test
    public void testCheckSavedValue() throws ParseException {
        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        assertEquals("", facade.setCashFlowReport("IBM"));
        facade.getChartInfo("Capital Expenditures");
        facade.setSavedChartInfo("test","Capital Expenditures","1998-01-01", 14.5);

        assertEquals("", facade.checkSavedValue());
    }
    @Test
    public void testCheckSavedValuePositive() throws ParseException {
        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        assertEquals("", facade.setCashFlowReport("IBM"));
        facade.getChartInfo("Capital Expenditures");
        facade.setSavedChartInfo("test","Capital Expenditures","1998-01-01", 0.05);

        assertEquals("Capital Expenditures", facade.checkSavedValue());
    }



}
