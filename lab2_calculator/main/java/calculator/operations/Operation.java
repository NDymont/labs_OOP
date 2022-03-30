package calculator.operations;

import calculator.ContextCalculator;
import calculator.DivisionByZeroException;
import calculator.ExtractingRootFromNegativeException;
import calculator.IncorrectNameOfParameterException;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.IncorrectTypeOfArgumentException;
import calculator.NotEnoughDataInStackException;
import calculator.UnidentifiedArgumentTypeException;

import java.util.List;

public interface Operation {
    int getNumberOfArguments();
    int getNumberOfRequiredValues();
    void execute(ContextCalculator contextCalculator, List<String> arguments)
            throws IncorrectNumberOfArgumentsException, IncorrectNameOfParameterException,
            IncorrectTypeOfArgumentException, DivisionByZeroException,
            ExtractingRootFromNegativeException, UnidentifiedArgumentTypeException,
            NotEnoughDataInStackException;
}
