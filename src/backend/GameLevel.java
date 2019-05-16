package backend;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
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
 * class GameLevel.
 *
 * @author Guy Shoham
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
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
    private static final int BALL_SPEED = 9;
    private static final int BALL_RADIUS = 5;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int WIN = 1;
    private static final int LOSE = 0;
    private boolean isPaddleExist;

    /**
     * class Constructor.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = new Counter();
        this.lives = new Counter(10);
        this.blockRemover = new BlockRemover(this, blocksCounter);
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.isPaddleExist = false;
        this.runner = new AnimationRunner(gui);
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
                Rectangle rect = new Rectangle(new Point(25 + b * BLOCK_WIDTH, 100 + row * 25),
                        BLOCK_WIDTH, BLOCK_HEIGHT);
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
     */
    public void playOneTurn() {
        this.createBallOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;
        this.runner.run(this);
    }

    private void createBallOnTopOfPaddle() {
        //init paddle if needed
        if (!isPaddleExist) {
            Paddle paddle = new Paddle(new Rectangle(new Point(350, 550), 100, 25), Color.GRAY, keyboard);
            paddle.addToGame(this);
            isPaddleExist = true;
        }

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
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (blocksCounter.getValue() == 0 || ballsCounter.getValue() == 0) {
            running = false;
        }
    }

    /**
     * Run the game and finish after 4 lives has been played or level is finish.
     */
    public void run() {
        //play while you still have lives.
        while (lives.getValue() != 0) {
            playOneTurn();
            if (ballsCounter.getValue() == 0) {
                lives.decrease(1);
            } else {
                //you win the game.
                score.increase(100);
                System.out.println("You Win! Score: " + score.getValue());
                gui.close();
                return;
            }
        }
        //you lost all your lives, game over.
        System.out.println("Game Over! Score: " + score.getValue());
        gui.close();
    }
}