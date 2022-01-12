package LabSix;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Buffer {
    int maxBufferSize = 10;
    Semaphore mutex = new Semaphore(1);
    Semaphore items = new Semaphore(0);
    Semaphore spaces = new Semaphore(maxBufferSize);
    LinkedList<Event> buffer = new LinkedList<Event>();
    int numInBuffer = 0;

    public Buffer() { }

    public void produce(String eventLabel) throws InterruptedException {
        Event event = new Event(eventLabel);
        spaces.acquire();
        mutex.acquire();
        buffer.addFirst(event);
        System.out.println(buffer.getFirst().name);
        mutex.release();
        items.release();
    }

    public void consume() throws InterruptedException {
        items.acquire();
        mutex.acquire();
        Event event = buffer.removeLast();
        mutex.release();
        spaces.release();
        event.process();
    }
}