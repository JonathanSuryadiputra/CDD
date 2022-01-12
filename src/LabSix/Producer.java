package LabSix;

public class Producer implements Runnable {
    private Buffer queue;
    private String eventLabel;

    public Producer(String eventLabel, Buffer queue) {
        this.queue = queue;
        this.eventLabel = eventLabel;
    }

    public void run() {
        try {
            queue.produce(this.eventLabel);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}