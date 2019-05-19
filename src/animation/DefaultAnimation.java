package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class DefaultAnimation implements Animation {

    private boolean stop;
    private String key;
    private KeyboardSensor keyboard;
    private boolean isAlreadyPressed = true;

    public DefaultAnimation(KeyboardSensor sensor, String key) {
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
        }
        else{
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
