package home.intexsoft.bank_application.exceptions;

public class EntityRetrievalException extends RuntimeException {

    public EntityRetrievalException() {
    }

    public EntityRetrievalException(String message) {
        super(message);
    }

    public EntityRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityRetrievalException(Throwable cause) {
        super(cause);
    }

    public EntityRetrievalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
