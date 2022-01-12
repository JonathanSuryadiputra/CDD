package LabSix;

public class Consumer implements Runnable {
    private Buffer queue;

    public Consumer(Buffer queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.consume();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}