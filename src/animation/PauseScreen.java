package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class PauseScreen extends KeyPressStoppableAnimation {

    public PauseScreen(KeyboardSensor sensor, String key, Animation decorated) {
        super(sensor, key, decorated);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        super.doOneFrame(d);
    }
}