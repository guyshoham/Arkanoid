package animation;

import biuoop.DrawSurface;

/**
 * class PauseScreen.
 *
 * @author Guy Shoham
 */
public class PauseScreen implements Animation {

    /**
     * Class Constructor.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(330, 250, "Paused", 40);
        d.drawText(250, 500, "Press Space to Continue", 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}