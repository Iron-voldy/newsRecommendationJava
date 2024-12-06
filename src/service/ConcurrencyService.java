package service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyService {

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);  // Pool of 4 threads

    // Execute tasks concurrently
    public static void executeTask(Runnable task) {
        executor.execute(task);
    }

    // Shutdown the executor service gracefully when done
    public static void shutdown() {
        executor.shutdown();
    }
}
