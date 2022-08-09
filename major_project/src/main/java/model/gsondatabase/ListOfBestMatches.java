package model.gsondatabase;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * This class is responsible for storing the list of the best matches from the input API
 */
public class ListOfBestMatches {
    ArrayList<BestMatch> bestMatches;
    @SerializedName("Error Message")
    String errorMessage;
    String Note;

    /**
     * Returns the error message
     * @return An string containing error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns a note if there is too many api calls
     * @return A string containing the note
     */

    public String getNote(){
        return Note;
    }

    /**
     * An array list containing best matches
     * @return An arraylist containing the best matches
     */
    public  ArrayList<BestMatch>  getBestMatches() {
        return bestMatches;
    }
}
