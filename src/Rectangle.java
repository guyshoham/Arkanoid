import java.util.ArrayList;
import java.util.List;

class Rectangle {

    private Point upperLeft;
    private double width, height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public List intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();

        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Line top = new Line(upperLeft, upperRight);
        Line bottom = new Line(lowerLeft, lowerRight);
        Line left = new Line(upperLeft, lowerLeft);
        Line right = new Line(upperRight, lowerRight);

        if (line.isIntersecting(top)) {
            points.add(line.intersectionWith(top));
        }
        if (line.isIntersecting(bottom)) {
            points.add(line.intersectionWith(bottom));
        }
        if (line.isIntersecting(left)) {
            points.add(line.intersectionWith(left));
        }
        if (line.isIntersecting(right)) {
            points.add(line.intersectionWith(right));
        }

        return points;
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}