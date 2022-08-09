package model.api;

import com.google.gson.Gson;
import model.gsondatabase.CashFlowReports;
import model.gsondatabase.CompanyOverView;
import model.gsondatabase.ListOfBestMatches;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * This class creates and manages connections to the input api, and returns required information
 */
public class OnlineInput implements InputApi {
    private final String key = System.getenv("INPUT_API_KEY");
    /**
     * Returns a list of best matches
     * @param input The symbol of the stock
     * @return A list of best matches class
     */
    @Override
    public ListOfBestMatches bestMatches(String input){
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=" + input + "&apikey=" + key))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            ListOfBestMatches bestMatches = gson.fromJson(response.body(), ListOfBestMatches.class);

            return bestMatches;



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    /**
     * Returns the company overview information
     * @param symbol A stock ticker that is selected
     * @return A class containing company overview information
     */
    @Override
    public CompanyOverView companyOverView(String symbol){
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + key))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() < 200 ||response.statusCode() > 399) {
                return null;
            }
            Gson gson = new Gson();
            CompanyOverView companyOverView = gson.fromJson(response.body(), CompanyOverView.class);

            return companyOverView;



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    /**
     * Returns the information for the cash flow reports
     * @param symbol The stock that the chart information is being found for
     * @return A class containing the cash flow report information
     */
    @Override
    public CashFlowReports cashflowReports(String symbol) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("https://www.alphavantage.co/query?function=CASH_FLOW&symbol=" + symbol + "&apikey=" + key))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            CashFlowReports cashFlowReports = gson.fromJson(response.body(), CashFlowReports.class);
            return cashFlowReports;



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
}

