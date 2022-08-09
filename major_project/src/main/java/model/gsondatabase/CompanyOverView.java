package model.gsondatabase;
import com.google.gson.annotations.SerializedName;

/**
 * This class is responsible for storing the Company overview Information from the input API
 */
public class CompanyOverView {
    String Symbol;
    String AssetType;
    String Name;
    String Description;
    String CIK;
    String Exchange;
    String Currency;
    String Sector;
    String Industry;
    String Address;
    String FiscalYearEnd;
    String LatestQuarter;
    String MarketCapitalization;
    String EBITDA;
    @SerializedName("Error Message")
    String errorMessage;
    String Note;

    /**
     * Returns an error message
     * @return A string containing an error message
     */

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the a note if there are too many API connections
     * @return A string containing the note
     */
    public String getNote(){
        return Note;
    }

    /**
     * Returns the symbol of the stock
     * @return A string containing the symbol
     */

    public String getSymbol() {
        return Symbol;
    }

    /**
     * Returns the name for the stock
     * @return A string containing the name of the stock
     */

    public String getName() {
        return Name;
    }

    /**
     * Returns the description for the stock
     * @return A string containing the description
     */
    public String getDescription() {
        return Description;
    }


}
