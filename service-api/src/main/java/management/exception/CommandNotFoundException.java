package management.exception;

/**
 * Exception that will throw if command is not found by Id.
 */
public class CommandNotFoundException extends RuntimeException {
    /**
     * Constructor with parameters.
     *
     * @param message - exception message
     */
    public CommandNotFoundException(String message) {
        super(message);
    }
}
