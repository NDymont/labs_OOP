package calculator;

import calculator.operations.Operation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoryOperation {
    private static final Logger log = Logger.getLogger(FactoryOperation.class.getName());
    public Operation createOperation(String designationOperation) throws ClassNotFoundException, InstantiationException, IllegalAccessException, OperationNotFoundException, IOException {
        final Properties properties = new Properties();

        InputStream resourceAsStream = FactoryOperation.class.getClassLoader().getResourceAsStream("Operations.properties");

        if (resourceAsStream == null) {
            throw new FileNotFoundException();
        }
        properties.load(resourceAsStream);
        String className = properties.getProperty(designationOperation);
        if (className == null) {
            OperationNotFoundException exception = new OperationNotFoundException(designationOperation);
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
        return (Operation) Class.forName(className).newInstance();
    }
}
