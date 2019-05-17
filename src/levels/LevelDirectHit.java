package levels;

import backend.LevelInformation;
import backend.Velocity;
import gameobjects.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class LevelDirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 3));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        Rectangle rect = new Rectangle(new Point(385, 200), 30, 30);
        Block b = new Block(rect, Color.RED);
        b.setNumberOfHits(1);
        blocks.add(b);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
