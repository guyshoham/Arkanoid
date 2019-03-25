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
        Line topEdge = new Line(upperLeft, upperRight);
        Line bottomEdge = new Line(lowerLeft, lowerRight);
        Line leftEdge = new Line(upperLeft, lowerLeft);
        Line rightEdge = new Line(upperRight, lowerRight);

        if (line.isIntersecting(topEdge)) {
            points.add(line.intersectionWith(topEdge));
        }
        if (line.isIntersecting(bottomEdge)) {
            points.add(line.intersectionWith(bottomEdge));
        }
        if (line.isIntersecting(leftEdge)) {
            points.add(line.intersectionWith(leftEdge));
        }
        if (line.isIntersecting(rightEdge)) {
            points.add(line.intersectionWith(rightEdge));
        }

        return points;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}