/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad1;

@FunctionalInterface
public interface Selector<Type> {// Uwaga: interfejs musi być sparametrtyzowany
    boolean select(Type value);
}
