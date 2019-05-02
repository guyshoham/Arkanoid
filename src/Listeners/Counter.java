package Listeners;

public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    // add number to current count.
    public void increase(int number) {
        count += number;
    }

    // subtract number from current count.
    public void decrease(int number) {
        count -= number;
    }

    // get current count.
    public int getValue() {
        return count;
    }
}