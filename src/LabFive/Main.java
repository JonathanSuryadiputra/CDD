/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabFive;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author              Jonathan Suryadiputra
 * @version             1.0
 * @since               2021-11-1
 * @shortDescription
 * @longDescription
 */
public class Main {
    
      // Maximum number of threads in thread pool
    static final int MAX_T = 8;
  
    public static void main(String[] args)
    {
        Queue queue = new Queue();
        // creates five tasks
        Runnable r1 = new Follower("A", queue);
        Runnable r2 = new Follower("B", queue);
        Runnable r3 = new Leader("1", queue);
        Runnable r4 = new Leader("2", queue);
        Runnable r5 = new Follower("C", queue);
        Runnable r6 = new Follower("D", queue);
        Runnable r7 = new Leader("3", queue);
        Runnable r8 = new Leader("4", queue);
          
        // creates a thread pool with MAX_T no. of 
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);  
         
        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);
        pool.execute(r6);
        pool.execute(r7);
        pool.execute(r8);
          
        // pool shutdown ( Step 4)
        pool.shutdown();
    }
}
