package calculator.operations;

import calculator.ArgumentCountChecker;
import calculator.ContextCalculator;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Subtract implements Operation {
    private static final Logger log = Logger.getLogger(Subtract.class.getName());

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
            throws IncorrectNumberOfArgumentsException, NotEnoughDataInStackException {
        ArgumentCountChecker argumentCountChecker = new ArgumentCountChecker();
        argumentCountChecker.checkNumberOfArguments(arguments.size(), getNumberOfArguments(), this.getClass().getSimpleName());
        argumentCountChecker.checkNumberOfValues(contextCalculator.getSizeOfStack(), getNumberOfRequiredValues(), this.getClass().getSimpleName());

        double arg1 = contextCalculator.pop();
        double arg2 = contextCalculator.pop();
        double result = arg1 - arg2;
        contextCalculator.push(result);
        log.log(Level.INFO, String.format("%s - %s = %s", arg1, arg2, result));
    }
}
