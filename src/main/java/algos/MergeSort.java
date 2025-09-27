package algos;

import java.util.Arrays;

public class MergeSort {
    private static final int cutOff = 10;

    public static void sort(int[] a, Metrics m) {
        m.reset();
        if (a == null || a.length <= 1) return;
        m.startTimer();
        int[] buf = new int[a.length];
        sort(a, buf, 0, a.length - 1, m);
        m.stopTimer();
    }

    private static void sort(int[] a, int[] buf, int left, int right, Metrics m) {
        m.enterRecursion();
        if (right - left + 1 <= cutOff) {
            insertionSort(a, left, right, m);
            m.exitRecursion();
            return;
        }

        int mid = left + (right - left) / 2;
        sort(a, buf, left, mid, m);
        sort(a, buf, mid + 1, right, m);

        m.incComparisons();
        if (a[mid] <= a[mid + 1]) {
            m.exitRecursion();
            return;
        }

        merge(a, buf, left, mid, right, m);
        m.exitRecursion();
    }

    private static void merge(int[] a, int[] buf, int left, int mid, int right, Metrics m) {
        int i = left, j = mid + 1, k = 0;
        int len = right - left + 1;

        while (i <= mid && j <= right) {
            m.incComparisons();
            if (a[i] <= a[j]) buf[k++] = a[i++];
            else buf[k++] = a[j++];
        }
        while (i <= mid) buf[k++] = a[i++];
        while (j <= right) buf[k++] = a[j++];

        System.arraycopy(buf, 0, a, left, len);
        m.incSwaps();
    }

    private static void insertionSort(int[] a, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int value = a[i];
            int j = i - 1;
            while (j >= left) {
                m.incComparisons();
                if (a[j] > value) {
                    a[j + 1] = a[j];
                    m.incSwaps();
                    j--;
                } else break;
            }
            a[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        System.out.println("before: [5, 2, 9, 1, 5, 6]");
        System.out.println("after:  " + Arrays.toString(arr));
        System.out.println("time = " + m.getExecutionTimeMillis() + " ms");
        System.out.println("comparisons = " + m.getComparisons());
        System.out.println("swaps = " + m.getSwaps());
        System.out.println("max depth = " + m.getMaxRecursionDepth());
    }
}



