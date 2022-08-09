package model.api;

/**
 * This class emulates the output api locally
 */
public class OfflineOutput implements OutputAPI{
    /**
     * Returns a string with the report contents
     * @param content The content that is to be included in the API
     * @return A link to the report content
     */
    @Override
    public String sendReport(String content) {
        return "https://pastebin.com/SkZqRdG8";
    }
}
