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
class Queue {
    int leaders = 0;
    int followers = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore leaderQ = new Semaphore(0);
    Semaphore followerQ = new Semaphore(0);
    Semaphore rendezvous = new Semaphore(0);

    Queue() {
        // constructor
    }

    void dance(String objName) {
        System.out.print(objName);
    }

    public void waitForLeader(String follower_name) throws InterruptedException {
        mutex.acquire();
        if (leaders > 0) {
            leaders --;
            leaderQ.release();
        }
        else {
            followers ++;
            mutex.release();
            followerQ.acquire();
        }
        dance(follower_name);
        rendezvous.release();
    }

    public void waitForFollower(String leader_name) throws InterruptedException {
        mutex.acquire();
        if (followers > 0) {
            followers --;
            followerQ.release();
        }
        else {
            leaders ++;
            mutex.release();
            leaderQ.acquire();
        }
        dance(leader_name);
        rendezvous.acquire();
        mutex.release();
    }
}
