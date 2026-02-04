/**
 * Custom exception class for Blue task management chatbot errors.
 * <p>
 * This exception wraps all application-specific errors including invalid user input,
 * corrupted storage data, file I/O failures, and unrecognized commands. It provides
 * a consistent error handling mechanism throughout the application with user-friendly
 * error messages.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
package blue.exceptions;

public class BlueException extends Exception {

    /**
     * Constructs a new BlueException with the specified error message.
     *
     * @param message The detail message describing the error.
     */
    public BlueException(String message) {
        super(message);
    }
}
