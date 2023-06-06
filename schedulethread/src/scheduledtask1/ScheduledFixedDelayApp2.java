package scheduledtask1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledFixedDelayApp2 {

	public static void main(String[] args) {
		
		System.out.println("Demonstration of a scheduled tasks at a fixed "
				+ "delay time.");
		
		// Get a pool to schedule threads
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		// Formatting current date
		String pattern = "dd/MM/yyyy HH:mm:ss";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println("Task scheduled to execute after 10 seconds at : " 
				+ dtf.format(now));

		// Create task and submit to pool
		Runnable task1 = new Task("App-Task1");
		Runnable task2 = new Task("App-Task2");
		ScheduledFuture<?> result1 = executor.scheduleWithFixedDelay(task1, 10, 10, TimeUnit.SECONDS);
		ScheduledFuture<?> result2 = executor.scheduleWithFixedDelay(task2, 10, 10, TimeUnit.SECONDS);

		System.out.println("Shutdown and await requested at : " + dtf.format(now));


		//shutdownAndAwaitTermination(executor);

	}
	
	static void shutdownAndAwaitTermination(ExecutorService executorService) {
		
		executorService.shutdown();
		
		try {
			
			if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
				executorService.shutdownNow();
			}
			
		} catch (InterruptedException ie) {
			executorService.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

}
