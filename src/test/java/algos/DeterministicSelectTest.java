package algos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class DeterministicSelectTest {

    @Test
    public void testSimpleArray() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        Metrics m = new Metrics();
        assertEquals(7, DeterministicSelect.select(arr.clone(), 2, m));
        assertEquals(3, DeterministicSelect.select(arr.clone(), 0, m));
        assertEquals(20, DeterministicSelect.select(arr.clone(), 5, m));
    }

    @Test
    public void testRandomArray() {
        Random rand = new Random(123);
        for (int t = 0; t < 50; t++) {
            int n = rand.nextInt(200) + 1;
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = rand.nextInt(1000) - 500;

            int[] copy = arr.clone();
            Arrays.sort(copy);

            Metrics m = new Metrics();
            for (int k = 0; k < Math.min(10, n); k++) {
                assertEquals(copy[k], DeterministicSelect.select(arr.clone(), k, m));
            }
        }
    }

    @Test
    public void testSingleElement() {
        int[] arr = {42};
        Metrics m = new Metrics();
        assertEquals(42, DeterministicSelect.select(arr, 0, m));
    }

    @Test
    public void testTwoElement() {
        int[] arr = {99, 1};
        Metrics m = new Metrics();
        assertEquals(1, DeterministicSelect.select(arr.clone(), 0, m));
        assertEquals(99, DeterministicSelect.select(arr.clone(), 1, m));
    }
}

