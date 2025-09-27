package algos;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics m) {
        m.reset();
        m.startTimer();
        int result = selectRec(arr, 0, arr.length - 1, k, m);
        m.stopTimer();
        return result;
    }

    private static int selectRec(int[] arr, int left, int right, int k, Metrics m) {
        m.enterRecursion();
        if (left == right) {
            m.exitRecursion();
            return arr[left];
        }
        int pivotIndex = pivot(arr, left, right, m);
        pivotIndex = partition(arr, left, right, pivotIndex, m);
        m.incComparisons();
        if (k == pivotIndex) {
            m.exitRecursion();
            return arr[k];
        } else if (k < pivotIndex) {
            int res = selectRec(arr, left, pivotIndex - 1, k, m);
            m.exitRecursion();
            return res;
        } else {
            int res = selectRec(arr, pivotIndex + 1, right, k, m);
            m.exitRecursion();
            return res;
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics m) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right, m);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            m.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i, m);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex, m);
        return storeIndex;
    }

    private static int pivot(int[] arr, int left, int right, Metrics m) {
        if (right - left < 5) {
            Arrays.sort(arr, left, right + 1);
            return (left + right) / 2;
        }
        int subRight = left;
        for (int i = left; i <= right; i += 5) {
            int subEnd = Math.min(i + 4, right);
            Arrays.sort(arr, i, subEnd + 1);
            int median = (i + subEnd) / 2;
            swap(arr, median, subRight, m);
            subRight++;
        }
        int mid = (subRight - left) / 2;
        int medianValue = selectRec(arr, left, subRight - 1, left + mid, m);
        for (int i = left; i <= right; i++) {
            if (arr[i] == medianValue) return i;
        }
        return left;
    }

    private static void swap(int[] arr, int i, int j, Metrics m) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        m.incSwaps();
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        Metrics m = new Metrics();
        int k = 2;
        int result = DeterministicSelect.select(arr, k, m);

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("k = " + k + " -> " + result);
        System.out.println("time = " + m.getExecutionTimeMillis() + " ms");
        System.out.println("comparisons = " + m.getComparisons());
        System.out.println("swaps = " + m.getSwaps());
        System.out.println("max depth = " + m.getMaxRecursionDepth());
    }
}
