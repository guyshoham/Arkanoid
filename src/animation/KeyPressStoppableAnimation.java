package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private boolean stop;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key) {
        this.keyboard = sensor;
        this.key = key;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed(key)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}