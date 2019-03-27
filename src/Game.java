import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;

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
        Ball ball = new Ball(new Point(400, 750), 10, Color.BLACK);
        Velocity v = Velocity.fromAngleAndSpeed(0, 2);
        ball.setVelocity(v);
        ball.setTopLeftCorner(new Point(0, 0));
        ball.setBottomRightCorner(new Point(800, 800));
        ball.addToGame(this);

        //init walls
        Rectangle rectTop = new Rectangle(new Point(0, 0), 800, 25);
        Rectangle rectBottom = new Rectangle(new Point(0, 775), 800, 25);
        Rectangle rectLeft = new Rectangle(new Point(0, 0), 25, 800);
        Rectangle rectRight = new Rectangle(new Point(775, 0), 25, 800);
        Block blockTop = new Block(rectTop, Color.GRAY);
        Block blockBottom = new Block(rectBottom, Color.GRAY);
        Block blockLeft = new Block(rectLeft, Color.GRAY);
        Block blockRight = new Block(rectRight, Color.GRAY);
        blockTop.addToGame(this);
        blockBottom.addToGame(this);
        blockLeft.addToGame(this);
        blockRight.addToGame(this);

        /*for (int i = 0; i < 30; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * 25, 25), 25, 25);
            Block block = new Block(rect, Color.BLUE);
            block.addToGame(this);
        }*/

        ball.setEnvironment(environment);
    }

    // Run the game -- start the animation loop.
    public void run() {
        GUI gui = new GUI("Game Run", 800, 800);
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