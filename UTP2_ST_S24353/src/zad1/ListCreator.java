/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<Type>{ // Uwaga: klasa musi być sparametrtyzowana
    public List <Type> list;

    public ListCreator(List<Type> list) {
        this.list = list;
    }

    public static <Type> ListCreator <Type> collectFrom (List<Type> list){
        return new ListCreator<>(list);
    }

    public ListCreator <Type> when (Selector <Type> selector){
        List<Type> selectorEndList = new ArrayList<>();

        for (Type type : list) {
            if (selector.select(type)) {
                selectorEndList.add(type);
            }
        }
        list = selectorEndList;
        return this;
    }

    public <Type2> List <Type2> mapEvery (Mapper <Type,Type2> mapper){
        List<Type2> mapperEndList = new ArrayList<>();

        for (Type type : list) {
            mapperEndList.add(mapper.map((type)));
        }
        return mapperEndList;
    }
}