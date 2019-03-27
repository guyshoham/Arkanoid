import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.List;

public class UnitTests {

    public static void main(String[] args) {
        checkPart1();
    }

    public static void checkRectangle() {
        Point upperLeft = new Point(0, 0);
        Rectangle rect = new Rectangle(upperLeft, 10, 10);

        if (rect.getUpperLeft().getX() != 0 || rect.getUpperLeft().getY() != 0) {
            System.out.println("Error: Rectangle.getUpperLeft");
        }
        if (rect.getWidth() != 10) {
            System.out.println("Error: Rectangle.getWidth");
        }
        if (rect.getHeight() != 10) {
            System.out.println("Error: Rectangle.getHeight");
        }

        Point lineStart = new Point(5, -5);
        Point lineEnd = new Point(15, 5);
        Line line = new Line(lineStart, lineEnd);
        List list = rect.intersectionPoints(line);

        for (Object o : list) {
            System.out.println(o.toString());
        }

        Point insideRect = new Point(10, 5);
        Point outsideRect = new Point(12, 5);

        if (!insideRect.isInRect(upperLeft, rect.getLowerRight())) {
            System.out.println("Error: Point.isInRect when point inside");
        }
        if (outsideRect.isInRect(upperLeft, rect.getLowerRight())) {
            System.out.println("Error: Point.isInRect when point outside");
        }

    }

    public static void checkPart1() {
        GUI gui = new GUI("GUI Test Part 1", 200, 200);
        Sleeper sleeper = new Sleeper();

        Rectangle rectTop = new Rectangle(new Point(0, 0), 200, 50);
        Rectangle rectBottom = new Rectangle(new Point(0, 150), 200, 50);
        Rectangle rectLeft = new Rectangle(new Point(0, 0), 50, 200);
        Rectangle rectRight = new Rectangle(new Point(150, 0), 50, 200);

        Block blockTop = new Block(rectTop, Color.BLUE);
        Block blockBottom = new Block(rectBottom, Color.RED);
        Block blockLeft = new Block(rectLeft, Color.GREEN);
        Block blockRight = new Block(rectRight, Color.YELLOW);

        Point ballCenter = new Point(100, 100);
        Ball ball = new Ball(ballCenter, 5, Color.BLACK);

        ball.addToGameEnv(blockTop);
        ball.addToGameEnv(blockBottom);
        ball.addToGameEnv(blockLeft);
        ball.addToGameEnv(blockRight);

        Velocity v = Velocity.fromAngleAndSpeed(120, 5);
        ball.setVelocity(v);
        ball.setTopLeftCorner(new Point(0, 0));
        ball.setBottomRightCorner(new Point(200, 200));
        int count = 0;
        while (true) {
            count++;
            System.out.println(count);
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            blockTop.drawOn(d);
            blockBottom.drawOn(d);
            blockLeft.drawOn(d);
            blockRight.drawOn(d);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(5);
        }
        //todo: fix bug (angle 120, speed 1, sleep 5, after ~700 steps)
    }
}
