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
        Ball ball1 = new Ball(new Point(400, 700), 10, Color.BLACK);
        Velocity v1 = Velocity.fromAngleAndSpeed(30, 15);
        ball1.setVelocity(v1);
        ball1.setTopLeftCorner(new Point(0, 0));
        ball1.setBottomRightCorner(new Point(800, 800));
        ball1.addToGame(this);

        Ball ball2 = new Ball(new Point(400, 700), 10, Color.BLUE);
        Velocity v2 = Velocity.fromAngleAndSpeed(70, 15);
        ball2.setVelocity(v2);
        ball2.setTopLeftCorner(new Point(0, 0));
        ball2.setBottomRightCorner(new Point(800, 800));
        ball2.addToGame(this);

       /* Rectangle background = new Rectangle(new Point(0, 0), 800, 800);
        background.setColor(Color.cyan);*/

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

        for (int i = 3; i < 30; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * 25, 25), 25, 25);
            Block block = new Block(rect, Color.GRAY);
            block.addToGame(this);
        }
        for (int i = 4; i < 30; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * 25, 50), 25, 25);
            Block block = new Block(rect, Color.RED);
            block.addToGame(this);
        }
        for (int i = 5; i < 30; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * 25, 75), 25, 25);
            Block block = new Block(rect, Color.YELLOW);
            block.addToGame(this);
        }
        for (int i = 6; i < 30; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * 25, 100), 25, 25);
            Block block = new Block(rect, Color.BLUE);
            block.addToGame(this);
        }
        for (int i = 7; i < 30; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * 25, 125), 25, 25);
            Block block = new Block(rect, Color.PINK);
            block.addToGame(this);
        }
        for (int i = 8; i < 30; i++) {
            Rectangle rect = new Rectangle(new Point(25 + i * 25, 150), 25, 25);
            Block block = new Block(rect, Color.GREEN);
            block.addToGame(this);
        }

        ball1.setEnvironment(environment);
        ball2.setEnvironment(environment);
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