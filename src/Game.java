import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui = new GUI("Game Run", 800, 800);
    private KeyboardSensor keyboard = gui.getKeyboardSensor();


    private static final int BALL_SPEED = 10;
    private static final int BALL_RADIUS = 5;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;

    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        Ball ball1 = new Ball(new Point(400, 700), BALL_RADIUS, Color.BLACK);
        Velocity v1 = Velocity.fromAngleAndSpeed(300, BALL_SPEED);
        ball1.setVelocity(v1);
        ball1.setTopLeftCorner(new Point(0, 0));
        ball1.setBottomRightCorner(new Point(800, 800));
        ball1.addToGame(this);

        Ball ball2 = new Ball(new Point(400, 700), BALL_RADIUS, Color.BLUE);
        Velocity v2 = Velocity.fromAngleAndSpeed(60, BALL_SPEED);
        ball2.setVelocity(v2);
        ball2.setTopLeftCorner(new Point(0, 0));
        ball2.setBottomRightCorner(new Point(800, 800));
        ball2.addToGame(this);

        //init walls
        Rectangle rectTop = new Rectangle(new Point(0, 0), 800, 25);
        Rectangle rectBottom = new Rectangle(new Point(0, 775), 800, 25);
        Rectangle rectLeft = new Rectangle(new Point(0, 0), 25, 800);
        Rectangle rectRight = new Rectangle(new Point(775, 0), 25, 800);
        Block blockTop = new Block(rectTop, Color.BLACK);
        Block blockBottom = new Block(rectBottom, Color.BLACK);
        Block blockLeft = new Block(rectLeft, Color.BLACK);
        Block blockRight = new Block(rectRight, Color.BLACK);
        blockTop.addToGame(this);
        blockBottom.addToGame(this);
        blockLeft.addToGame(this);
        blockRight.addToGame(this);

        //init blocks
        for (int i = 3; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 100), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.GRAY, 2);
            block.addToGame(this);
        }
        for (int i = 4; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 125), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.RED, 1);
            block.addToGame(this);
        }
        for (int i = 5; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 150), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.YELLOW, 1);
            block.addToGame(this);
        }
        for (int i = 6; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 175), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.BLUE, 1);
            block.addToGame(this);
        }
        for (int i = 7; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 200), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.PINK, 1);
            block.addToGame(this);
        }
        for (int i = 8; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 225), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.GREEN, 1);
            block.addToGame(this);
        }

        //init paddle
        Paddle paddle = new Paddle(new Rectangle(new Point(350, 750), 100, 25), Color.GRAY, keyboard);
        paddle.addToGame(this);

        Ball.setEnvironment(environment);
    }

    public void initializeWalls() {
        Ball ball1 = new Ball(new Point(400, 700), BALL_RADIUS, Color.BLACK);
        Velocity v1 = Velocity.fromAngleAndSpeed(0, BALL_SPEED);
        ball1.setVelocity(v1);
        ball1.setTopLeftCorner(new Point(0, 0));
        ball1.setBottomRightCorner(new Point(800, 800));
        ball1.addToGame(this);

        Ball ball2 = new Ball(new Point(400, 700), BALL_RADIUS, Color.BLUE);
        Velocity v2 = Velocity.fromAngleAndSpeed(70, BALL_SPEED);
        ball2.setVelocity(v2);
        ball2.setTopLeftCorner(new Point(0, 0));
        ball2.setBottomRightCorner(new Point(800, 800));
        ball2.addToGame(this);

        //init walls
        Rectangle rectTop = new Rectangle(new Point(0, 0), 800, 25);
        Rectangle rectBottom = new Rectangle(new Point(0, 775), 800, 25);
        Rectangle rectLeft = new Rectangle(new Point(0, 0), 25, 800);
        Rectangle rectRight = new Rectangle(new Point(775, 0), 25, 800);
        Block blockTop = new Block(rectTop, Color.BLACK);
        Block blockBottom = new Block(rectBottom, Color.BLACK);
        Block blockLeft = new Block(rectLeft, Color.BLACK);
        Block blockRight = new Block(rectRight, Color.BLACK);
        blockTop.addToGame(this);
        blockBottom.addToGame(this);
        blockLeft.addToGame(this);
        blockRight.addToGame(this);

        //init blocks
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * BLOCK_WIDTH, 600), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rect, Color.GRAY, 2);
            block.addToGame(this);
        }


        //init paddle
        Paddle paddle = new Paddle(new Rectangle(new Point(350, 750), 100, 25), Color.GRAY, keyboard);
        paddle.addToGame(this);

        Ball.setEnvironment(environment);
    }

    // Run the game -- start the animation loop.
    public void run() {
        Sleeper sleeper = new Sleeper();

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}