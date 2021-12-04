package LabSix;

public class Consumer implements Runnable {
    private Buffer queue;

    public Consumer(Buffer queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.consume();
            if (queue.buffer.size() == 0 && queue.numOfProducers == 0) {
                return;
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
