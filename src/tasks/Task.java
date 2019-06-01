package tasks;

/**
 * interface Task.
 *
 * @author Guy Shoham
 */
public interface Task<T> {
    T run();
}