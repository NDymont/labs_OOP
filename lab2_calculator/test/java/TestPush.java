import calculator.ContextCalculator;
import calculator.operations.Operation;
import calculator.operations.Push;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPush {

    @Test
    public void testPushValue() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        arguments.add("45.5");
        Operation operation = new Push();
        operation.execute(contextCalculator, arguments);
        Double value = contextCalculator.getTop();
        Assert.assertEquals(new Double(45.5), value);
    }

    @Test
    public void testPushParameter() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        contextCalculator.putParameter("x1", 45.5);
        arguments.add("x1");
        Operation operation = new Push();
        operation.execute(contextCalculator, arguments);
        Double value = contextCalculator.getTop();
        Assert.assertEquals(new Double(45.5), value);
    }
}
