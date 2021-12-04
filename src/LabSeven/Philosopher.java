package LabSeven;

public class Philosopher implements Runnable {
    private int number;
    private Table table;

    public Philosopher(int number, Table table) {
        this.number = number;
        this.table = table;
    }

    public void run() {
        try {
            table.getForks(number);
            System.out.println("Philosopher " + number + " is eating.");
            table.putForks(number);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
