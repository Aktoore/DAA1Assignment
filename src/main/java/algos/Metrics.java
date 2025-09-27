package algos;

public class Metrics {
    private long comparisons;
    private long swaps;
    private long recursionDepth;
    private long maxRecursionDepth;
    private long startTime;
    private long endTime;

    public void reset() {
        comparisons = 0;
        swaps = 0;
        recursionDepth = 0;
        maxRecursionDepth = 0;
        startTime = 0;
        endTime = 0;
    }

    public void incComparisons() {
        comparisons++;
    }

    public void incSwaps() {
        swaps++;
    }

    public void enterRecursion() {
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
        }
    }

    public void exitRecursion() {
        recursionDepth--;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public long getExecutionTimeNano() {
        return endTime - startTime;
    }

    public double getExecutionTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }
}
