package algos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    public void testSimpleCase() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(12, 30),
                new ClosestPair.Point(40, 50),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(12, 10),
                new ClosestPair.Point(3, 4)
        };
        Metrics m = new Metrics();
        double result = ClosestPair.closestPair(points, m);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    public void testTwoPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4)
        };
        Metrics m = new Metrics();
        double result = ClosestPair.closestPair(points, m);
        assertEquals(5.0, result, 1e-9);
    }

    @Test
    public void testCollinearPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(1, 0),
                new ClosestPair.Point(2, 0),
                new ClosestPair.Point(3, 0)
        };
        Metrics m = new Metrics();
        double result = ClosestPair.closestPair(points, m);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    public void testDuplicatePoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(5, 5),
                new ClosestPair.Point(5, 5),
                new ClosestPair.Point(10, 10)
        };
        Metrics m = new Metrics();
        double result = ClosestPair.closestPair(points, m);
        assertEquals(0.0, result, 1e-9);
    }
}
