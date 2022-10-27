package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator <T>{

    private List <T> list;
    private List <T> listTmp;

    public ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator <T> collectFrom(List<T> destinations) {
        return new ListCreator<T>(destinations);
    }


    public ListCreator <T> when(Predicate <T> p) {
        listTmp = new ArrayList<T>();

        for (T t : list) {
            if (p.test(t)) {
                listTmp.add(t);
            }
        }
        this.list = listTmp;
        return this;
    }

    public <R> List <T> mapEvery (Function<T,R> u){
        listTmp = new ArrayList<>();
        for (T e: list){
            listTmp.add((T)u.apply(e));
        }
        this.list = listTmp;
        return list;
    }
}
