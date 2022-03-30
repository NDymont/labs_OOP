package calculator;

public class OperationNotFoundException extends Exception {
    public OperationNotFoundException(String nameOperation) {
        super("There is no \"" + nameOperation + "\" operation");
    }
}
