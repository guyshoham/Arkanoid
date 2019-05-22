package backend;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.DefaultAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.Collidable;
import gameobjects.Ball;
import gameobjects.Block;
import geometry.Velocity;
import indicators.LevelNameIndicator;
import indicators.LivesIndicator;
import gameobjects.Paddle;
import indicators.ScoreIndicator;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
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
    private KeyboardSensor keyboard;
    private Counter blocksCounter, ballsCounter, score, lives;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private LevelInformation info;
    private Paddle paddle;

    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int BALL_RADIUS = 5;
    private boolean isPaddleExist;

    /**
     * Class Constructor.
     *
     * @param info     level information
     * @param keyboard keyboard
     * @param runner   animation runner
     * @param score    score counter
     * @param lives    lives counter
     */
    public GameLevel(LevelInformation info, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.lives = lives;
        this.blockRemover = new BlockRemover(this, blocksCounter);
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.isPaddleExist = false;
        this.runner = runner;
        this.info = info;
        this.keyboard = keyboard;
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
     * Initialize a new game: create the background, walls, and blocks.
     */
    public void initialize() {
        //init background
        Sprite background = info.getBackground();
        background.addToGame(this);

        //init lives rect
        Rectangle livesRect = new Rectangle(new Point(0, 0), 100, 25);
        LivesIndicator livesIndicator = new LivesIndicator(livesRect, lives);
        livesIndicator.addToGame(this);

        //init score rect
        Rectangle scoreRect = new Rectangle(new Point(100, 0), 400, 25);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreRect, score);
        scoreIndicator.addToGame(this);

        //init level name rect
        Rectangle levelNameRect = new Rectangle(new Point(500, 0), 300, 25);
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(levelNameRect, info.levelName());
        levelNameIndicator.addToGame(this);

        //init walls
        Rectangle rectTop = new Rectangle(new Point(0, 25), 800, 25);
        Rectangle rectLeft = new Rectangle(new Point(0, 25), 25, 600);
        Rectangle rectRight = new Rectangle(new Point(775, 25), 25, 600);
        Block blockTop = new Block(rectTop, Color.GRAY);
        Block blockLeft = new Block(rectLeft, Color.GRAY);
        Block blockRight = new Block(rectRight, Color.GRAY);
        blockTop.addToGame(this);
        blockLeft.addToGame(this);
        blockRight.addToGame(this);

        //init death region block
        Rectangle rectBottom = new Rectangle(new Point(0, 600), 800, 25);
        Block blockBottom = new Block(rectBottom, Color.BLACK);
        blockBottom.addToGame(this);
        blockBottom.addHitListener(ballRemover);

        //init blocks
        for (Block block : info.blocks()) {
            block.addToGame(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
        }
        blocksCounter.increase(info.numberOfBlocksToRemove());
    }

    /**
     * play one turn -- start the animation loop.
     * finish when no balls left or no blocks left.
     */
    public void playOneTurn() {
        this.createBallOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts.
        this.running = true;
        this.runner.run(this);
    }

    /**
     * created the paddle and the balls before the level starts.
     * if the paddle is already exist, we move it to the middle.
     */
    private void createBallOnTopOfPaddle() {
        //init paddle if needed
        if (isPaddleExist) {
            paddle.removeFromGame(this);
        }
        paddle = new Paddle(new Rectangle(new Point(25 + ((750 - info.paddleWidth()) / 2), 550),
                info.paddleWidth(), 20), Color.orange, info.paddleSpeed(), keyboard);
        paddle.addToGame(this);
        isPaddleExist = true;

        //init balls
        for (int i = 0; i < info.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 500), BALL_RADIUS, Color.WHITE);
            Velocity v = info.initialBallVelocities().get(i);
            ball.setVelocity(v);
            ball.setTopLeftCorner(new Point(0, 0));
            ball.setBottomRightCorner(new Point(GUI_WIDTH, GUI_HEIGHT));
            ball.addToGame(this);
            ball.addHitListener(ballRemover);
            ballsCounter.increase(1);
        }

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
            this.runner.run(new PauseScreen(this.keyboard, KeyboardSensor.SPACE_KEY, new DefaultAnimation()));
        }
        if (blocksCounter.getValue() == 0 || ballsCounter.getValue() == 0) {
            running = false;
        }
    }

    /**
     * Run the game and finish after 7 lives has been played or level is finish.
     */
    public void run() {
        //play while you still have lives.
        while (lives.getValue() != 0) {
            playOneTurn();
            if (ballsCounter.getValue() == 0) {
                lives.decrease(1);
            } else {
                //you finish the level.
                score.increase(100);
                return;
            }
        }
    }
}