package listeners;

import backend.Counter;
import backend.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;

/**
 * class BallRemover.
 *
 * @author Guy Shoham
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * class constructor.
     *
     * @param game       game
     * @param numOfBalls counter
     */
    public BallRemover(GameLevel game, Counter numOfBalls) {
        this.game = game;
        this.remainingBalls = numOfBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        hitter.removeHitListener(this);
        remainingBalls.decrease(1);
    }
}
