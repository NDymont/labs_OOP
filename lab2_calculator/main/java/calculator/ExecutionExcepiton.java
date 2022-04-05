package calculator;

public class ExecutionExcepiton extends Exception{
    public ExecutionExcepiton(String message) {
        super(message);
    }

    public ExecutionExcepiton(Exception exception) {
        super(exception);
    }
}
