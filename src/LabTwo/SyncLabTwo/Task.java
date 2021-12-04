/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabTwo.SyncLabTwo;

/**
 * @author  Jonathan Suryadiputra
 * @version 1.0
 * @since   2021-10-11
 * @shortDescription This is a lab to study the different types of locks to prevent race condition
 * @longDescription This lab sheet outlines 3 different types of locks to prevent race condition, namely Atomic Variables, Mutex Locks, as well as the 'synchronized' keyword built in Java. The task is to fix the race condition found in the raw code provided.
 */
public class Task implements Runnable {
private String name;
    private IntegerObj total;
    public Task(String task_1, IntegerObj total) {
        name=task_1;
        this.total = total;
    }

    public void run()
    {
        try
        {
            for (int i = 0; i<500; i++)
            {
                total.inc();
                if (i%100==0){
                   Thread.sleep(100); 
                }
                
            }
            System.out.println(name+" complete");
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
