package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {

    // A variable that holds the value of the Maybe object.
    T value;

    // A constructor that takes a value of type T and assigns it to the value field.
    public Maybe(T value) {
        super();
        this.value = value;
    }

    // A default constructor.
    public Maybe() {
        super();
    }


    /**
     * If the value is null, return an empty Maybe, otherwise return a Maybe with the value.
     *
     * @param s The value to be wrapped in a Maybe.
     * @return A Maybe object
     */
    public static <T> Maybe<T> of(T s) {
        return new Maybe<>(s);
    }

    /**
     * If the value is present, invoke the specified consumer with the value, otherwise do nothing
     *
     * @param consumer A Consumer is a functional interface that takes in a single input and returns no result.
     */
    public void ifPresent(Consumer<T> consumer) {
        if(isPresent()){
            consumer.accept(value);
        }
    }

    /**
     * If the value is not null, return true, otherwise return false.
     *
     * @return The value of the variable value.
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * If the Maybe is empty, return "Maybe is empty", otherwise, return "Maybe has value " + value
     *
     * @return The value of the Maybe object.
     */
    @Override
    public String toString() {

        if(!isPresent()) {

            return "Maybe is empty";
        }else {
            try {
                this.get();
            }catch(NoSuchElementException e) {
                throw new RuntimeException(e);
            }
        }

        return "Maybe has value " + value;
    }

    /**
     * If the value is present, return it, otherwise throw an exception
     *
     * @return The value of the Optional object.
     */
    public T get() {
        if(!isPresent()){
            throw new NoSuchElementException("maybe is empty");
        }
        return value;
    }

    /**
     * If the Maybe is present, apply the function to the value and return a new Maybe with the result. If the Maybe is
     * empty, return a new empty Maybe
     *
     * @param func A function that takes a value of type T and returns a value of type R.
     * @return A new Maybe object with the value of the function applied to the value of the Maybe object.
     */
    public <R> Maybe<R> map(Function<T, R> func) {
        if(isPresent()){
            R x = func.apply(value);
            return new Maybe<>(x);
        }
        return new Maybe<>();
    }

    /**
     * If the Optional is present, return the value, otherwise return the default value
     *
     * @param defVal The default value to return if the Optional is empty.
     * @return The value of the Optional object if it is present, otherwise the default value.
     */
    public T orElse (T defVal) {
        if(isPresent()){
            return value;
        }

        return defVal;
    }

    /**
     * If the Maybe is present, and the predicate is true, return the Maybe. Otherwise, return an empty Maybe
     *
     * @param pred A predicate that takes a value and returns a boolean.
     * @return The value of the Maybe object.
     */
    public Maybe<T> filter(Predicate<T> pred) {
        if(isPresent()) {
            if(pred.test(value)){
                return this;
            }
        }
        return this;
    }
}