package calculator;

public class OperationNotFoundException extends CreateOperationException {
    public OperationNotFoundException(String nameOperation) {
        super("There is no \"" + nameOperation + "\" operation");
    }
}
