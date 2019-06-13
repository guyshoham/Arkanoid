package animation;

/**
 * interface Menu.
 *
 * @author Guy Shoham
 */
public interface Menu<T> extends Animation {
    void addSelection(String key, String message, T returnVal);

    T getStatus();

    void addSubMenu(String key, String message, Menu<T> subMenu);

    void resetStatus();
}