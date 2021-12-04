package LabSix;

public class Event {
    String name;
    public Event(String name) {
        this.name = name;
    }

    public void process() {
        System.out.println(this.name.toLowerCase());
    }
}
