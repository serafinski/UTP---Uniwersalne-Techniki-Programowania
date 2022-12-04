package zad2;

public class StringTask implements Runnable {
    private final String letter;
    private volatile String word = "";
    private final int number;
    private volatile int counter;
    private static int state = -1;
    private static Thread thread;

    // A constructor. It is called when we create an object of the class.
    public StringTask(String letter, int number) {
        this.letter = letter;
        this.number = number;;
    }


    /**
     * This function returns the word that was concatenated
     *
     * @return The word that is being returned is the final concatenated word.
     */
    public String getResult(){
        return word;
    }


    /**
     * If the state is -1, return TaskState.CREATED, else if the state is 0, return TaskState.ABORTED, else if the state is
     * 1, return TaskState.RUNNING, else return TaskState.READY.
     *
     * @return The state of the task.
     */
    public TaskState getState(){
        switch (state){
            case -1:
                return TaskState.CREATED;
            case 0:
                return TaskState.ABORTED;
            case 1:
                return TaskState.RUNNING;
            case 2:
            default:
                return TaskState.READY;
        }
    }

    /**
     * This function creates a new thread and starts it.
     */
    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    /**
     * If the thread is running, interrupt it.
     */
    public void abort(){
        state = 0;
        thread.interrupt();
    }

    /**
     * If the state is READY, return true. Otherwise, if the state is ABORTED, return true. Otherwise, return false
     *
     * @return The state of the task.
     */
    public boolean isDone(){
        return getState() == TaskState.READY || getState() == TaskState.ABORTED;
    }

    /**
     * The function runs a loop that adds a letter to a word until the word is the length of the number or the thread is
     * interrupted
     */
    @Override
    public void run() {
        state=1;

        do{
            word += letter;
            counter++;
        }
        while (counter < number && !Thread.currentThread().isInterrupted());

        if (counter == number) {
            state = 2;
        }
    }
}
