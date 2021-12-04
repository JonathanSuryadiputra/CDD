/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabThree;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author              Jonathan Suryadiputra
 * @version             1.0
 * @since               2021-10-18
 * @shortDescription    This is a lab to study the implementation of Semaphores as a synchronisation tool.
 * @longDescription     This lab sheet contains code that implements a Barrier class in multiple threads to synchronise the execution of the threads. The Barrier is a reusable barrier implemented using only Semaphores.
 */
public class Main {
    // Maximum number of threads in thread pool
    static final int MAX_T = 4;
    public static void main(String[] args) {
        // creates four threads executing the Barrier class (Step 1)
        Runnable r1 = new Barrier("task 1", MAX_T);
        Runnable r2 = new Barrier("task 2", MAX_T);
        Runnable r3 = new Barrier("task 3", MAX_T);
        Runnable r4 = new Barrier("task 4", MAX_T);
          
        // creates a thread pool with MAX_T no. of 
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
         
        // passes the Barrier objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);

        // pool shutdown ( Step 4)
        pool.shutdown();
    }
}
