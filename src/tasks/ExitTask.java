package tasks;

/**
 * class ExitTask.
 *
 * @author Guy Shoham
 */

public class ExitTask implements Task<Void> {

    public ExitTask() {
    }

    public Void run() {
        System.exit(0);
        return null;
    }
}