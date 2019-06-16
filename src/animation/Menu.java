package animation;

/**
 * interface Menu.
 *
 * @param <T> general type
 * @author Guy Shoham
 */
public interface Menu<T> extends Animation {
    void addSelection(String key, String message, T returnVal);

    T getStatus();

    void addSubMenu(String key, String message, Menu<T> subMenu);

    void resetStatus();
}