package Listeners;

import game.Game;
import gameObjects.Ball;
import gameObjects.Block;

public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    public BallRemover(Game game, Counter numOfBalls) {
        this.game = game;
        this.remainingBalls = numOfBalls;
    }

    // Balls that are out of bounds should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        hitter.removeHitListener(this);
        remainingBalls.decrease(1);
        System.out.println("A ball removed");
        if (remainingBalls.getValue() == 0) {
            System.out.println("You lost a life");
        }
    }
}
