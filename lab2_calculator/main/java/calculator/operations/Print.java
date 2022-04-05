package calculator.operations;

import calculator.ArgumentCountChecker;
import calculator.ContextCalculator;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Print implements Operation {

    private static final Logger log = Logger.getLogger(Print.class.getName());

    private final int numberOfArguments = 0;
    private final int numberOfRequiredValues = 1;

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws IncorrectNumberOfArgumentsException, NotEnoughDataInStackException {
        ArgumentCountChecker.checkNumberOfArguments(arguments.size(), numberOfArguments, this.getClass().getSimpleName());
        ArgumentCountChecker.checkNumberOfValues(contextCalculator.getSizeOfStack(), numberOfRequiredValues, this.getClass().getSimpleName());

        double value = contextCalculator.getTop();
        System.out.println(value);
        if (log.isLoggable(Level.INFO)) {
            log.log(Level.INFO, String.format("value %s was printed", value));
        }
    }
}
