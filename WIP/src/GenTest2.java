import java.lang.reflect.Method;

class Para<S,T> {
    static int nr;
    private S first;
    private T last;

    public Para() {

    }

    public static int getNr() {
        return nr;
    }

    public Para(S first, T last) {
        this.first = first;
        this.last = last;
        nr++;
    }

    public S getFirst() {
        return first;
    }

    public void setFirst(S first) {
        this.first = first;
    }

    public T getLast() {
        return last;
    }

    public void setLast(T last) {
        this.last = last;
    }

}
public class GenTest2{
    public static void main(String[] args) {
        //ta klasa
        Para<String,Integer> p1 = new Para<>("Ala",3);
        System.out.println(Para.getNr());

        Para<String,Integer> p2 = new Para<>("Ala",3);
        System.out.println(Para.getNr());

        //powinna być inna niż ta klasa (przynajmniej koncepcyjnie)
        Para<String,String> p3 = new Para<>("Ala","Kowalska");
        System.out.println(Para.getNr());

        //ALE NIE JEST

        //Co jest - TYLKO klasa Para
        //"Raw Type"

        Class p1class = p1.getClass();
        System.out.println(p1class);
        System.out.println();

        //Metodami refleksji możemy się przekonać, że w definicji klasy Para typem fazy wykonania parametrów jest
        //Obiekt "typu erasure"!!!

        Method[] methods = p1class.getDeclaredMethods(); //zwraca tablice metod deklarowanych w klasie
        for(Method m: methods)
            System.out.println(m);

        //Surowego typu ("Raw Type") możemy też używać, ale czasem wiąże się to z niuansami i kompilator może nas ostrzegać
        //o możliwych błędach.
    }
}

