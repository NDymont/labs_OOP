package calculator;

import calculator.operations.Operation;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {
    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    private Map<String, Operation> operationsMap = new HashMap<>();
    private FactoryOperation factoryOperation;
    private ContextCalculator contextCalculator = new ContextCalculator();

    public Calculator() throws PropertiesException {
        try {
            factoryOperation = new FactoryOperation();
        } catch (PropertiesException e) {
            log.log(Level.SEVERE, "", e);
            throw e;
        }
    }

    private Operation getOperation(String nameOperation) throws CreateOperationException {
        if (operationsMap.containsKey(nameOperation)) {
            return operationsMap.get(nameOperation);
        }
        Operation operation;
        operation = factoryOperation.createOperation(nameOperation);
        operationsMap.put(nameOperation, operation);
        return operation;
    }

    public void execute(Pair<String, List<String>> command) {
        Operation operation;
        try {
            operation = getOperation(command.getKey());
        } catch (CreateOperationException e) {
            System.err.println("The operation cannot be performed");
            log.log(Level.SEVERE, "", e);
            return;
        }
        try {
            operation.execute(contextCalculator, command.getValue());
        } catch (ExecutionExcepiton e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}
