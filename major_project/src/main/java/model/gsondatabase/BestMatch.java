package model.gsondatabase;
import com.google.gson.annotations.SerializedName;

/**
 * This class is responsible for storing the best matches information from the input API
 */
public class BestMatch {
    @SerializedName("1. symbol")
    private String symbol;
    @SerializedName("2. name")
    private String name;
    @SerializedName("3. type")
    private String type;
    @SerializedName("4. region")
    private String region;
    @SerializedName("5. marketOpen")
    private String marketOpen;
    @SerializedName("6. marketClose")
    private String marketClose;
    @SerializedName("7. timezone")
    private String timezone;
    @SerializedName("8. currency")
    private String currency;
    @SerializedName("9. matchScore")
    private String matchScore;

    /**
     * Returns the symbol of the best match
     * @return A string containing a symbol
     */
    public String getSymbol() {
        return symbol;
    }




}
