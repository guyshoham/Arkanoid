import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void distance() {
        Point p1 = new Point(0, 12);
        Point p2 = new Point(5, 0);
        int result = (int) p1.distance(p2);
        assertEquals(13, result);
    }

    @Test
    public void equals() {
        Point p1, p2;

        p1 = new Point(2, 2);
        p2 = new Point(2, -2);
        assertFalse(p1.equals(p2));

        p1 = new Point(2, 2);
        p2 = new Point(-2, 2);
        assertFalse(p1.equals(p2));

        p1 = new Point(2, 2);
        p2 = new Point(2, 2);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void getX() {
        Point p = new Point(2, 3);
        assertEquals(2, (int) p.getX());
    }

    @Test
    public void getY() {
        Point p = new Point(2, 3);
        assertEquals(3, (int) p.getY());
    }

    @Test
    public void isInRect() {
        Rectangle rect = new Rectangle(new Point(0, 0), 10, 10);
        Point point;

        point = new Point(0, 0);
        assertFalse(point.isInRect(rect));
        assertFalse(point.isInRect(rect.getUpperLeft(), rect.getLowerRight()));

        point = new Point(5, 5);
        assertTrue(point.isInRect(rect));
        assertTrue(point.isInRect(rect.getUpperLeft(), rect.getLowerRight()));
    }
}