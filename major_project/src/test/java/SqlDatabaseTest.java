
import model.SqlDatabase;
import model.api.InputApi;
import model.contactapi.UserFacade;
import model.contactapi.UserFacadeImpl;
import model.api.OutputAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SqlDatabaseTest {
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
    public void testGetCachedInfo(){
        when(database.getStockInfo("IBM")).thenReturn(testReturnStrings.DatabaseInfo());
        assertNotNull(facade.companyOverview("IBM", true));
        verify(inputAPI, never()).companyOverView(anyString());
    }

    @Test
    public void testAddCacheStorage(){
        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        facade.companyOverview("IBM", true);
        verify(database, times(1)).insertStockInfo(anyString(),anyString(),anyString());
    }

    @Test
    public void testAddThenGetCachedInfo(){
        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        when(database.getStockInfo("IBM")).thenReturn(new ArrayList<>());
        facade.companyOverview("IBM", true);
        when(database.getStockInfo("IBM")).thenReturn(testReturnStrings.DatabaseInfo());
        facade.companyOverview("IBM",true);
        verify(inputAPI, times(1)).companyOverView(anyString());
    }
    @Test
    public void testCachedInfoFacade(){
        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        when(database.getStockInfo("IBM")).thenReturn(testReturnStrings.DatabaseInfo());
        facade.companyOverview("IBM", true);
        assertTrue(facade.checkCachedData("IBM"));

    }

    @Test
    public void testDeleteCachedData(){
        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        facade.companyOverview("IBM", true);
        facade.removeCachedData();
        assertEquals(0, database.getStockInfo(anyString()).size());
    }

    @Test
    public void testAddTwoCachesToData(){
        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        when(inputAPI.companyOverView("SAIC")).thenReturn(testReturnStrings.companyOverView("SAIC"));
        facade.companyOverview("IBM",true);
        facade.companyOverview("SAIC",true);
        verify(database,times(2)).insertStockInfo(anyString(),anyString(),anyString());

    }

    @Test
    public void testAddCacheThenDeleteCacheThenAdd(){
        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        when(inputAPI.companyOverView("SAIC")).thenReturn(testReturnStrings.companyOverView("SAIC"));
        facade.companyOverview("IBM",true);
        verify(database,times(1)).insertStockInfo(anyString(),anyString(),anyString());
        facade.removeCachedData();
        verify(database,times(1)).removeCachedData();
        facade.companyOverview("SAIC",true);
        verify(database,times(2)).insertStockInfo(anyString(),anyString(),anyString());

    }

    @Test
    public void testEveryFunctionWithCachedData(){
        when(inputAPI.bestMatches("IBM")).thenReturn(testReturnStrings.positiveMatches());
        assertEquals(10, facade.getBestMatches("IBM").size());
        verify(inputAPI,times(1)).bestMatches("IBM");

        when(database.getStockInfo("IBM")).thenReturn(testReturnStrings.DatabaseInfo());
        assertNotNull(facade.companyOverview("IBM", true));
        verify(inputAPI,never()).companyOverView("IBM");

        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        assertEquals("", facade.setCashFlowReport("IBM"));
        verify(inputAPI,times(1)).cashflowReports("IBM");

        when(outputAPI.sendReport(anyString())).thenReturn("https://pastebin.com/SkZqRdG8");
        assertNotNull(facade.sendReport("IBM"));
        verify(outputAPI, times(1)).sendReport(anyString());

    }














}
