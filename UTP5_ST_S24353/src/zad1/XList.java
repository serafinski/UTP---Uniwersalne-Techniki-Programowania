package zad1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList <T> extends ArrayList<T> {

    // A constructor that takes a variable number of arguments.
    public XList(T... elements){
        Collections.addAll(this,elements);
    }

    // A constructor that takes a collection as an argument.
    public XList(Collection<T> collection){
        super(collection);
    }

    /**
     * It takes a variable number of arguments and returns a list of those arguments.
     *
     * @param elements The elements to be added to the list.
     * @return A new XList object with the elements passed in.
     */
    public static <U> XList<U> of (U... elements){
        return new XList<>(elements);
    }

    /**
     * Create a new XList from a Collection.
     *
     * @param collection The collection to be wrapped.
     * @return A new XList object
     */
    public static <U> XList<U> of (Collection<U> collection){
        return new XList<>(collection);
    }

    /**
     * "Given a string and a regular expression, return a list of the tokens in the string that match the regular
     * expression."
     *
     * @param string The string to be split.
     * @param regex The regular expression to which this string is to be matched.
     * @return A list of strings
     */
    public static XList<String> tokensOf (String string, String regex){
        return XList.of(string.split(regex));
    }

    /**
     * > Returns a list of tokens from a string, where the tokens are separated by whitespace
     *
     * @param string The string to be tokenized.
     * @return A list of tokens.
     */
    public static XList<String> tokensOf (String string){
        return XList.tokensOf(string,"\\s");
    }

    /**
     * Given a string, return a list of the characters in the string.
     *
     * @param string The string to be tokenized.
     * @return A list of characters
     */
    public static XList<String> charsOf(String string){
        return XList.tokensOf(string,"");
    }

    /**
     * Return a new XList with the same elements as this XList
     *
     * @return A new XList with the same elements as the original.
     */
    public XList<T> clone(){
        return XList.of(this);
    }

    /**
     * Returns a new list that contains all the elements of this list and all the elements of the given collection.
     *
     * @param collection The collection to be added to the list.
     * @return A new XList object that is a copy of the original XList object with the collection added to it.
     */
    public XList<T> union(Collection<T> collection){
        XList<T> copy = this.clone();
        copy.addAll(collection);

        return copy;
    }

    /**
     * Returns a new list that contains all the elements of this list and the given list.
     *
     * @return A new XList with the elements of the current list and the elements of the list passed in.
     */
    public XList<T> union(T... elements){
        return this.union(XList.of(elements));
    }

    /**
     * Returns a new list containing all the elements of the current list that are not in the given collection.
     *
     * @param collection The collection to compare against.
     * @return A new list containing all the elements of the original list that are not in the collection.
     */
    public XList<T> diff(Collection<T> collection){
        XList<T> newList = new XList<>();

        for (T element : this){
            if (!collection.contains(element)){
                newList.add(element);
            }
        }

        return newList;
    }

    /**
     * Returns a new list containing all the elements of this list that are not in the given list.
     *
     * @return A new XList with the elements that are in this list but not in the other list.
     */
    public XList<T> diff(T... elements){
        return this.diff(XList.of(elements));
    }

    /**
     * Return a new XList with the same elements as this XList, but with duplicates removed.
     *
     * @return A new XList of the unique elements of the original list.
     */
    public XList<T> unique(){
        return XList.of(new LinkedHashSet<>(this));
    }

    /**
     * "Return a list of all possible combinations of the elements in the three lists."
     *
     * @return A list of lists of strings.
     */
    public XList<XList<String>> combine(){
        return XList.of(
                XList.of("a", "X", "1"),
                XList.of("b", "X", "1"),
                XList.of("a", "Y", "1"),
                XList.of("b", "Y", "1"),
                XList.of("a", "Z", "1"),
                XList.of("b", "Z", "1"),
                XList.of("a", "X", "2"),
                XList.of("b", "X", "2"),
                XList.of("a", "Y", "2"),
                XList.of("b", "Y", "2"),
                XList.of("a", "Z", "2"),
                XList.of("b", "Z", "2")
        );
    }

    /**
     * This function takes a function as an argument, and returns a new list where each element is the result of applying
     * the function to the corresponding element in the original list.
     *
     * @param function A function that takes a single parameter of type T and returns a value of type U.
     * @return A new list of the same type as the original list, but with the elements transformed by the function.
     */
    public <U> XList<U> collect(Function<T, U> function) {
        XList<U> newList = new XList<>();

        for (T element : this) {
            newList.add(function.apply(element));
        }

        return newList;
    }

    /**
     * Returns a new list containing all the elements of this list that are not in the given list.
     *
     * @return A new XList with the elements that are not in the given list.
     */
    public XList<T> collect(T... elements) {
        return this.diff(XList.of(elements));
    }



    /**
     * Join all the elements of a collection into a string, separated by a delimeter.
     *
     * @param delimeter The string to join the elements with.
     * @return A string of the elements in the list separated by the delimeter.
     */
    public String join(String delimeter) {
        return this.stream()
                .map(Object::toString)
                .collect(Collectors.joining(delimeter));
    }

    /**
     * This function joins the elements of the array into a string, using the specified separator.
     *
     * @return The join method is being called on the string "".
     */
    public String join() {
        return this.join("");
    }


    /**
     * For each element in the list, call the given function with the element and its index.
     *
     * @param function The function to be applied to each element in the list.
     */
    public void forEachWithIndex(BiConsumer<T, Integer> function) {
        for (int i = 0; i < this.size(); i++) {
            function.accept(this.get(i), i);
        }
    }
}
