package Listeners;

import game.Game;
import gameObjects.Ball;
import gameObjects.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHit();
        if (beingHit.getNumberOfHits() == 0) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            remainingBlocks.decrease(1);
        }
    }
}