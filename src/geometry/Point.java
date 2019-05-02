package geometry;

/**
 * Point Model Object.
 *
 * @author Guy Shoham
 */
public class Point {
    private double x, y; //the x and y position of the points.

    /**
     * Class constructor.
     *
     * @param x the x position of the points.
     * @param y the y position of the points.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other the other point.
     * @return the distance between the two points.
     */
    public double distance(Point other) {
        // a formula to calculate the distance between 2 points.
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * @param other the other point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * @return the X position of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the Y position of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * checks if this point is in the rectangle.
     *
     * @param topLeftCorner     top left corner point of the rectangle.
     * @param bottomRightCorner bottom right corner point of the rectangle.
     * @return true if this point is in the rectangle, else otherwise.
     * if the ball is on the edges of the rectangle, the method still returns false.
     */
    public boolean isInRect(Point topLeftCorner, Point bottomRightCorner) {
        return x > topLeftCorner.getX() && x < bottomRightCorner.getX()
                && y > topLeftCorner.getY() && y < bottomRightCorner.getY();
    }

    /**
     * checks if this point is in the rectangle.
     *
     * @param rect the rectangle.
     * @return true if this point is in the rectangle, else otherwise.
     * if the ball is on the edges of the rectangle, the method still returns false.
     */
    public boolean isInRect(Rectangle rect) {
        return isInRect(rect.getUpperLeft(), rect.getLowerRight());
    }

    @Override
    public String toString() {
        return "Point[" + x + "," + y + ']';
    }
}