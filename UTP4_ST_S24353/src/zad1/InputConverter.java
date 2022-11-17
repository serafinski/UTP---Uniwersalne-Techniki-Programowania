package zad1;


import java.util.function.Function;

/**
 * It takes a list of functions as an argument and applies them to the data in the order they are given
 */
public class InputConverter<T> {
    private final T danewejsciowe;

    // It's a constructor.
    public InputConverter(T danewejsciowe) {
        this.danewejsciowe = danewejsciowe;
    }

    /**
     * It takes an array of functions and applies them to the input value, returning the result of the last function
     *
     * @return The last value of the input variable.
     */
    public <R> R convertBy(Function... functions) {
        Object input = this.danewejsciowe;
        Object rezultat = null;

        for(Function f : functions){
            rezultat = f.apply(input);
            input = rezultat;
        }
        return (R) rezultat;
    }
}