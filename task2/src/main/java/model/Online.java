package model;

import GsonDataBase.*;
import GsonDataBase.Error;
import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Online implements API{
    private String token;
    private String returnString;
    private String shipID;
    private String errorMessage;

    @Override
    public String getServerStatus(){
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/game/status"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("1");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            Gson gson = new Gson();
            ServerStatus status = gson.fromJson(response.body(), ServerStatus.class);
            returnString = status.getServerStatus();



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }

        return returnString;
    }
    @Override
    public String claimUsername(String username) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/users/" + username + "/claim"))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("2");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return error.getErrorMessage();
            }
            Gson gson = new Gson();
            claimUsername user = gson.fromJson(response.body(), claimUsername.class);
            token = user.getToken();
            returnString = "Your token is: " + token;

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return returnString;
    }

    @Override
    public UserAcc getAccountInfo() {
        try {
            System.out.println(token);
            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/account?token=" + token))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("3");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());

            if(response.statusCode() < 200 ||response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }
            Gson gson = new Gson();
            AccountInfo accountInfo = gson.fromJson(response.body(), AccountInfo.class);
            return accountInfo.getUserAcc();



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }

    @Override
    public List<Loan> getListofLoans() {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/types/loans?token=" + token))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("4");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }
            Gson gson = new Gson();
            LoanInfo loanInfo = gson.fromJson(response.body(), LoanInfo.class);
            return loanInfo.getLoans();



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    @Override
    public AcceptedLoanDetails takeLoan(String type) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/loans?token=" + token + "&type=" + type))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("5");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() < 200 ||response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }
            Gson gson = new Gson();
            AcceptedLoan takenLoan = gson.fromJson(response.body(), AcceptedLoan.class);
            return takenLoan.getLoan();

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    @Override
    public List<AcceptedLoanDetails> getListOfCurrentLoans() {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/loans?token=" + token))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("6");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() < 200 ||response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }
            Gson gson = new Gson();
            CurrentLoans loans = gson.fromJson(response.body(), CurrentLoans.class);

            return loans.getLoans();



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }

    @Override
    public List<ShipListing> getListOfShips(String className) {
        String url;
        if (className.equals("")) {
            url = "https://api.spacetraders.io/systems/OE/ship-listings?token=" + token;
        }
        else{
            url = "https://api.spacetraders.io/systems/OE/ship-listings?token=" + token + "&class=" + className;
        }
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI(url))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("7");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() < 200 ||response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }

            Gson gson = new Gson();
            ListOfShipListings listOfShipListings = gson.fromJson(response.body(), ListOfShipListings.class);

            return Arrays.asList(listOfShipListings.getShipListings());



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    @Override
    public ShipDetails purchaseShip(String location, String type) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/ships?token=" + token +"&location=" + location +  "&type=" + type))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("8");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }
            Gson gson = new Gson();
            ShipDetails shipDetails = gson.fromJson(response.body(), ShipDetails.class);
            shipID = shipDetails.getShip().getId();
            return shipDetails;

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    @Override
    public List<Ship> getListOfCurrentShips() {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/ships?token=" + token))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("9");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }

            Gson gson = new Gson();
            MyShips ships = gson.fromJson(response.body(), MyShips.class);
            return Arrays.asList(ships.getShips());

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    @Override
    public PurchaseGood purchaseShipFuel(String shipID, String quantity) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/purchase-orders?token=" + token + "&shipId=" + shipID + "&good=FUEL" + "&quantity=" + quantity ))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("10");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());

            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }
            Gson gson = new Gson();
            PurchaseGood purchaseFuel = gson.fromJson(response.body(), PurchaseGood.class);
            return purchaseFuel;

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }

    public ArrayList<Good> getMarketplaceListings(String location) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/locations/" + location + "/marketplace?token=" + token))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("11");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }

            Gson gson = new Gson();
            Marketplace marketplace = gson.fromJson(response.body(), Marketplace.class);
            ArrayList<Good> a1 = new ArrayList<>(Arrays.asList(marketplace.getGoods()));
            return a1;

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }

    @Override
    public PurchaseGood purchaseGoods(String shipID, String good, String quantity) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/purchase-orders?token=" + token + "&shipId=" + shipID + "&good=" + good + "&quantity=" + quantity ))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("12");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }
            Gson gson = new Gson();
            PurchaseGood purchaseGood = gson.fromJson(response.body(), PurchaseGood.class);
            return purchaseGood;

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }

    @Override
    public ArrayList<Location> getListOfLocations(String type) {
        String url;
        if (type.equals("")) {
            url = "https://api.spacetraders.io/systems/OE/locations?token=" + token;
        }
        else{
            url = "https://api.spacetraders.io/systems/OE/locations?token=" + token + "&type=" + type;
        }
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI(url))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("13");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }


            Gson gson = new Gson();
            ListOfLocations locations = gson.fromJson(response.body(), ListOfLocations.class);
            ArrayList<Location> a1 = new ArrayList<>(Arrays.asList(locations.getLocations()));
            return a1;



        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    @Override
    public FlightPlan createFlightPLan(String shipID, String destination) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/flight-plans?token=" + token + "&shipId=" + shipID + "&destination=" + destination))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("14");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() < 200 ||response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }

            Gson gson = new Gson();
            FlightList flightList = gson.fromJson(response.body(), FlightList.class);
            return flightList.getFlightPlan();

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }

    @Override
    public FlightPlan viewFlightPlan(String flightPlanId) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/flight-plans/" + flightPlanId + "/" + "?token=" + token))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("15");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() < 200 ||response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }

            Gson gson = new Gson();
            FlightList flightList = gson.fromJson(response.body(), FlightList.class);
            return flightList.getFlightPlan();

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }
    @Override
    public PurchaseGood sellGoods(String shipID, String good, String quantity) {
        try {

            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.spacetraders.io/my/sell-orders?token=" + token + "&shipId=" + shipID + "&good=" + good + "&quantity=" + quantity ))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("16");
            System.out.println("Response status code was: " + response.statusCode());
            System.out.println("Response headers were: " + response.headers());
            System.out.println("Response body was:" + response.body());
            if(response.statusCode() > 399) {
                Gson gson = new Gson();
                Error error = gson.fromJson(response.body(), Error.class);
                this.errorMessage = error.getErrorMessage();
                return null;
            }
            Gson gson = new Gson();
            PurchaseGood purchaseGood = gson.fromJson(response.body(), PurchaseGood.class);
            return purchaseGood;

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
            return null;
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return null;
    }




    public String getToken(){
        return this.token;
    }

    public String setToken(String token){
        this.token = token;
        if(getAccountInfo() == null){
            return getErrorMessage();
        }
        return this.token;
    }

    @Override
    public String getErrorMessage(){
        return errorMessage + "\n";
    }


}

