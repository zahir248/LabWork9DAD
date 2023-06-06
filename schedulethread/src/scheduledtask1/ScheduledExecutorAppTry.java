// activity 4.2

package scheduledtask1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class ScheduledExecutorAppTry {

    public static void main(String[] args) {

        System.out.println("Demonstration of a scheduled task.");

        // Get a pool to schedule threads
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

        // Formatting current date
        String pattern = "dd/MM/yyyy HH:mm:ss";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();

        // Display current time to mark the execution from the main( )
        System.out.println("Task scheduled to execute after 5 seconds at: " + dtf.format(now));

        // Execute task 5 seconds after the application starts
        Task2 task = new Task2("App-Task");
        ScheduledFuture<Integer> result = scheduledExecutor.schedule(task, 5, TimeUnit.SECONDS);

        try {
            // Get the result of the task
            Integer sum = result.get();
            if (sum != null) {
                System.out.println("Result of Task2: " + sum);
            } else {
                System.out.println("Task2 did not return a valid result.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Shutdown and await requested at: " + dtf.format(now));

        // Shutdown the executor service
        scheduledExecutor.shutdown();

        try {
            // Wait for the tasks to terminate
            if (!scheduledExecutor.awaitTermination(1, TimeUnit.MINUTES)) {
                // Cancel currently executing tasks
                scheduledExecutor.shutdownNow();
                // Wait for the tasks to respond to cancellation
                if (!scheduledExecutor.awaitTermination(1, TimeUnit.MINUTES)) {
                    System.err.println("Executor service did not terminate.");
                }
            }
        } catch (InterruptedException e) {
            scheduledExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
