import calculator.ContextCalculator;
import calculator.ExtractingRootFromNegativeException;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;
import calculator.operations.Operation;
import calculator.operations.SquareRootExtraction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSquareRootExtraction {

    @Test
    public void testSqrt() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        contextCalculator.push(10);
        contextCalculator.push(25);
        Operation operation = new SquareRootExtraction();
        operation.execute(contextCalculator, new ArrayList<>());
        Double value = contextCalculator.getTop();
        Assert.assertEquals(new Double(5), value);
    }

    @Test(expected = IncorrectNumberOfArgumentsException.class)
    public void testNumberOfArgumentsException() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        arguments.add("34");
        contextCalculator.push(10);
        contextCalculator.push(30);
        Operation operation = new SquareRootExtraction();
        operation.execute(contextCalculator, arguments);
    }

    @Test(expected = NotEnoughDataInStackException.class)
    public void testNumberOfValues() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        Operation operation = new SquareRootExtraction();
        operation.execute(contextCalculator, arguments);
    }

    @Test(expected = ExtractingRootFromNegativeException.class)
    public void testRootFromNegative() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        contextCalculator.push(-1);
        Operation operation = new SquareRootExtraction();
        operation.execute(contextCalculator, arguments);
    }
}