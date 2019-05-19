package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public abstract class KeyPressStoppableAnimation implements Animation {

    private Animation decorated;
    private KeyboardSensor keyboard;
    private boolean isAlreadyPressed = true;
    private boolean stop;
    private String key;

    protected KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation decorated) {
        this.decorated = decorated;
        this.keyboard = sensor;
        this.key = key;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!isAlreadyPressed) {
            if (this.keyboard.isPressed(key)) {
                this.stop = true;
            }
        } else {
            if (!this.keyboard.isPressed(key)) {
                this.isAlreadyPressed = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}