package LabSix;

public class Producer implements Runnable {
    private Buffer queue;
    private String eventLabel;

    public Producer(String eventLabel, Buffer queue) {
        this.queue = queue;
        this.eventLabel = eventLabel;
        try {
            this.queue.mutex.acquire();
            this.queue.numOfProducers++;
            this.queue.mutex.release();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            queue.produce(this.eventLabel);
            this.queue.mutex.acquire();
            this.queue.numOfProducers--;
            this.queue.mutex.release();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
