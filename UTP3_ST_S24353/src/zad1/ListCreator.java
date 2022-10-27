package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator <T>{

    private List <T> list;
    private List <T> tmp;

    // A constructor that takes a list of generic type T and assigns it to the list variable.
    public ListCreator(List<T> list) {
        this.list = list;
    }

    /**
     * It takes a list of destinations and returns a ListCreator object that has a list of destinations.
     *
     * @param destinations The list to collect the results into.
     * @return A new ListCreator object.
     */
    public static <T> ListCreator <T> collectFrom(List<T> destinations) {
        return new ListCreator<T>(destinations);
    }


    /**
     * When the predicate is true, add the element to the temporary list.
     *
     * @param predicate Predicate <T>
     * @return A ListCreator object
     */
    public ListCreator <T> when(Predicate <T> predicate) {
        tmp = new ArrayList<T>();

        for (T t : list) {
            if (predicate.test(t)) {
                tmp.add(t);
            }
        }
        this.list = tmp;
        return this;
    }

    /**
     * It takes a function as an argument and applies it to every element of the list
     *
     * @param function a function that takes in a generic type and returns a generic type
     * @return The list is being returned.
     */
    public <R> List <T> mapEvery (Function<T,R> function){
        tmp = new ArrayList<>();
        for (T t: list){
            tmp.add((T) function.apply(t));
        }
        this.list = tmp;
        return list;
    }
}
