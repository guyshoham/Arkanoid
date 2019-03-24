
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

/**
 * MultipleBouncingBallsAnimation class.
 *
 * @author Guy Shoham
 */
public class MultipleBouncingBallsAnimation {
    public static final int SIZE = 500;
    private static final Point FRAME_TOP_LEFT = new Point(0, 0); // top left corner of gray rectangle
    private static final Point FRAME_BOTTOM_RIGHT = new Point(500, 500); //  bottom right corner of gray rectangle

    /**
     * this main method responsible for generate balls, and move them in the frame.
     *
     * @param args the size of the balls.
     */
    public static void main(String[] args) {
        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            int input = Integer.parseInt(args[i]);
            Random rand = new Random(); // create a random-number generator
            int x = rand.nextInt(SIZE) + 1; // get integer in range 1-500
            int y = rand.nextInt(SIZE) + 1; // get integer in range 1-500
            int angle = rand.nextInt(360) + 1; // get integer in range 1-360
            Ball ball = new Ball(x, y, input, java.awt.Color.BLACK);
            balls[i] = ball;
            double speed = 15 - (input * 0.2);
            if (input >= 50) {
                speed = 3;
            }
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
            ball.setTopLeftCorner(FRAME_TOP_LEFT);
            ball.setBottomRightCorner(FRAME_BOTTOM_RIGHT);

        }
        GUI gui = new GUI("multiple bouncing balls", SIZE, SIZE);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
