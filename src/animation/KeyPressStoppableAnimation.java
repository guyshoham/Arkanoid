package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class KeyPressStoppableAnimation.
 *
 * @author Guy Shoham
 */
public class KeyPressStoppableAnimation implements Animation {

    private Animation decorated;
    private KeyboardSensor keyboard;
    private boolean isAlreadyPressed = true;
    private boolean stop = false;
    private String key;

    /**
     * Class Constructor.
     *
     * @param sensor    keyboard
     * @param key       key
     * @param decorated animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation decorated) {
        this.decorated = decorated;
        this.keyboard = sensor;
        this.key = key;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        decorated.doOneFrame(d);
        if (this.keyboard.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}