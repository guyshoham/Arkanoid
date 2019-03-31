import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void isPointInLine() {
        Line line = new Line(new Point(0, 0), new Point(10, 10));
        Point middle = new Point(5, 5);
        boolean result = Line.isPointInLine(line, middle);
        assertTrue(result);
    }

    @Test
    public void start() {
        Point start = new Point(0, 12);
        Point end = new Point(5, 0);
        Line line = new Line(start, end);

        assertEquals(start, line.getStart());
    }

    @Test
    public void end() {
        Point start = new Point(0, 12);
        Point end = new Point(5, 0);
        Line line = new Line(start, end);

        assertEquals(end, line.getEnd());
    }

    @Test
    public void length() {
        Point start = new Point(0, 12);
        Point end = new Point(5, 0);
        Line line = new Line(start, end);

        assertEquals(13, (int) line.length());
    }

    @Test
    public void middle() {
        Point start = new Point(10, 50);
        Point end = new Point(30, 40);
        Point middle = new Point(20, 45);
        Line line = new Line(start, end);

        assertEquals((int) middle.getX(), (int) line.middle().getX());
        assertEquals((int) middle.getY(), (int) line.middle().getY());
    }

    @Test
    public void getSlope() {
        Point start = new Point(10, 20);
        Point end = new Point(5, 5);
        Line line = new Line(start, end);

        assertEquals(3, (int) line.getSlope());
    }

    @Test
    public void getIntercept() {
        Point start = new Point(10, 20);
        Point end = new Point(5, 5);
        Line line = new Line(start, end);

        assertEquals(-10, (int) line.getIntercept());
    }

    @Test
    public void isIntersecting() {
        Line line1 = new Line(new Point(0, 0), new Point(10, 10));
        Line line2 = new Line(new Point(10, 0), new Point(0, 10));

        assertTrue(line1.isIntersecting(line2));
    }

    @Test
    public void intersectionWith() {
        Line line1 = new Line(new Point(0, 0), new Point(10, 10));
        Line line2 = new Line(new Point(10, 0), new Point(0, 10));
        Point point = line1.intersectionWith(line2);

        assertEquals(5, (int) point.getX());
        assertEquals(5, (int) point.getY());

    }

    @Test
    public void equals() {
        Line line1 = new Line(new Point(0, 0), new Point(10, 10));
        Line line2 = new Line(new Point(10, 0), new Point(0, 10));
        assertFalse(line1.equals(line2));

        line1 = new Line(new Point(10, 0), new Point(10, 10));
        line2 = new Line(new Point(10, 0), new Point(10, 10));
        assertTrue(line1.equals(line2));
    }

    @Test
    public void getStart() {
    }

    @Test
    public void getEnd() {
    }

    @Test
    public void calcSlope() {
    }

    @Test
    public void calcIntercept() {
    }

    @Test
    public void calcDet() {
    }

    @Test
    public void closestIntersectionToStartOfLine() {
    }
}