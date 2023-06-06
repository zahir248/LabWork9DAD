// activity 7.2

package scheduledtask1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledSQLStatementGenerator {

    public static void main(String[] args) {
        // Create a scheduled executor service with a pool of one thread
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Schedule the generation of SQL statements as delayed tasks
        scheduleTable1(executor, 5);  // Schedule table1 after 5 seconds
        scheduleTable2(executor, 10); // Schedule table2 after 10 seconds
        scheduleTable3(executor, 15); // Schedule table3 after 15 seconds
        scheduleTable4(executor, 20); // Schedule table4 after 20 seconds

        // Shutdown the executor after all tasks are executed
        executor.shutdown();
    }

    private static void scheduleTable1(ScheduledExecutorService executor, int delaySeconds) {
        executor.schedule(() -> {
            // Define table name and fields
            String tableName = "table1";
            List<String> fields = new ArrayList<>();
            fields.add("id INT PRIMARY KEY");
            fields.add("name VARCHAR(255)");
            fields.add("age INT");

            // Generate CREATE TABLE statement
            String createTableStatement = generateCreateTableStatement(tableName, fields);
            System.out.println(createTableStatement);
        }, delaySeconds, TimeUnit.SECONDS);
    }

    private static void scheduleTable2(ScheduledExecutorService executor, int delaySeconds) {
        executor.schedule(() -> {
            // Define table name and fields
            String tableName = "table2";
            List<String> fields = new ArrayList<>();
            fields.add("id INT PRIMARY KEY");
            fields.add("email VARCHAR(255)");
            fields.add("phone VARCHAR(20)");

            // Generate CREATE TABLE statement
            String createTableStatement = generateCreateTableStatement(tableName, fields);
            System.out.println(createTableStatement);
        }, delaySeconds, TimeUnit.SECONDS);
    }

    private static void scheduleTable3(ScheduledExecutorService executor, int delaySeconds) {
        executor.schedule(() -> {
            // Define table name and fields
            String tableName = "table3";
            List<String> fields = new ArrayList<>();
            fields.add("id INT PRIMARY KEY");
            fields.add("title VARCHAR(100)");
            fields.add("author VARCHAR(100)");
            fields.add("year INT");

            // Generate CREATE TABLE statement
            String createTableStatement = generateCreateTableStatement(tableName, fields);
            System.out.println(createTableStatement);
        }, delaySeconds, TimeUnit.SECONDS);
    }

    private static void scheduleTable4(ScheduledExecutorService executor, int delaySeconds) {
        executor.schedule(() -> {
            // Define table name and fields
            String tableName = "table4";
            List<String> fields = new ArrayList<>();
            fields.add("id INT PRIMARY KEY");
            fields.add("product_name VARCHAR(255)");
            fields.add("price DECIMAL(10, 2)");
            fields.add("quantity INT");

            // Generate CREATE TABLE statement
            String createTableStatement = generateCreateTableStatement(tableName, fields);
            System.out.println(createTableStatement);
        }, delaySeconds, TimeUnit.SECONDS);
    }

    private static String generateCreateTableStatement(String tableName, List<String> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(tableName).append(" (");
        for (int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i));
            if (i < fields.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(");");
        return sb.toString();
    }
}