package LabSeven;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author              Jonathan Suryadiputra
 * @version             1.0
 * @since               2021-11-8
 * @shortDescription
 * @longDescription
 */

public class Main {
    // Maximum number of threads in thread pool
    static final int MAX_T = 5;

    public static void main(String[] args) {
        Table table = new Table(MAX_T);
        // creates tasks
        Runnable r1 = new Philosopher(0, table);
        Runnable r2 = new Philosopher(1, table);
        Runnable r3 = new Philosopher(2, table);
        Runnable r4 = new Philosopher(3, table);
        Runnable r5 = new Philosopher(4, table);

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Buffer objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // pool shutdown ( Step 4)
        pool.shutdown();
    }
}
