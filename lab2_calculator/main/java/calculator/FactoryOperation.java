package calculator;

import calculator.operations.Operation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoryOperation {
    private static final Logger log = Logger.getLogger(FactoryOperation.class.getName());
    Properties properties;

    public FactoryOperation() throws PropertiesException {
        properties = new Properties();

        InputStream resourceAsStream = FactoryOperation.class.getClassLoader().getResourceAsStream("Operations.properties");

        if (resourceAsStream == null) {
            PropertiesException exception = new PropertiesException("Error getting the property file");
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            PropertiesException exception = new PropertiesException("Error loading the property", e);
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
    }

    public Operation createOperation(String designationOperation) throws CreateOperationException {
        String className = properties.getProperty(designationOperation);
        if (className == null) {
            OperationNotFoundException exception = new OperationNotFoundException(designationOperation);
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
        try {
            return (Operation) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            CreateOperationException exception = new CreateOperationException(e);
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
    }
}
