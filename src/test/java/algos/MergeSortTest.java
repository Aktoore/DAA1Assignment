package algos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {

    @Test
    public void testSimpleSort() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertArrayEquals(new int[]{1, 2, 5, 5, 6, 9}, arr);
    }

    @Test
    public void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 100; t++) {
            int n = rand.nextInt(100);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000) - 500;

            int[] copy = Arrays.copyOf(arr, arr.length);
            Metrics m = new Metrics();
            MergeSort.sort(arr, m);
            Arrays.sort(copy);

            assertArrayEquals(copy, arr);
        }
    }
}
