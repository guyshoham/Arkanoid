package levels;

import geometry.Velocity;
import backgrounds.BackgroundFinalFour;
import gameobjects.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class LevelFinalFour.
 *
 * @author Guy Shoham
 */
public class LevelFinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(60, 5));
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        velocities.add(Velocity.fromAngleAndSpeed(300, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundFinalFour();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.green, Color.WHITE, Color.PINK, Color.CYAN};

        //init blocks
        for (int row = 0; row < 7; row++) {
            for (int b = 0; b < 15; b++) {
                Rectangle rect = new Rectangle(new Point(25 + b * 50, 100 + row * 25),
                        50, 25);
                Block block = new Block(rect, colors[row]);
                block.setNumberOfHits(1);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
