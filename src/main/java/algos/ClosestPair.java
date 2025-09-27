package algos;

import java.util.*;

public class ClosestPair {

    public static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
        public String toString() { return "(" + x + "," + y + ")"; }
    }

    private static double dist(Point a, Point b, Metrics m) {
        m.incComparisons();
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double closestPair(Point[] points, Metrics m) {
        m.reset();
        m.startTimer();

        Point[] ptsByX = points.clone();
        Arrays.sort(ptsByX, Comparator.comparingDouble(p -> p.x));
        Point[] ptsByY = points.clone();
        Arrays.sort(ptsByY, Comparator.comparingDouble(p -> p.y));

        double result = closest(ptsByX, ptsByY, 0, points.length - 1, m);

        m.stopTimer();
        return result;
    }

    private static double closest(Point[] ptsByX, Point[] ptsByY, int left, int right, Metrics m) {
        m.enterRecursion();

        if (right - left <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    min = Math.min(min, dist(ptsByX[i], ptsByX[j], m));
                }
            }
            Arrays.sort(ptsByX, left, right + 1, Comparator.comparingDouble(p -> p.y));
            m.exitRecursion();
            return min;
        }

        int mid = (left + right) / 2;
        double midX = ptsByX[mid].x;

        Point[] leftY = Arrays.stream(ptsByY).filter(p -> p.x <= midX).toArray(Point[]::new);
        Point[] rightY = Arrays.stream(ptsByY).filter(p -> p.x > midX).toArray(Point[]::new);

        double d1 = closest(ptsByX, leftY, left, mid, m);
        double d2 = closest(ptsByX, rightY, mid + 1, right, m);
        double d = Math.min(d1, d2);

        List<Point> strip = new ArrayList<>();
        for (Point p : ptsByY) {
            if (Math.abs(p.x - midX) < d) strip.add(p);
        }

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, dist(strip.get(i), strip.get(j), m));
            }
        }

        m.exitRecursion();
        return d;
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };

        Metrics m = new Metrics();
        double ans = ClosestPair.closestPair(points, m);

        System.out.println("Closest distance = " + ans);
        System.out.println("time = " + m.getExecutionTimeMillis() + " ms");
        System.out.println("comparisons = " + m.getComparisons());
        System.out.println("max depth = " + m.getMaxRecursionDepth());
    }
}
