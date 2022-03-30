import calculator.ContextCalculator;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;
import calculator.operations.Operation;
import calculator.operations.Pop;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPop {

    @Test
    public void testPop() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        Operation operation = new Pop();
        contextCalculator.push(5);
        contextCalculator.push(6);
        operation.execute(contextCalculator, arguments);
        Double value = contextCalculator.getTop();
        Assert.assertEquals(new Double(5), value);
    }

    @Test(expected = NotEnoughDataInStackException.class)
    public void testNotEnoughDataInStackException() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        Operation operation = new Pop();
        operation.execute(contextCalculator, arguments);
    }

    @Test(expected = IncorrectNumberOfArgumentsException.class)
    public void testNumberOfArgumentsException() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        arguments.add("34");
        contextCalculator.push(10);
        contextCalculator.push(30);
        Operation operation = new Pop();
        operation.execute(contextCalculator, arguments);
    }
}
