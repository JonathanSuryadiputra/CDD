package LabSeven;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Table {
    private LinkedList<Semaphore> forks = new LinkedList<Semaphore>();
    private Semaphore footman = new Semaphore(4);

    public Table(int numOfForks) {
        for (int i = 0; i < numOfForks; i++) {
            Semaphore fork = new Semaphore(1);
            forks.add(fork);
        }
    }

    public int left(int index) {
        return index;
    }

    public int right(int index) {
        return (index+1) % 5;
    }

    public void getForks(int philIndex) throws InterruptedException {
        footman.acquire();
        forks.get(left(philIndex)).acquire();
        forks.get(right(philIndex)).acquire();
    }

    public void putForks(int philIndex) throws InterruptedException {
        forks.get(left(philIndex)).release();
        forks.get(right(philIndex)).release();
        footman.release();
    }

}
