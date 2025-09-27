package algos;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000};

        for (int n : sizes) {
            int[] arr = randomArray(n);
            System.out.println("\n=== n = " + n + " ===");

            Metrics m1 = new Metrics();
            int[] copy1 = arr.clone();
            MergeSort.sort(copy1, m1);
            System.out.println("MergeSort: time=" + m1.getExecutionTimeMillis() +
                    "ms, comps=" + m1.getComparisons() +
                    ", swaps=" + m1.getSwaps() +
                    ", depth=" + m1.getMaxRecursionDepth());

            Metrics m2 = new Metrics();
            int[] copy2 = arr.clone();
            QuickSort.sort(copy2, m2);
            System.out.println("QuickSort: time=" + m2.getExecutionTimeMillis() +
                    "ms, comps=" + m2.getComparisons() +
                    ", swaps=" + m2.getSwaps() +
                    ", depth=" + m2.getMaxRecursionDepth());

            Metrics m3 = new Metrics();
            int k = n / 2;
            DeterministicSelect.select(arr.clone(), k, m3);
            System.out.println("DeterministicSelect: time=" + m3.getExecutionTimeMillis() +
                    "ms, comps=" + m3.getComparisons() +
                    ", swaps=" + m3.getSwaps() +
                    ", depth=" + m3.getMaxRecursionDepth());

            Metrics m4 = new Metrics();
            ClosestPair.Point[] pts = randomPoints(n);
            ClosestPair.closestPair(pts, m4);
            System.out.println("ClosestPair: time=" + m4.getExecutionTimeMillis() +
                    "ms, comps=" + m4.getComparisons() +
                    ", depth=" + m4.getMaxRecursionDepth());
        }
    }

    private static int[] randomArray(int n) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = r.nextInt(10000);
        return arr;
    }

    private static ClosestPair.Point[] randomPoints(int n) {
        Random r = new Random();
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) pts[i] = new ClosestPair.Point(r.nextInt(10000), r.nextInt(10000));
        return pts;
    }
}
