package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class PauseScreen extends KeyPressStoppableAnimation implements Animation {

    public PauseScreen(KeyboardSensor k) {
        super(k, KeyboardSensor.SPACE_KEY);
    }

    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        super.doOneFrame(d);
    }

    public boolean shouldStop() {
        return super.shouldStop();
    }
}