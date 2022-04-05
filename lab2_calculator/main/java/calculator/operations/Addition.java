package calculator.operations;

import calculator.ArgumentCountChecker;
import calculator.ContextCalculator;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Addition implements Operation {

    private static final Logger log = Logger.getLogger(Addition.class.getName());

    private final int numberOfArguments = 0;
    private final int numberOfRequiredValues = 2;

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws IncorrectNumberOfArgumentsException, NotEnoughDataInStackException {

        ArgumentCountChecker.checkNumberOfArguments(arguments.size(), numberOfArguments, this.getClass().getSimpleName());
        ArgumentCountChecker.checkNumberOfValues(contextCalculator.getSizeOfStack(), numberOfRequiredValues, this.getClass().getSimpleName());
        double arg1 = contextCalculator.pop();
        double arg2 = contextCalculator.pop();
        double result = arg1 + arg2;
        contextCalculator.push(result);
        if (log.isLoggable(Level.INFO)) {
            log.log(Level.INFO, String.format("%s + %s = %s", arg1, arg2, result));
        }
    }
}
