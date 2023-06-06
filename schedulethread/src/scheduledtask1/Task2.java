// activity 4.1

package scheduledtask1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.Callable;

public class Task2 implements Callable<Integer> {

    private String taskName;

    public Task2(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public Integer call() throws Exception {
        // Display task name and execution time
        LocalDateTime now = LocalDateTime.now();
        String pattern = "dd/MM/yyyy HH:mm:ss";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        System.out.println("Task " + taskName + " executed on: " + dtf.format(now));

        // Generate 100 random integers and compute the summation
        Random random = new Random();
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            int randomNumber = random.nextInt();
            sum += randomNumber;
        }

        // Display the result of the computation
        System.out.println("Sum of 100 random numbers for task " + taskName + ": " + sum);

        return sum;
    }
}
