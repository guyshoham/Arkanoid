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
}