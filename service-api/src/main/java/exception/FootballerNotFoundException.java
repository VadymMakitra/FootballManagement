package exception;

/**
 * Exception throws if footballer not found;
 */
public class FootballerNotFoundException extends RuntimeException {
    /**
     * Constructor with message.
     * @param message - exception text
     */
    public FootballerNotFoundException(String message) {
        super(message);
    }
}
