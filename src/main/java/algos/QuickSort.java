package algos;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] a, Metrics m) {
        m.reset();
        if (a == null || a.length <= 1) return;
        m.startTimer();
        quicksort(a, 0, a.length - 1, m);
        m.stopTimer();
    }

    private static void quicksort(int[] a, int left, int right, Metrics m) {
        m.enterRecursion();
        while (left < right) {
            int pivot = partition(a, left, right, m);
            if (pivot - left < right - pivot) {
                quicksort(a, left, pivot - 1, m);
                left = pivot + 1;
            } else {
                quicksort(a, pivot + 1, right, m);
                right = pivot - 1;
            }
        }
        m.exitRecursion();
    }

    private static int partition(int[] a, int left, int right, Metrics m) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, right, m);
        int store = left;
        for (int i = left; i < right; i++) {
            m.incComparisons();
            if (a[i] < pivotValue) {
                swap(a, i, store, m);
                store++;
            }
        }
        swap(a, store, right, m);
        return store;
    }

    private static void swap(int[] a, int i, int j, Metrics m) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        m.incSwaps();
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 1, 8, 4, 5};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        System.out.println("before: [3, 6, 1, 8, 4, 5]");
        System.out.println("after:  " + Arrays.toString(arr));
        System.out.println("time = " + m.getExecutionTimeMillis() + " ms");
        System.out.println("comparisons = " + m.getComparisons());
        System.out.println("swaps = " + m.getSwaps());
        System.out.println("max depth = " + m.getMaxRecursionDepth());
    }
}

