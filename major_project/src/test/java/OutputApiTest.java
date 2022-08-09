import model.SqlDatabase;
import model.api.InputApi;
import model.api.OutputAPI;
import model.contactapi.UserFacade;
import model.contactapi.UserFacadeImpl;
import model.gsondatabase.CashFlowReports;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class OutputApiTest {
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
    public void testSendReportNoSymbol(){
        String actual = facade.sendReport("");
        assertNotNull(actual);
        assertEquals("Error, No current stock is selected", actual);
        verify(outputAPI, never()).sendReport(anyString());
    }

    @Test
    public void testSendReport(){
        when(inputAPI.bestMatches("IBM")).thenReturn(testReturnStrings.positiveMatches());
        when(inputAPI.companyOverView("IBM")).thenReturn(testReturnStrings.companyOverView("IBM"));
        when(inputAPI.cashflowReports("IBM")).thenReturn(testReturnStrings.cashflowReports("IBM"));
        when(outputAPI.sendReport(anyString())).thenReturn("https://pastebin.com/SkZqRdG8");
        assertNotNull(facade.sendReport("IBM"));
        verify(outputAPI, times(1)).sendReport(anyString());

    }
    @Test
    public void testSendReportIncorrectSymbol(){
        when(inputAPI.bestMatches("random")).thenReturn(testReturnStrings.noBestMatches());
        when(inputAPI.companyOverView("random")).thenReturn(testReturnStrings.companyOverviewNoSymbol());
        when(inputAPI.cashflowReports("random")).thenReturn(new CashFlowReports());
//        when(outputAPI.sendReport(anyString())).thenReturn("Invalid Input, Stock does not exist.");
        assertEquals("Error, selected stock has no valid information on API.", facade.sendReport("random"));
        verify(outputAPI, never()).sendReport(anyString());
    }
}
