package calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ContextCalculator {
    private Stack<Double> valueStack = new Stack<>();
    private Map<String, Double> parameters = new HashMap<>();

    public void putParameter(String key, Double value) {
        parameters.put(key, value);
    }

    public double getValue(String key) {
        return parameters.get(key);
    }

    public double getTop() {
        return valueStack.peek();
    }

    public double pop() {
        return valueStack.pop();
    }

    public void push(double key) {
        valueStack.push(key);
    }

    public void printStack() {
        System.out.println(valueStack);
    }

    public int getSizeOfStack() {
        return valueStack.size();
    }
}
