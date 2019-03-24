import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * BouncingBallAnimation class.
 *
 * @author Guy Shoham
 */
public class BouncingBallAnimation {
    public static final int SIZE = 200;
    private static final Point FRAME_TOP_LEFT = new Point(0, 0); // top left corner of gray rectangle
    private static final Point FRAME_BOTTOM_RIGHT = new Point(200, 200); //  bottom right corner of gray rectangle

    /**
     * this main method responsible for generate one ball, and move it in the frame.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("bouncing ball", SIZE, SIZE);
        Sleeper sleeper = new Sleeper();

        Ball ball = new Ball(100, 100, 30, Color.BLACK);
        Velocity v = Velocity.fromAngleAndSpeed(90, 2);
        ball.setVelocity(v);
        ball.setTopLeftCorner(FRAME_TOP_LEFT);
        ball.setBottomRightCorner(FRAME_BOTTOM_RIGHT);

        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
