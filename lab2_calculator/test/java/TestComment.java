import calculator.ContextCalculator;
import calculator.operations.Comment;
import calculator.operations.Operation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestComment {

    @Test
    public void testPop() throws Exception {
        ContextCalculator contextCalculator = new ContextCalculator();
        List<String> arguments = new ArrayList<>();
        contextCalculator.push(10);
        contextCalculator.push(30);
        int sizeOfStack = contextCalculator.getSizeOfStack();
        Operation operation = new Comment();
        operation.execute(contextCalculator, arguments);
        int newSizeOfStack = contextCalculator.getSizeOfStack();
        Assert.assertEquals(sizeOfStack, newSizeOfStack);
        Double value = contextCalculator.getTop();
        Assert.assertEquals(new Double(30), value);
    }
}