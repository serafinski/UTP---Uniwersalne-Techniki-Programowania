/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad1;


public interface Mapper<Type,Type2> { // Uwaga: interfejs musi być sparametrtyzowany
    Type2 map(Type value);
}  
