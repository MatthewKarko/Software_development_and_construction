package model.api;

/**
 * This class is responsible for connecting to the output API
 */
public interface OutputAPI {
    /**
     * Sends a report to the pastebin API
     * @param content The content that is to be included in the API
     * @return A string as to whether or not it was successful
     */
    String sendReport(String content);
}
