import java.util.ArrayList;
import java.util.List;

/**
 * Line Model Object.
 *
 * @author Guy Shoham
 */
public class Line {
    // constructors
    private Point start, end; //the start and end points of the line
    private double slope; //the slope of the line equation (m)
    private double intercept; //the intercept of the line equation (b)

    /**
     * Class constructor.
     *
     * @param start the point where the line starts.
     * @param end   the point where the line ends.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = calcSlope(this);
        this.intercept = calcIntercept(start, slope);
    }

    /**
     * Class constructor.
     *
     * @param x1 the x position of the point where the line starts.
     * @param y1 the y position of the point where the line starts.
     * @param x2 the x position of the point where the line ends.
     * @param y2 the y position of the point where the line ends.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double midX = (end.getX() + start.getX()) / 2;
        double midY = (end.getY() + start.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * @return the slope of the line equation.
     */
    public double getSlope() {
        return slope;
    }

    /**
     * @return the intercept of the line equation.
     */
    public double getIntercept() {
        return intercept;
    }

    /**
     * this methods checks if the lines intersect.
     * the method checks that the lines are not parallel, and than get the intersection point of the lines.
     * if the intersection point is in both lines, so the lines intersect.
     *
     * @param other the other line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //checks if the lines are parallel. if so return false.
        if (this.getSlope() == other.getSlope()) {
            return false;
        }

        //get the intersection point of the lines. if there isn't a point return null.
        Point intersectionPoint = intersectionWith(other);

        if (intersectionPoint != null) {
            //return true if the intersectionPoint is on both lines, false otherwise.
            return isPointInLine(this, intersectionPoint) && isPointInLine(other, intersectionPoint);
        } else {
            return false;
        }

    }

    /**
     * this methods gives the intersection point of two lines.
     * calculates equations for both lines and find, calculates the x,y position of the intersection point,
     * and checks if the point is in both lines.
     * if the intersection point is in both lines, so the lines intersect.
     *
     * @param other the other line.
     * @return the intersection point.
     */
    public Point intersectionWith(Line other) {
        // checks if lines are parallel
        if (this.getSlope() == other.getSlope()) {
            return null;
        }

        double x, y;
        double x1, y1, z1, x2, y2, z2; //variables for the two equations (y = mx + b)

        //(-mx + y = b)
        x1 = this.getSlope() * -1;
        y1 = 1;
        z1 = this.getIntercept();
        x2 = other.getSlope() * -1;
        y2 = 1;
        z2 = other.getIntercept();

        x = calcDet(z1, y1, z2, y2) / calcDet(x1, y1, x2, y2);
        y = slope * x + intercept;

        //create the intersection point of the lines.
        Point p = new Point(x, y);
        //checks if the point are in both lines. if false return null.
        if (isPointInLine(this, p) && isPointInLine(other, p)) {
            return p;
        } else {
            return null;
        }
    }

    /**
     * the method checks if the x and y position of both lines are the same, or opposite.
     *
     * @param other the other line.
     * @return true if the lines equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (start.equals(other.getStart()) && end.equals(other.getEnd())) {
            return true;
        } else {
            return start.equals(other.getEnd()) && end.equals(other.getStart());
        }
    }

    /**
     * @return the start point of the line
     */
    public Point getStart() {
        return start;
    }

    /**
     * @return the end point of the line
     */
    public Point getEnd() {
        return end;
    }

    /**
     * this method calculates the slope (m) of the line equation.
     *
     * @param line the line we want to calculate the slope for.
     * @return the slope of the line equation.
     */
    public double calcSlope(Line line) {
        return ((line.getEnd().getY() - line.getStart().getY()) / (line.getEnd().getX() - line.getStart().getX()));
    }

    /**
     * this method calculates the intercept (b) of the line equation.
     *
     * @param p1 point of the line (no matter which).
     * @param m  the slope of the line equation.
     * @return the intercept of the line equation.
     */
    public double calcIntercept(Point p1, double m) {
        return p1.getY() - m * p1.getX();
    }

    /**
     * this method calculates the determinant of 2x2 matrix.
     *
     * @param a first parameter.
     * @param b second parameter.
     * @param c third parameter.
     * @param d forth parameter.
     * @return the determinant of the 2x2 matrix.
     */
    public double calcDet(double a, double b, double c, double d) {
        return a * d - b * c;
    }

    /**
     * @param line  the line.
     * @param point the point.
     * @return true if the points is in line, false otherwise.
     */
    public boolean isPointInLine(Line line, Point point) {
        Line a = new Line(line.getStart(), point);
        Line b = new Line(point, line.getEnd());

        return Math.floor(line.length()) == Math.floor(a.length() + b.length());
    }


    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List points = rect.intersectionPoints(this);

        if (points.isEmpty()) {
            return null;
        } else {
            Point closestPoint = (Point) points.get(0);
            double closestDistance = this.start.distance(closestPoint);

            for (Object p : points) {
                double distance = this.start.distance((Point) p);
                if (distance < closestDistance) {
                    closestPoint = (Point) p;
                }
            }
            return closestPoint;
        }
    }
}