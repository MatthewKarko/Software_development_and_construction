package model.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * This class creates and maintains the connection to the output API
 */
public class OnlineOutput implements OutputAPI{
    private String key = System.getenv("PASTEBIN_API_KEY");
    private String returnString;
    /**
     * Returns a string with the report contents
     * @param content The content that is to be included in the API
     * @return Information on the report content
     */
    @Override
    public String sendReport(String content) {
        try {
            String body = "api_dev_key=" + key + "&api_option=paste" + "&api_paste_code=" + content;
            byte[] out = body.getBytes(StandardCharsets.UTF_8);
            HttpRequest request = HttpRequest.newBuilder(new URI("https://pastebin.com/api/api_post.php"))
                    .POST(HttpRequest.BodyPublishers.ofByteArray(out))
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            returnString = response.body();

        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
// This would mean our URI is incorrect - this is here because often the URI you use will not be (fully)
// hard-coded and so needs a way to be checked for correctness at runtime.
        }
        return returnString;
    }

}
