import calculator.ContextCalculator;
import calculator.IncorrectNumberOfArgumentsException;
import calculator.NotEnoughDataInStackException;
import calculator.operations.Operation;
import calculator.operations.Subtract;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSubtract {
    @Test
    public void testSubstract() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        contextCalculator.push(10);
        contextCalculator.push(30);
        Operation operation = new Subtract();
        operation.execute(contextCalculator, new ArrayList<>());
        Double value = contextCalculator.getTop();
        Assert.assertEquals(new Double(20), value);
    }

    @Test(expected = IncorrectNumberOfArgumentsException.class)
    public void testNumberOfArgumentsException() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        arguments.add("34");
        contextCalculator.push(10);
        contextCalculator.push(30);
        Operation operation = new Subtract();
        operation.execute(contextCalculator, arguments);
    }

    @Test(expected = NotEnoughDataInStackException.class)
    public void testNumberOfValues() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        contextCalculator.push(10);
        Operation operation = new Subtract();
        operation.execute(contextCalculator, arguments);
    }
}