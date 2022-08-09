package model.gsondatabase;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * This class is responsible for storing the cash flow report information from the input API
 */
public class CashFlowReports {
    ArrayList<Report> quarterlyReports;
    @SerializedName("Error Message")
    String errorMessage;
    String Note;

    /**
     * Returns the Error message
     * @return A string containing the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the note
     * @return A string containing the note
     */
    public String getNote(){
        return Note;
    }


    /**
     * Returns an arraylist of the quarterly reports
     * @return An arraylist containing the quarterly reports
     */

    public ArrayList<Report> getQuarterlyReports() {
        return quarterlyReports;
    }


}
