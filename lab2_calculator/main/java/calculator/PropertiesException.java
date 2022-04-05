package calculator;

public class PropertiesException extends InitializationException{

    public PropertiesException(String message) {
        super(message);
    }

    public PropertiesException(String message, Exception exception) {
        super(message, exception);
    }
}
