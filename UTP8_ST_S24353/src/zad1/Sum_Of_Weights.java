package zad1;

/**
 * The class is a thread that counts the weight of all the commodities in the storage
 */
public class Sum_Of_Weights implements Runnable {

    // A private variable that is used to store the storage object.
    private final Warehouse warehouse;

    // A private variable that is used to store the sum of the weights of all the commodities in the storage.
    private double sum;

    // A constructor that sets the value of the private variable warehouse to the value of the parameter warehouse and sets
    // the value of the private variable sum to 0.
    public Sum_Of_Weights(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.sum = 0;
    }


    /**
     * The function is a thread that counts the weight of all the commodities in the storage
     */
    @Override
    public void run() {
        for(int i = 1; warehouse.is_Expecting_More_Deliveries() || !warehouse.is_Commodity_Stack_Empty(); i++){
            sum += warehouse.unload().getWeight();

            // It prints the number of counted weights every 100 iterations.
            if(i%100 == 0){
                System.out.println("policzono wage "+i+" towarów");
            }
        }
        System.out.println("Waga to: "+ sum);
    }
}
