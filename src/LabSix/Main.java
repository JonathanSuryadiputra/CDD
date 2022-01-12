/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabSix;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This lab demonstrates the producer and consumer problem as well as its solution.
 *
 * @author              Jonathan Suryadiputra
 * @version             1.0
 * @since               2021-11-8
 */
public class Main {
    // Maximum number of threads in thread pool
    static final int MAX_T = 8;
  
    public static void main(String[] args)
    {
        Buffer queue = new Buffer();
        // creates tasks
        Runnable r1 = new Producer("A", queue);
        Runnable r2 = new Producer("B", queue);
        Runnable r3 = new Producer("C", queue);
        Runnable r4 = new Producer("D", queue);
        Runnable r5 = new Consumer(queue);
        Runnable r6 = new Consumer(queue);
        Runnable r7 = new Consumer(queue);
        Runnable r8 = new Consumer(queue);
          
        // creates a thread pool with MAX_T no. of 
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);  
         
        // passes the Buffer objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);
        pool.execute(r6);
        pool.execute(r7);
        pool.execute(r8);

        //System.exit(0);

        // pool shutdown ( Step 4)
        pool.shutdown();
    }
}