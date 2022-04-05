package calculator.operations;

import calculator.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SquareRootExtraction implements Operation {
    private static final Logger log = Logger.getLogger(SquareRootExtraction.class.getName());

    private final int numberOfArguments = 0;
    private final int numberOfRequiredValues = 1;

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws ExtractingRootFromNegativeException, IncorrectNumberOfArgumentsException, NotEnoughDataInStackException {

        ArgumentCountChecker.checkNumberOfArguments(arguments.size(), numberOfArguments, this.getClass().getSimpleName());
        ArgumentCountChecker.checkNumberOfValues(contextCalculator.getSizeOfStack(), numberOfRequiredValues, this.getClass().getSimpleName());

        double value = contextCalculator.pop();
        if (value < 0) {
            contextCalculator.push(value);
            ExtractingRootFromNegativeException exception = new ExtractingRootFromNegativeException();
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
        double sqrtValue = Math.sqrt(value);
        contextCalculator.push(sqrtValue);
        if (log.isLoggable(Level.INFO)) {
            log.log(Level.INFO, String.format("sqrt(%s) = %s", value, sqrtValue));
        }
    }
}
