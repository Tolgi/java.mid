package hr.abysalto.hiring.mid.exception;

public class ExternalClientException extends RuntimeException {

    public ExternalClientException(String message) {
        super(message);
    }

    public ExternalClientException(String message, Throwable cause) {
        super(message, cause);
    }
}