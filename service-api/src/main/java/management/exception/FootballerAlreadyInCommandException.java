package management.exception;

/**
 * Exception throws if footballer already in command.
 */
public class FootballerAlreadyInCommandException extends RuntimeException {
    /**
     * Constructor with message.
     *
     * @param message that will be throw
     */
    public FootballerAlreadyInCommandException(String message) {
        super(message);
    }
}
