/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad1;


import java.nio.file.Paths;

public class Main {
  /**
   * It creates two threads, one of which reads objects from a file and puts them in a warehouse, and the other one
   * calculates the sum of weights of all objects in the warehouse
   */
  public static void main(String[] args) {
    Warehouse warehouse = new Warehouse();

    Object_Creator object_creator = new Object_Creator(warehouse, Paths.get("../Towary.txt"));
    Sum_Of_Weights sum_of_weights = new Sum_Of_Weights(warehouse);

    Thread object_Creator_Thread = new Thread(object_creator);
    Thread sum_Of_Weights_Thread = new Thread(sum_of_weights);

    object_Creator_Thread.start();
    sum_Of_Weights_Thread.start();

    try {
      object_Creator_Thread.join(1000);
      sum_Of_Weights_Thread.join();
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
