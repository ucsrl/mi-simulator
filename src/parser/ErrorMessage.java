package parser;

import gui.CONSTANTS;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorMessage.
 */
public class ErrorMessage {

    /** The message. */
    private String message = "";

    public void append(int zeile, String error) {
	message += CONSTANTS.ERROR_PARSER + " " + zeile + ": " + error + "\n";
    }

    /**
     * Append.
     * 
     * @param s
     *            the s
     */
    public void append(String s) {
	message += s + "\n";
    }

    /**
     * Gets the error message.
     * 
     * @return the error message
     */
    public String getErrorMessage() {
	return message;
    }
}
