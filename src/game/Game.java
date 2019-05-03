package game;

import Listeners.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import collisions.Collidable;
import gameObjects.Ball;
import gameObjects.Block;
import gameObjects.Paddle;
import geometry.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * class Game.
 *
 * @author Guy Shoham
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui = new GUI("Game Run", 800, 600);
    private KeyboardSensor keyboard = gui.getKeyboardSensor();
    private Counter blocksCounter, ballsCounter, score, lives;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;

    private static final int BALL_SPEED = 10;
    private static final int BALL_RADIUS = 5;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private boolean isPaddleExist;

    /**
     * class Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = new Counter();
        this.lives = new Counter();
        this.blockRemover = new BlockRemover(this, blocksCounter);
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.isPaddleExist = false;
    }

    /**
     * add the collidable object to the game environment.
     *
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * add the sprite object to the sprite collection.
     *
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove the sprite object from the sprite collection.
     *
     * @param s sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //init background
        Rectangle background = new Rectangle(new Point(0, 0), 800, 600);
        background.setColor(Color.BLUE.darker());
        background.addToGame(this);

        /*Ball ball3 = new Ball(new Point(400, 500), BALL_RADIUS, Color.CYAN);
        Velocity v3 = Velocity.fromAngleAndSpeed(60, BALL_SPEED);
        ball3.setVelocity(v3);
        ball3.setTopLeftCorner(new Point(0, 0));
        ball3.setBottomRightCorner(new Point(800, 600));
        ball3.addToGame(this);
        ball3.addHitListener(ballRemover);
        ballsCounter.increase(1);*/

        //init walls
        Rectangle livesRect = new Rectangle(new Point(0, 0), 100, 25);
        LivesIndicator livesIndicator = new LivesIndicator(livesRect, lives);
        livesIndicator.addToGame(this);

        Rectangle scoreRect = new Rectangle(new Point(100, 0), 700, 25);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreRect, score);
        scoreIndicator.addToGame(this);

        Rectangle rectTop = new Rectangle(new Point(0, 25), 800, 25);
        Rectangle rectLeft = new Rectangle(new Point(0, 25), 25, 600);
        Rectangle rectRight = new Rectangle(new Point(775, 25), 25, 600);
        Block blockTop = new Block(rectTop, Color.BLACK);
        Block blockLeft = new Block(rectLeft, Color.BLACK);
        Block blockRight = new Block(rectRight, Color.BLACK);
        blockTop.addToGame(this);
        blockLeft.addToGame(this);
        blockRight.addToGame(this);

        Rectangle rectBottom = new Rectangle(new Point(0, 590), 800, 25);
        Block blockBottom = new Block(rectBottom, Color.BLACK);
        blockBottom.addToGame(this);
        blockBottom.addHitListener(ballRemover);

        //init blocks
        for (int i = 3; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 100), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.GRAY, 2);
            block.addToGame(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
            blocksCounter.increase(1);
        }
        for (int i = 4; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 125), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.RED, 1);
            block.addToGame(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
            blocksCounter.increase(1);
        }
        for (int i = 5; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 150), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.YELLOW, 1);
            block.addToGame(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
            blocksCounter.increase(1);
        }
        for (int i = 6; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 175), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.BLUE, 1);
            block.addToGame(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
            blocksCounter.increase(1);
        }
        for (int i = 7; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 200), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.PINK, 1);
            block.addToGame(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
            blocksCounter.increase(1);
        }
        for (int i = 8; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 225), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.GREEN, 1);
            block.addToGame(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
            blocksCounter.increase(1);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public int playOneTurn() {
        //init 2 balls
        Ball ball1 = new Ball(new Point(400, 500), BALL_RADIUS, Color.YELLOW);
        Velocity v1 = Velocity.fromAngleAndSpeed(300, BALL_SPEED);
        ball1.setVelocity(v1);
        ball1.setTopLeftCorner(new Point(0, 0));
        ball1.setBottomRightCorner(new Point(800, 600));
        ball1.addToGame(this);
        ball1.addHitListener(ballRemover);
        ballsCounter.increase(1);

        Ball ball2 = new Ball(new Point(400, 500), BALL_RADIUS, Color.MAGENTA);
        Velocity v2 = Velocity.fromAngleAndSpeed(0, BALL_SPEED);
        ball2.setVelocity(v2);
        ball2.setTopLeftCorner(new Point(0, 0));
        ball2.setBottomRightCorner(new Point(800, 600));
        ball2.addToGame(this);
        ball2.addHitListener(ballRemover);
        ballsCounter.increase(1);

        Ball.setEnvironment(environment);

        //init paddle if needed
        if (!isPaddleExist) {
            Paddle paddle = new Paddle(new Rectangle(new Point(350, 550), 100, 25), Color.GRAY, keyboard);
            paddle.addToGame(this);
            isPaddleExist = true;
        }

        Sleeper sleeper = new Sleeper();

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            if (blocksCounter.getValue() == 0) {
                score.increase(100);
                return 1;
            }
            if (ballsCounter.getValue() == 0) {
                return 0;
            }

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    public void run() {
        lives.increase(4);
        while (lives.getValue() != 0) {
            if (playOneTurn() == 0) {
                lives.decrease(1);
            } else {
                gui.close();
                return;
            }
        }
        gui.close();
    }
}