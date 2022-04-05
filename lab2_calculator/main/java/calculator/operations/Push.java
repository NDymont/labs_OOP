package calculator.operations;

import calculator.ArgumentCountChecker;
import calculator.ContextCalculator;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.IncorrectTypeOfArgumentException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Push implements Operation {
    private static final Logger log = Logger.getLogger(Push.class.getName());

    private final int numberOfArguments = 1;

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws IncorrectNumberOfArgumentsException, IncorrectTypeOfArgumentException {
        ArgumentCountChecker.checkNumberOfArguments(arguments.size(), numberOfArguments, this.getClass().getSimpleName());
        String argument = arguments.get(0);
        StringBuilder stringBuilder = new StringBuilder(arguments.get(0));
        if (Character.isLetter(stringBuilder.charAt(0))) {
            double value = contextCalculator.getValue(argument);
            contextCalculator.push(value);
            if (log.isLoggable(Level.INFO)) {
                log.log(Level.INFO, String.format("value %s was pushed", value));
            }
        } else {
            try {
                contextCalculator.push(Double.parseDouble(argument));
            } catch (NumberFormatException ex) {
                IncorrectTypeOfArgumentException exception = new IncorrectTypeOfArgumentException(this.getClass().getSimpleName());
                log.log(Level.SEVERE, "", exception);
                throw exception;
            }
        }
    }
}
