package animation;

import biuoop.DrawSurface;

/**
 * class DefaultAnimation.
 *
 * @author Guy Shoham
 */
public class DefaultAnimation implements Animation {

    private boolean stop;

    /**
     * Class Constructor.
     */
    public DefaultAnimation() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.stop = true;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
