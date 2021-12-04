/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabFive;

import java.util.concurrent.Semaphore;

/**
 *
 * @author joe
 */
public class Follower implements Runnable {
    private String name;
    private Queue queue;

    public Follower(String follower_name, Queue queue) {
        name = follower_name;
        this.queue = queue;
    }
    
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.waitForLeader(name);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
