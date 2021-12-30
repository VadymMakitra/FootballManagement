package management.exception;

/**
 * Exception throws if footballer not found;
 */
public class FootballerNotFoundException extends RuntimeException {
    /**
     * Constructor with message.
     * @param message - management.exception text
     */
    public FootballerNotFoundException(String message) {
        super(message);
    }
}
