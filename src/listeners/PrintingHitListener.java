package listeners;

import gameobjects.Ball;
import gameobjects.Block;

/**
 * class PrintingHitListener.
 *
 * @author Guy Shoham
 */
public class PrintingHitListener implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}