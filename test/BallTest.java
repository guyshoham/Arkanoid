import gameObjects.Ball;
import geometry.Point;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BallTest {

    Ball ball = new Ball(new Point(5, 5), 5, Color.BLACK);

    @Test
    public void getX() {
        assertEquals(5, ball.getX());
    }

    @Test
    public void getY() {
        assertEquals(5, ball.getY());
    }

    @Test
    public void getSize() {
        assertEquals(5, ball.getSize());
    }

    @Test
    public void getColor() {
        assertEquals(Color.BLACK, ball.getColor());
    }
}