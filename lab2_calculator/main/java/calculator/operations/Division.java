package calculator.operations;

import calculator.ArgumentCountChecker;
import calculator.ContextCalculator;
import calculator.DivisionByZeroException;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Division implements Operation {
    private static final Logger log = Logger.getLogger(Division.class.getName());

    private final int numberOfArguments = 0;

    private final int numberOfRequiredValues = 2;

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws DivisionByZeroException, IncorrectNumberOfArgumentsException, NotEnoughDataInStackException {

        ArgumentCountChecker.checkNumberOfArguments(arguments.size(), numberOfArguments, this.getClass().getSimpleName());
        ArgumentCountChecker.checkNumberOfValues(contextCalculator.getSizeOfStack(), numberOfRequiredValues, this.getClass().getSimpleName());

        double divisible = contextCalculator.pop();
        double divisor = contextCalculator.pop();
        if (divisor == 0) {
            contextCalculator.push(divisor);
            contextCalculator.push(divisible);

            DivisionByZeroException exception = new DivisionByZeroException();
            log.log(Level.SEVERE, "", exception);
            throw  exception;
        }
        double result = divisible / divisor;
        contextCalculator.push(result);
        if (log.isLoggable(Level.INFO)) {
            log.log(Level.INFO, String.format("%s / %s = %s", divisible, divisor, result));
        }
    }
}
