package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class TestScreen extends KeyPressStoppableAnimation {

    public TestScreen(KeyboardSensor sensor, String key, Animation decorated) {
        super(sensor, key, decorated);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "TEST TEST TEST TEST ", 32);
        super.doOneFrame(d);
    }
}