package tasks;


/**
 * interface Task.
 *
 * @param <T> general type
 * @author Guy Shoham
 */
public interface Task<T> {
    /**
     * doing task inside function.
     *
     * @return null
     */
    T run();
}