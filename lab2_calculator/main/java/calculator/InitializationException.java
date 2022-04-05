package calculator;

import java.io.IOException;

public class InitializationException extends Exception{

    public InitializationException(String message) {
        super(message);
    }

    public InitializationException(String message, Exception exception) {
        super(message, exception);
    }

    public InitializationException(IOException e) {
        super(e);
    }
}
