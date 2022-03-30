package calculator;

public class IncorrectNumberOfArgumentsException extends Exception {
    public IncorrectNumberOfArgumentsException(String className, int numberOfArguments, int correctNumberOfArgument) {
        super(String.format("operation %s requires %d arguments, arguments submitted: %d",
                className, correctNumberOfArgument, numberOfArguments));
    }
}
