package calculator;

public class ExtractingRootFromNegativeException extends Exception {
    public ExtractingRootFromNegativeException() {
        super("Extracting the root from a negative number is not possible");
    }
}