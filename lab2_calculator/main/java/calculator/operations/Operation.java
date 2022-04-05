package calculator.operations;

import calculator.*;

import java.util.List;

public interface Operation {

    void execute(ContextCalculator contextCalculator, List<String> arguments) throws ExecutionExcepiton;
}
