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
}