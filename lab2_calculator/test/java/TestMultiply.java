import calculator.ContextCalculator;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;
import calculator.operations.Multiply;
import calculator.operations.Operation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMultiply {
    @Test
    public void testMultiply() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        contextCalculator.push(10);
        contextCalculator.push(30);
        Operation operation = new Multiply();
        operation.execute(contextCalculator, new ArrayList<>());
        Double value = contextCalculator.getTop();
        Assert.assertEquals(new Double(300), value);
    }

    @Test(expected = IncorrectNumberOfArgumentsException.class)
    public void testNumberOfArgumentsException() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        arguments.add("47");
        contextCalculator.push(10);
        contextCalculator.push(30);
        Operation operation = new Multiply();
        operation.execute(contextCalculator, arguments);
    }

    @Test(expected = NotEnoughDataInStackException.class)
    public void testNumberOfValues() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        contextCalculator.push(10);
        Operation operation = new Multiply();
        operation.execute(contextCalculator, arguments);
    }
}