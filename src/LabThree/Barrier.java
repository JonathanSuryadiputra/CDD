/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabThree;

import java.util.concurrent.Semaphore;

/**
 * This is a lab to study the implementation of Semaphores as a synchronisation tool.
 * This lab sheet contains code that implements a Barrier class in multiple threads to synchronise the execution of the threads. The Barrier is a reusable barrier implemented using only Semaphores.
 *
 * @author              Jonathan Suryadiputra
 * @version             1.0
 * @since               2021-10-18
 */
public class Barrier implements Runnable {
    private int totalThreads;
    private int arrivedSoFar;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore turnsTile1 = new Semaphore(1);
    private Semaphore turnsTile2 = new Semaphore(0);
    private String name;

    public Barrier(String name, int totalThreads) {
        this.name = name;
        this.totalThreads = totalThreads;
        this.arrivedSoFar = 0;
    }

    public void run() {
        try {
            // first gate
            mutex.acquire();
            arrivedSoFar++;
            if (arrivedSoFar == totalThreads) {
                turnsTile2.acquire(); // lock the second
                turnsTile1.release(); // unlock the first
            }
            mutex.release();
            turnsTile1.acquire(); // first gate locked
            turnsTile1.release();
            // first gate ends
            // critical section
            for (int i = 0; i < 4; i++) {
                System.out.println(this.name + " running...");
                Thread.sleep(2000);
            }
            // second gate
            mutex.acquire();
            arrivedSoFar--;
            if (arrivedSoFar == 0) {
                turnsTile1.acquire(); // lock the first
                turnsTile2.release(); // unlock the second
            }
            mutex.release();
            turnsTile2.acquire(); // second gate locked
            turnsTile2.release();
            // second gate ends
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}