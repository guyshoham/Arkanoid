import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * AbstractArtDrawing class.
 *
 * @author Guy Shoham
 */
public class AbstractArtDrawing {
    private static final int MAX_LINES = 10;
    private static final int SIZE = 500;
    private static final int DOT_RADIUS = 3;

    /**
     * draw random lines on surface.
     *
     * @param lines   array of lines.
     * @param surface the surface.
     */
    public void drawRandomLines(Line[] lines, DrawSurface surface) {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        for (int i = 0; i < MAX_LINES; i++) {
            int x1 = rand.nextInt(SIZE) + 1; // get integer in range 1-500
            int x2 = rand.nextInt(SIZE) + 1; // get integer in range 1-500
            int y1 = rand.nextInt(SIZE) + 1; // get integer in range 1-500
            int y2 = rand.nextInt(SIZE) + 1; // get integer in range 1-500
            lines[i] = new Line(x1, y1, x2, y2);
            surface.setColor(Color.BLACK);
            surface.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * draw blue dots in the middle of the lines.
     *
     * @param lines   array of lines.
     * @param surface the surface.
     */
    public void drawMiddlePoints(Line[] lines, DrawSurface surface) {
        for (int i = 0; i < MAX_LINES; i++) {
            surface.setColor(Color.BLUE);
            Point middle = lines[i].middle(); //create a point in the middle of the line
            surface.fillCircle((int) middle.getX(), (int) middle.getY(), DOT_RADIUS);
        }
    }

    /**
     * draw red dots in the intersection points of the lines.
     *
     * @param lines   array of lines.
     * @param surface the surface.
     */
    public void drawIntersectionPoints(Line[] lines, DrawSurface surface) {
        surface.setColor(Color.RED);
        for (int i = 0; i < MAX_LINES; i++) {
            for (int j = i + 1; j < MAX_LINES; j++) {
                if (j != i) {
                    if (lines[i].isIntersecting(lines[j])) {
                        Point p = lines[i].intersectionWith(lines[j]); //create a point in the intersection of the line
                        surface.fillCircle((int) p.getX(), (int) p.getY(), DOT_RADIUS);
                    }
                }
            }
        }
    }

    /**
     * generate random lines.
     * draw blue points in the middle of the lines and red dots in the intersection points of the lines.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Line[] lines = new Line[MAX_LINES];
        GUI gui = new GUI("Random Lines", SIZE, SIZE);
        DrawSurface d = gui.getDrawSurface();

        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines(lines, d);
        example.drawMiddlePoints(lines, d);
        example.drawIntersectionPoints(lines, d);
        gui.show(d);
    }
}