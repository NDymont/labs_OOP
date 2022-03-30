package calculator.operations;

import calculator.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SquareRootExtraction implements Operation {
    private static final Logger log = Logger.getLogger(SquareRootExtraction.class.getName());

    @Override
    public int getNumberOfArguments() {
        return 0;
    }

    @Override
    public int getNumberOfRequiredValues() {
        return 1;
    }

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws ExtractingRootFromNegativeException, IncorrectNumberOfArgumentsException, NotEnoughDataInStackException {
        ArgumentCountChecker argumentCountChecker = new ArgumentCountChecker();
        argumentCountChecker.checkNumberOfArguments(arguments.size(), getNumberOfArguments(), this.getClass().getSimpleName());
        argumentCountChecker.checkNumberOfValues(contextCalculator.getSizeOfStack(), getNumberOfRequiredValues(), this.getClass().getSimpleName());

        double value = contextCalculator.pop();
        if (value < 0) {
            contextCalculator.push(value);
            ExtractingRootFromNegativeException exception = new ExtractingRootFromNegativeException();
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
        double sqrtValue = Math.sqrt(value);
        contextCalculator.push(sqrtValue);
        log.log(Level.INFO, String.format("sqrt(%s) = %s", value, sqrtValue));
    }
}
