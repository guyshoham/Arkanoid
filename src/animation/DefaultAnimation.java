package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class DefaultAnimation implements Animation {

    private boolean stop;
    private String key;
    private KeyboardSensor keyboard;

    public DefaultAnimation(KeyboardSensor sensor, String key) {
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
