/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad1;


public class Purchase {
    //id_klienta; nazwisko i imię; nazwa_towaru; cena; zakupiona_ilość
    public String clientID;
    public String clientSurname;
    public String clientName;
    public String productName;
    public float productPrice;
    public float productQuantity;


    // This is a constructor. It takes a string as an argument and splits it into an array of strings. Then it assigns the
    // values of the array to the fields of the class.
    public Purchase(String line) {
        String[] spliter = line.split(";");

        String[] clientNameSpliter = spliter[1].split(" ");

        this.clientID = spliter[0];
        this.clientSurname = clientNameSpliter[0];
        this.clientName = clientNameSpliter[1];
        this.productName = spliter[2];
        this.productPrice = Float.parseFloat(spliter[3]);
        this.productQuantity = Float.parseFloat(spliter[4]);
    }

    /**
     * This function returns the cost of the product by multiplying the product price by the product quantity
     *
     * @return The cost of the product.
     */
    public float getCost(){
        return this.productPrice * this.productQuantity;
    }


    /**
     * It returns a string containing the client's ID, name, surname, product name, product price, product quantity and
     * product cost
     *
     * @param withProductCost if true, the cost of the product will be added to the end of the string
     * @return A string containing the clientID, clientSurname, clientName, productName, productPrice, productQuantity and
     * productCost.
     */
    public String toString(boolean withProductCost){
        String concat = String.join(";",this.clientID,this.clientSurname + " " + this.clientName, this.productName, Float.toString(this.productPrice),Float.toString(this.productQuantity));

        if(!withProductCost){
            return concat;
        }

        String stringProductCost = Float.toString(this.getCost());
        return concat + " (koszt: " + stringProductCost + ")";
    }

    /**
     * > This function returns a string representation of the object
     *
     * @return The toString method is being returned.
     */
    @Override
    public String toString(){
        return this.toString(false);
    }

}
