package management.exception;

/**
 * Exception that throws if command didn't have enough budget.
 */
public class NotEnoughBudgetException extends RuntimeException {
    /**
     * Constructor with exception message.
     *
     * @param message that throws Exception
     */
    public NotEnoughBudgetException(String message) {
        super(message);
    }
}
