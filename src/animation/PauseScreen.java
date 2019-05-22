package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class PauseScreen.
 *
 * @author Guy Shoham
 */
public class PauseScreen extends KeyPressStoppableAnimation {

    /**
     * Class Constructor.
     *
     * @param sensor keyboard
     * @param key key
     * @param decorated animation
     */
    public PauseScreen(KeyboardSensor sensor, String key, Animation decorated) {
        super(sensor, key, decorated);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        super.doOneFrame(d);
    }
}