package zad1;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * A stack of commodities that is used to store the commodities that are being delivered
 */
public class Warehouse {

    // Creating a new stack of commodities.
    private final Stack<Commodity> commodities = new Stack<>();

    // A flag that is used to signal the end of the deliveries.
    private boolean more_Deliveries = true;

    /**
     * This function adds a commodity to the list of commodities
     *
     * @param commodity The commodity to be loaded.
     */
    public void load (Commodity commodity){
        commodities.add(commodity);
    }

    /**
     * If the stack is empty, wait for 10 milliseconds and try again.
     *
     * @return A Commodity object
     */
    public Commodity unload (){
        // Waiting for the stack to be filled.
        while (commodities.isEmpty()){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ignored) {

            }
        }
        // It returns the last element of the stack and removes it from the stack.
        return commodities.pop();
    }

    /**
     * This function sets the boolean variable more_Deliveries to false
     */
    public void finish_Delivery(){
        more_Deliveries = false;
    }

    /**
     * > This function returns a boolean value that indicates whether or not we are expecting more
     * deliveries
     *
     * @return The boolean value of more_Deliveries.
     */
    public boolean is_Expecting_More_Deliveries(){
        return more_Deliveries;
    }

    /**
     * This function returns true if the stack is empty, and false if it is not
     *
     * @return The method is_Commodity_Stack_Empty() returns a boolean value.
     */
    public boolean is_Commodity_Stack_Empty(){
        return commodities.empty();
    }
}
