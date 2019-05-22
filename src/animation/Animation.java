package animation;

import biuoop.DrawSurface;

/**
 * interface Animation.
 *
 * @author Guy Shoham
 */
public interface Animation {

    /**
     * handling the logic and stopping condition of the animation.
     *
     * @param d surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}