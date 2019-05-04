package listeners;

import gameobjects.Ball;
import gameobjects.Block;

/**
 * interface HitListener.
 *
 * @author Guy Shoham
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit block
     * @param hitter   ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}