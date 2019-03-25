/**
 * Point Model Object.
 *
 * @author Guy Shoham
 */
public class Point {
    // constructor
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

    @Override
    public String toString() {
        return "Point[" + x + "," + y + ']';
    }
}