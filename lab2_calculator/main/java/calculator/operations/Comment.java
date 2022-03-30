package calculator.operations;

import calculator.ContextCalculator;

import java.util.List;

public class Comment implements Operation {
    @Override
    public int getNumberOfArguments() {
        return 0;
    }

    @Override
    public int getNumberOfRequiredValues() {
        return 0;
    }

    @Override
    public void execute(ContextCalculator contextCalculator, List<String> arguments) {
    }
}
