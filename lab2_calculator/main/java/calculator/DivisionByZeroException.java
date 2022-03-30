package calculator;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super("Division by zero is not possible");
    }
}
