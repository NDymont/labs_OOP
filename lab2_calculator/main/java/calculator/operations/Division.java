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


    @Override
    public int getNumberOfArguments() {
        return 0;
    }

    @Override
    public int getNumberOfRequiredValues() {
        return 2;
    }

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws DivisionByZeroException, IncorrectNumberOfArgumentsException, NotEnoughDataInStackException {
        ArgumentCountChecker argumentCountChecker = new ArgumentCountChecker();
        argumentCountChecker.checkNumberOfArguments(arguments.size(), getNumberOfArguments(), this.getClass().getSimpleName());
        argumentCountChecker.checkNumberOfValues(contextCalculator.getSizeOfStack(), getNumberOfRequiredValues(), this.getClass().getSimpleName());

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
        log.log(Level.INFO, String.format("%s / %s = %s", divisible, divisor, result));
    }
}
