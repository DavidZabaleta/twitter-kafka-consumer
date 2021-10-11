package co.berako.events.validations;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
