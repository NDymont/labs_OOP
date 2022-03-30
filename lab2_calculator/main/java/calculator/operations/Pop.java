package calculator.operations;

import calculator.ArgumentCountChecker;
import calculator.ContextCalculator;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pop implements Operation {

    private static final Logger log = Logger.getLogger(Pop.class.getName());

    @Override
    public int getNumberOfArguments() {
        return 0;
    }

    @Override
    public int getNumberOfRequiredValues() {
        return 1;
    }

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments) throws IncorrectNumberOfArgumentsException, NotEnoughDataInStackException {
        ArgumentCountChecker argumentCountChecker = new ArgumentCountChecker();
        argumentCountChecker.checkNumberOfArguments(arguments.size(), getNumberOfArguments(), this.getClass().getSimpleName());
        argumentCountChecker.checkNumberOfValues(contextCalculator.getSizeOfStack(), getNumberOfRequiredValues(), this.getClass().getSimpleName());
        double value = contextCalculator.pop();
        log.log(Level.INFO, String.format("value %s was poped", value));
    }
}