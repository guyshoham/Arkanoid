package backend;

/**
 * class Counter.
 *
 * @author Guy Shoham
 */
public class Counter {
    private int count;


    /**
     * class constructor.
     *
     * @param num starting number.
     */
    public Counter(int num) {
        this.count = num;
    }

    /**
     * class constructor.
     */
    public Counter() {
        this(0);
    }

    /**
     * add number to current count.
     *
     * @param number number.
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number number.
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return count;
    }
}