package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * It reads the file line by line, splits the line into two parts (id and price) and creates a new Commodity object with
 * the given parameters
 */
public class Object_Creator implements Runnable{

    // A reference to the warehouse object.
    private final Warehouse warehouse;

    // A reference to the file from which the data is read.
    private final Path path;

    // A constructor. It is used to create an object of the class Object_Creator.
    public Object_Creator (Warehouse warehouse, Path path) {
        this.warehouse = warehouse;
        this.path = path;
    }

    @Override
    public void run() {
        Iterator<String> lines;

        // Used to read the file line by line.
        try {
            lines = Files.lines(path).iterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (int i = 1; lines.hasNext(); i++){
            String[] split_data_table = lines.next().split(" ");
            warehouse.load(new Commodity(Integer.parseInt(split_data_table[0]),Double.parseDouble(split_data_table[1])));

            // It prints the number of created objects every 200 objects.
            if(i % 200 == 0){
                System.out.println("utworzono " + i + " obiektów");
            }
        }

        // Used to inform the warehouse that the delivery is finished.
        warehouse.finish_Delivery();
    }
}
