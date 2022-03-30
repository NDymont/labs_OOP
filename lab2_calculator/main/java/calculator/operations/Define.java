package calculator.operations;

import calculator.ArgumentCountChecker;
import calculator.ContextCalculator;
import calculator.IncorrectNameOfParameterException;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.IncorrectTypeOfArgumentException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Define implements Operation {
    private static final Logger log = Logger.getLogger(Define.class.getName());

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    @Override
    public int getNumberOfRequiredValues() {
        return 0;
    }

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws IncorrectNumberOfArgumentsException, IncorrectNameOfParameterException, IncorrectTypeOfArgumentException {

        ArgumentCountChecker argumentCountChecker = new ArgumentCountChecker();
        argumentCountChecker.checkNumberOfArguments(arguments.size(), getNumberOfArguments(), this.getClass().getSimpleName());

        StringBuffer parameterName = new StringBuffer(arguments.get(0));
        if (!Character.isLetter(parameterName.charAt(0))) {

            IncorrectNameOfParameterException exception = new IncorrectNameOfParameterException(this.getClass().getSimpleName());
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
        double parameterValue;
        try {
            parameterValue = Double.parseDouble(arguments.get(1));
        } catch (NumberFormatException ex) {
            IncorrectTypeOfArgumentException exception = new IncorrectTypeOfArgumentException(this.getClass().getSimpleName());
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
        contextCalculator.putParameter(parameterName.toString(), parameterValue);
        log.log(Level.INFO, String.format("Parameter %s was defined by the value %s", parameterName, parameterValue));
    }
}