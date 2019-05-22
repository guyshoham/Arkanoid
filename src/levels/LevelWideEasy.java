package levels;

import geometry.Velocity;
import backgrounds.BackgroundWideEasy;
import gameobjects.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class LevelWideEasy.
 *
 * @author Guy Shoham
 */
public class LevelWideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(10, 7));
        velocities.add(Velocity.fromAngleAndSpeed(20, 7));
        velocities.add(Velocity.fromAngleAndSpeed(30, 7));
        velocities.add(Velocity.fromAngleAndSpeed(40, 7));
        velocities.add(Velocity.fromAngleAndSpeed(50, 7));
        velocities.add(Velocity.fromAngleAndSpeed(350, 7));
        velocities.add(Velocity.fromAngleAndSpeed(340, 7));
        velocities.add(Velocity.fromAngleAndSpeed(330, 7));
        velocities.add(Velocity.fromAngleAndSpeed(320, 7));
        velocities.add(Velocity.fromAngleAndSpeed(310, 7));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundWideEasy();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        Color[] colors = {Color.RED, Color.RED,
                Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN,
                Color.BLUE, Color.BLUE,
                Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};

        //init blocks
        for (int b = 0; b < 15; b++) {
            Rectangle rect = new Rectangle(new Point(25 + b * 50, 275),
                    50, 25);
            Block block = new Block(rect, colors[b]);
            block.setNumberOfHits(1);
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
