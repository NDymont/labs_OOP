package calculator;

public class DivisionByZeroException extends InvalidArithmeticOperationException {
    public DivisionByZeroException() {
        super("Division by zero is not possible");
    }
}
