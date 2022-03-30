import calculator.OperationNotFoundException;
import calculator.FactoryOperation;
import org.junit.Test;

public class TestFactoryOperation {
    @Test(expected = OperationNotFoundException.class)
    public void testDivisionByZero() throws Exception {
        FactoryOperation factoryOperation = new FactoryOperation();
        factoryOperation.createOperation("not_operation");
    }
}
