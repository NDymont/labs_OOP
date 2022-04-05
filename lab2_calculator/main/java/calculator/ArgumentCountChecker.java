package calculator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ArgumentCountChecker {
    private static final Logger log = Logger.getLogger(ArgumentCountChecker.class.getName());

    public static void checkNumberOfArguments(int numberOfArguments, int correctNumberOfArguments, String className) throws IncorrectNumberOfArgumentsException {
        if (numberOfArguments != correctNumberOfArguments) {
            IncorrectNumberOfArgumentsException exception = new IncorrectNumberOfArgumentsException(className, numberOfArguments, correctNumberOfArguments);
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
    }

    public static void checkNumberOfValues(int numberValues, int numberOfRequiredValues, String className) throws NotEnoughDataInStackException {
        if (numberValues < numberOfRequiredValues) {
            NotEnoughDataInStackException exception = new NotEnoughDataInStackException(numberValues, numberOfRequiredValues, className);
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
    }
}
