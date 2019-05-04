package backend;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import collisions.Collidable;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.LivesIndicator;
import gameobjects.Paddle;
import gameobjects.ScoreIndicator;
import geometry.Background;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
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
    private GUI gui = new GUI("Game Run", GUI_WIDTH, GUI_HEIGHT);
    private KeyboardSensor keyboard = gui.getKeyboardSensor();
    private Counter blocksCounter, ballsCounter, score, lives;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;

    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
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

    /**
     * remove the collidable object from the game environment.
     *
     * @param c collidable.
     */
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
        Background background = new Background(new Point(0, 0), GUI_WIDTH, GUI_HEIGHT);
        background.addToGame(this);

        //init walls, lives rect and score rect
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

        //init death region block
        Rectangle rectBottom = new Rectangle(new Point(0, 590), 800, 25);
        Block blockBottom = new Block(rectBottom, Color.BLACK);
        blockBottom.addToGame(this);
        blockBottom.addHitListener(ballRemover);

        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};

        //init blocks
        for (int row = 0; row < 6; row++) {
            for (int b = 3 + row; b < 15; b++) {
                Rectangle rect = new Rectangle(new Point(25 + b * BLOCK_WIDTH, 100 + row * 25), BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rect, colors[row]);
                if (row == 0) {
                    block.setNumberOfHits(2);
                } else {
                    block.setNumberOfHits(1);
                }
                block.addToGame(this);
                block.addHitListener(scoreTrackingListener);
                block.addHitListener(blockRemover);
                blocksCounter.increase(1);
            }
        }
    }

    /**
     * play one turn -- start the animation loop.
     * finish when no balls left or no blocks left.
     *
     * @return 1 if turn ended because of no blocks left, 0 if turn ended because of no balls left.
     */
    public int playOneTurn() {
        //init 2 balls
        Ball ball1 = new Ball(new Point(400, 500), BALL_RADIUS, Color.YELLOW);
        Velocity v1 = Velocity.fromAngleAndSpeed(300, BALL_SPEED);
        ball1.setVelocity(v1);
        ball1.setTopLeftCorner(new Point(0, 0));
        ball1.setBottomRightCorner(new Point(GUI_WIDTH, GUI_HEIGHT));
        ball1.addToGame(this);
        ball1.addHitListener(ballRemover);
        ballsCounter.increase(1);

        Ball ball2 = new Ball(new Point(400, 500), BALL_RADIUS, Color.MAGENTA);
        Velocity v2 = Velocity.fromAngleAndSpeed(0, BALL_SPEED);
        ball2.setVelocity(v2);
        ball2.setTopLeftCorner(new Point(0, 0));
        ball2.setBottomRightCorner(new Point(GUI_WIDTH, GUI_HEIGHT));
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
                System.out.println(score.getValue());
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

    /**
     * Run the game and finish after 4 lives has been played or level is finish.
     */
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