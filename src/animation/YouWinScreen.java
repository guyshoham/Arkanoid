package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class YouWinScreen.
 *
 * @author Guy Shoham
 */
public class YouWinScreen extends KeyPressStoppableAnimation {
    private int score;

    /**
     * Class Constructor.
     *
     * @param sensor keyboard
     * @param key key
     * @param score score
     * @param decorated animation
     */
    public YouWinScreen(KeyboardSensor sensor, String key, int score, Animation decorated) {
        super(sensor, key, decorated);
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your Score is " + score, 32);
        super.doOneFrame(d);
    }
}