package calculator;

public class IncorrectNameOfParameterException extends InvalidArgumentException {
    public IncorrectNameOfParameterException(String className) {
        super("A parameter with an incorrect name format was passed to the " + className +
                " class. The parameter name must begin with the letter");
    }
}
