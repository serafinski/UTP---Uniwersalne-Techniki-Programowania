/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class CustomersPurchaseSortFind {
    private ArrayList<Purchase> all;

    /**
     * Reads a file and creates a list of purchases
     *
     * @param fileName the name of the file to read
     */
    public void readFile(String fileName) {
        this.all = new ArrayList<>();

        try{
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while ((line = bufferedReader.readLine())!=null){
                this.all.add(new Purchase(line));
            }

            bufferedReader.close();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * It returns a comparator that compares purchases by the given filter
     *
     * @param filter a string that can be either "Nazwiska" or "Koszty"
     * @return Comparator<Purchase>
     */
    private Comparator<Purchase> getFilter (String filter){
        if (filter.equals("Nazwiska")){
            return (first, second) -> {
                int isdiffrent = first.clientSurname.compareTo(second.clientSurname);

                if (isdiffrent == 0){
                    return first.clientID.compareTo(second.clientID);
                }
                return  isdiffrent;
            };
        }

        if(filter.equals("Koszty")){
            return (first, second) -> {
                int difference = (int) Math.ceil(second.getCost()- first.getCost());

                if(difference == 0){
                    return first.clientID.compareTo(second.clientID);
                }
                return difference;
            };
        }
        return (first, second) -> 0;
    }

    /**
     * It takes a string, prints it, then prints all the purchases sorted by the filter that corresponds to the string
     *
     * @param filterName the filterName of the column to sort by
     */
    public void showSortedBy(String filterName) {
        System.out.println(filterName);

        this.all
                .stream()
                .sorted(this.getFilter(filterName))
                .forEach(purchase -> System.out.println(purchase.toString(filterName.equals("Koszty"))));

        System.out.println();
    }

    /**
     * "Show all purchases for a given client."
     *
     * The function is called showPurchaseFor and it takes a single parameter, a String called clientID
     *
     * @param clientID the client ID to filter by
     */
    public void showPurchaseFor(String clientID) {
        System.out.println("Klient " + clientID);

        this.all
                .stream()
                .filter(purchase -> purchase.clientID.equals(clientID))
                .forEach(System.out::println);

        System.out.println();
    }
}
