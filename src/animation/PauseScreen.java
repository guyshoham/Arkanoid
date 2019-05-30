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
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}