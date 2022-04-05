package calculator;

public class IncorrectTypeOfArgumentException extends InvalidArgumentException {
    public IncorrectTypeOfArgumentException(String className) {
        super("An argument with an incorrect format was passed to the " + className + " class");
    }
}
