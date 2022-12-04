package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {

    // Creating a list of threads.
    private final List <Thread> threads = new ArrayList<>();

    // Creating a list of threads.
    public Letters(String letters) {
        // Creating a new thread for each character in the string.
        for (char character : letters.toCharArray()){
            threads.add(new Thread ( () -> {
                // It prints the character and then waits for 1 second.
                while (true) {
                    System.out.print (character);
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException exc) {
                        return;
                    }
                }
            }, "Thread "+ character));
        }
    }

    /**
     * This function returns a list of threads.
     *
     * @return A list of threads.
     */
    public List<Thread> getThreads (){
        return threads;
    }
}
