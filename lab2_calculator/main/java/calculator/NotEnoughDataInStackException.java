package calculator;

public class NotEnoughDataInStackException extends Exception {

    public NotEnoughDataInStackException(int stackSize, int numberOfRequiredValues, String className) {
        super(String.format("There is not enough data in the stack.\n" +
                        "The %s operation requests %d values from the stack. Values in the stack : %d.",
                className, numberOfRequiredValues, stackSize));
    }
}
