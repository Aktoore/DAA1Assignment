# DAA1Assignment
# Algorithms Assignment 1

## Architecture
I made all algorithms use one helper class called `Metrics`.  
It measures:
- how many comparisons happen,
- how many swaps,
- recursion depth,
- and execution time.  

MergeSort uses one buffer array so it doesn’t create too many new arrays.  
QuickSort always goes into the smaller half first, so recursion doesn’t go too deep.  
Deterministic Select uses “median of medians” to find the k-th element.  
Closest Pair is solved with divide and conquer (split points in half, then check middle strip).  

## Theory

- **MergeSort** → splits array in half, then merges. Time: `O(n log n)`.  
- **QuickSort** → splits around pivot, average `O(n log n)`, worst `O(n²)`.  
- **Deterministic Select** → median of medians, only one side continues. Time: `O(n)`.  
- **Closest Pair** → divide array, solve two halves, check strip. Time: `O(n log n)`.  
 

## Notes
- MergeSort is stable and good with cache.  
- QuickSort is usually faster but not always (depends on pivot).  
- Deterministic Select is linear but slow for small inputs.  
- Closest Pair also matches `O(n log n)` but has extra work with geometry.  

## Conclusion
Theory mostly matches the measurements.  
Small differences come from cache, random pivot choice, and Java’s memory (GC).  

