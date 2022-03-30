package com.company;

import calculator.*;
import calculator.FactoryOperation;
import calculator.operations.Operation;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        Workflow workflow = new Workflow("input.txt");
        FactoryOperation factoryOperation = new FactoryOperation();

        ContextCalculator contextCalculator = new ContextCalculator();

        while (workflow.getSize() > 0) {
            Pair<String, List<String>> comand = workflow.poll();
            Operation operation = null;
            try {
                operation = factoryOperation.createOperation(comand.getKey());
            } catch (OperationNotFoundException e) {
                System.err.println("ERROR: " + e.getMessage());
                continue;
            }
            try {
                operation.execute(contextCalculator, comand.getValue());
            } catch (IncorrectNumberOfArgumentsException | ExtractingRootFromNegativeException |
                    IncorrectTypeOfArgumentException | IncorrectNameOfParameterException |
                    DivisionByZeroException | UnidentifiedArgumentTypeException |
                    NotEnoughDataInStackException e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        }
    }
}
