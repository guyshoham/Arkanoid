import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void intersectionPoints() {
        Line rightEdge = new Line(new Point(25, 0), new Point(25, 800));
        Line trajectory = new Line(new Point(320, 591.4359353944892), new Point(4.999999999999935, 45.83993101029296));

        boolean result = trajectory.isIntersecting(rightEdge);
        assertTrue(result);
    }

    @Test
    public void getUpperLeft() {
    }

    @Test
    public void getUpperRight() {
    }

    @Test
    public void getLowerLeft() {
    }

    @Test
    public void getLowerRight() {
    }

    @Test
    public void getTopEdge() {
    }

    @Test
    public void getBottomEdge() {
    }

    @Test
    public void getLeftEdge() {
    }

    @Test
    public void getRightEdge() {
    }

    @Test
    public void intersectionPoints1() {
    }

    @Test
    public void getWidth() {
        Rectangle rect = new Rectangle(new Point(0, 0), 10, 20);
        assertEquals(10, (int) rect.getWidth());
    }

    @Test
    public void getHeight() {
        Rectangle rect = new Rectangle(new Point(0, 0), 20, 10);
        assertEquals(10, (int) rect.getHeight());
    }
}