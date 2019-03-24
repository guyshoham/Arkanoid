/**
 * @author Guy Shoham
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * MultipleFramesBouncingBallsAnimation class.
 *
 * @author Guy Shoham
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final int SIZE = 600; // size of window
    //points which represent corners of gray and yellow frames
    private static final Point GRAY_TOP_LEFT = new Point(50, 50);
    private static final Point GRAY_BOTTOM_RIGHT = new Point(500, 500);
    private static final Point YELLOW_TOP_LEFT = new Point(450, 450);
    private static final Point YELLOW_BOTTOM_RIGHT = new Point(600, 600);

    /**
     * this main method responsible for generate balls, and move them in 2 different frames.
     *
     * @param args the size of the balls.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("bouncing balls", SIZE, SIZE);
        Ball[] balls = new Ball[args.length];
        generateBalls(args, balls);
        runBalls(gui, balls);
    }

    /**
     * this method generate X number of balls (x = args.length).
     * the method sets random position, color. angle for each ball.
     * the size of the ball comes from args input.
     * the speed of the ball is based on the ball's size. bigger balls moves slower.
     * when the method is finished, all the balls are in 'balls' array.
     *
     * @param args  the size of the balls.
     * @param balls array of balls.
     */
    public static void generateBalls(String[] args, Ball[] balls) {
        for (int i = 0; i < args.length; i++) {
            Random rand = new Random(); // create a random-number generator
            int x, y, angle, red, green, blue, input = Integer.parseInt(args[i]);

            //generate random position for the ball (gray [50,500], yellow [450,600])
            if (i < args.length / 2) {
                x = (rand.nextInt((int) (GRAY_BOTTOM_RIGHT.getX() - GRAY_TOP_LEFT.getX() + 1))
                        + (int) GRAY_TOP_LEFT.getX());
                y = (rand.nextInt((int) (GRAY_BOTTOM_RIGHT.getY() - GRAY_TOP_LEFT.getY() + 1))
                        + (int) GRAY_TOP_LEFT.getY());
            } else {
                x = (rand.nextInt((int) (YELLOW_BOTTOM_RIGHT.getX() - YELLOW_TOP_LEFT.getX() + 1))
                        + (int) YELLOW_TOP_LEFT.getX());
                y = (rand.nextInt((int) (YELLOW_BOTTOM_RIGHT.getY() - YELLOW_TOP_LEFT.getY() + 1))
                        + (int) YELLOW_TOP_LEFT.getY());
            }

            //generate random angle and color
            angle = rand.nextInt(360) + 1; // generate angle [1,360]
            red = rand.nextInt(255) + 1; // generate RGB color [1,255]
            green = rand.nextInt(255) + 1; // generate RGB color[1,255]
            blue = rand.nextInt(255) + 1; // generate RGB color[1,255]

            //generate ball and put it in array
            Ball ball = new Ball(x, y, input, new Color(red, green, blue));
            balls[i] = ball;

            //set speed of ball
            double speed = 15 - (input * 0.2);
            if (input >= 50) {
                speed = 3;
            }

            //set angle of ball
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);

            //set frameTopLeft and frameBottomRight
            if (i < args.length / 2) {
                ball.setTopLeftCorner(GRAY_TOP_LEFT);
                ball.setBottomRightCorner(GRAY_BOTTOM_RIGHT);
            } else {
                ball.setTopLeftCorner(YELLOW_TOP_LEFT);
                ball.setBottomRightCorner(YELLOW_BOTTOM_RIGHT);
            }
        }
    }

    /**
     * this main method responsible for generate balls, and move them in 2 different frames.
     *
     * @param gui   gui object.
     * @param balls array of balls.
     */
    private static void runBalls(GUI gui, Ball[] balls) {
        Sleeper sleeper = new Sleeper();
        while (true) {
            //create window and rectangles
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle((int) GRAY_TOP_LEFT.getX(), (int) GRAY_TOP_LEFT.getY(),
                    (int) (GRAY_BOTTOM_RIGHT.getX() - GRAY_TOP_LEFT.getX()),
                    (int) (GRAY_BOTTOM_RIGHT.getY() - GRAY_TOP_LEFT.getY()));
            d.setColor(Color.YELLOW);
            d.fillRectangle((int) YELLOW_TOP_LEFT.getX(), (int) YELLOW_TOP_LEFT.getY(),
                    (int) (YELLOW_BOTTOM_RIGHT.getX() - YELLOW_TOP_LEFT.getX()),
                    (int) (YELLOW_BOTTOM_RIGHT.getY() - YELLOW_TOP_LEFT.getY()));
            //move balls
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);  // wait for 'DELAY' milliseconds.
        }
    }
}

