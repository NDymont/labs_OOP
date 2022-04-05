package com.company;

import calculator.Calculator;
import calculator.InitializationException;
import calculator.Workflow;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e);
        }
        Workflow workflow;
        Calculator calculator;
        try {
            workflow = new Workflow("input.txt");
            calculator = new Calculator();
        } catch (InitializationException e) {
            return;
        }
        while (workflow.getSize() > 0) {
            Pair<String, List<String>> command = workflow.poll();
            calculator.execute(command);
        }
    }
}
