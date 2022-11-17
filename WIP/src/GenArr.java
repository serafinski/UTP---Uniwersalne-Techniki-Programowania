public class GenArr <T extends Comparable<T>> {
    private T[] arr;
    private Para<T,T> minMax; // na przechowywanie wartości min i max

    public void init(T[]a){
        if(a==null || a.length==0)
            throw new IllegalArgumentException("Invalid array init");
        minMax=null;
        arr=a;
    }

    public GenArr(T[] a){
        init(a);
    }

    private Para<T,T> evaluate(String kind){
        //w klasie Para<S, T> jest konstruktor bezparametrowy
        if(minMax == null)
            minMax = new Para<T,T>();
        T v = arr[0];
        switch (kind){
            case "MAX":{
                if(minMax.getFirst() == null){
                    //możemy to napisać dzięki temu, że T extends comparable
                    for (T e: arr)
                        if(e.compareTo(v)>0)
                            v=e;
                    minMax.setFirst(v);
                }
            }
            case "MIN":{
                if (minMax.getLast() == null){
                    for(T e: arr)
                        if(e.compareTo(v)<0)
                            v=e;
                    minMax.setLast(v);
                }
            }
        }
        return minMax;
    }

    public T max(){
        return evaluate("MAX").getFirst();
    }

    public T min(){
        return evaluate("MIN").getLast();
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1,2,7,-3};
        Integer[] arr2 = {1,7,8,-10};
        String[] napisy = {"A","Z","C"};

        GenArr<Integer> gai = new GenArr<>(arr1);
        System.out.println(gai.max() + " " + gai.min());
        gai.init(arr2);
        System.out.println(gai.max() + " " + gai.min());

        GenArr<String> gas = new GenArr<>(napisy);
        System.out.println(gas.max()+ " " + gas.min());
    }

}
