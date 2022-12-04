/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad2;


public class Main {
  public static void main(String[] args) throws InterruptedException {
    StringTask task = new StringTask("Y", 50000);
    System.out.println("Task " + task.getState());
    task.start();


    if (args.length > 0 && args[0].equals("abort")) {
      // Creating a new thread that will sleep for 1 second and then abort the task.
      new Thread(() -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException exception) {
          exception.printStackTrace();
        }
        task.abort();
      }).start();
    }

    while (!task.isDone()) {
      Thread.sleep(500);
      switch(task.getState()) {
        case RUNNING: System.out.print("R."); break;
        case ABORTED: System.out.println(" ... aborted."); break;
        case READY: System.out.println(" ... ready."); break;
        default: System.out.println("unknown state");
      }

    }
    System.out.println("Task " + task.getState());
    System.out.println(task.getResult().length());
  }
}
