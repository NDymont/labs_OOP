import calculator.ContextCalculator;
import calculator.IncorrectNameOfParameterException;
import calculator.IncorrectTypeOfArgumentException;
import calculator.operations.Define;
import calculator.operations.Operation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDefine {
    @Test
    public void testDefine() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        Operation operation = new Define();
        arguments.add("arg");
        arguments.add("0.05");
        operation.execute(contextCalculator, arguments);
        Double value = contextCalculator.getValue("arg");
        Assert.assertEquals(value, new Double(0.05));
    }

    @Test
    public void testRedefinition() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        Operation operation = new Define();
        arguments.add("arg");
        arguments.add("5");

        List<String> newArguments = new ArrayList<>();
        newArguments.add("arg");
        newArguments.add("0.05");

        operation.execute(contextCalculator, arguments);
        operation.execute(contextCalculator, newArguments);

        Double value = contextCalculator.getValue("arg");
        Assert.assertEquals(value, new Double(0.05));
    }

    @Test(expected = IncorrectNameOfParameterException.class)
    public void testIncorrectName() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        Operation operation = new Define();
        arguments.add("*arg");
        arguments.add("5");
        operation.execute(contextCalculator, arguments);
    }

    @Test(expected = IncorrectTypeOfArgumentException.class)
    public void testIncorrectNumber() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        Operation operation = new Define();
        arguments.add("arg");
        arguments.add("5,2");
        operation.execute(contextCalculator, arguments);
    }
}
