import calculator.ContextCalculator;
import calculator.DivisionByZeroException;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;
import calculator.operations.Division;
import calculator.operations.Operation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDivision {
    @Test
    public void testDivision() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        contextCalculator.push(10);
        contextCalculator.push(30);
        Operation operation = new Division();
        operation.execute(contextCalculator, new ArrayList<>());
        Double value = contextCalculator.getTop();
        Assert.assertEquals(new Double(3), value);
    }

    @Test(expected = DivisionByZeroException.class)
    public void testDivisionByZero() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        contextCalculator.push(0);
        contextCalculator.push(30);
        Operation operation = new Division();
        operation.execute(contextCalculator, new ArrayList<>());
        Double value = contextCalculator.getTop();
    }

    @Test(expected = IncorrectNumberOfArgumentsException.class)
    public void testNumberOfArgumentsException() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        arguments.add("47");
        contextCalculator.push(10);
        contextCalculator.push(30);
        Operation operation = new Division();
        operation.execute(contextCalculator, arguments);
    }

    @Test(expected = NotEnoughDataInStackException.class)
    public void testNumberOfValues() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        contextCalculator.push(10);
        Operation operation = new Division();
        operation.execute(contextCalculator, arguments);
    }
}