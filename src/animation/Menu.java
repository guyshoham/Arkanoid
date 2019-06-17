package animation;

/**
 * interface Menu.
 *
 * @param <T> general type
 * @author Guy Shoham
 */
public interface Menu<T> extends Animation {

    /**
     * @param key       key
     * @param message   message
     * @param returnVal Task
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return status of menu
     */
    T getStatus();

    /**
     * @param key     key
     * @param message message
     * @param subMenu Menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * reset menu status.
     */
    void resetStatus();
}