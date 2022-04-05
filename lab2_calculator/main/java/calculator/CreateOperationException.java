package calculator;

public class CreateOperationException extends ExecutionExcepiton {
    CreateOperationException(String message) {
        super(message);
    }

    CreateOperationException(Exception exception) {
        super(exception);
    }
}
