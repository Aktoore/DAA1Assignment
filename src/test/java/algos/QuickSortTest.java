package algos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {

    @Test
    public void testSimpleSort() {
        int[] arr = {3, 6, 1, 8, 4, 5};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 3, 4, 5, 6, 8}, arr);
    }

    @Test
    public void testRandomArrays() {
        Random rand = new Random(456);
        for (int t = 0; t < 50; t++) {
            int n = rand.nextInt(200) + 1;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000) - 500;

            int[] copy = Arrays.copyOf(arr, arr.length);
            Metrics m = new Metrics();
            QuickSort.sort(arr, m);
            Arrays.sort(copy);

            assertArrayEquals(copy, arr);
        }
    }

    @Test
    public void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, arr);
    }

    @Test
    public void testReverseSorted() {
        int[] arr = {9, 7, 5, 3, 1};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 3, 5, 7, 9}, arr);
    }
}
