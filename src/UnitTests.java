import java.util.List;

public class UnitTests {

    public static void main(String[] args) {
        checkRectangle();
    }

    private static void checkRectangle() {
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
}
