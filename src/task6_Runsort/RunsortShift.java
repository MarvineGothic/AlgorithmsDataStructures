package task6_Runsort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import static task6_Runsort.SortFunctions.*;


/**
 * @author Sergiy Isakov seis@itu.dk
 */
@SuppressWarnings("unchecked")
public class RunsortShift {
    private static Comparable[] aux;

    public static void main(String[] args) {
        Comparable[] a = StdIn.readAllStrings();
        sort(a);
        for (Comparable anA : a) StdOut.println(anA);
    }

    /**
     * <p>
     * Method to sort an array of comparable elements, using an array with indexes
     * of partitions(runs). Method 'merge()' from algs4 using those indexes as lo, mid and hi variables
     * to merge two sorted partition in one sorted.
     * There are two variables 'runsLength' and 'initialSize' to keep track of size of 'runs' array.
     * In while loop we create:
     * 'shiftIndex' starting from 0, to make shift of elements in 'runs'-array
     * and 'lo' variable to be used in 'merge()';
     * <p>
     * Within one while loop - after every merge:
     * "size" of 'runs'-array decrements by 1;
     * used mid index rewrites with end value with help of 'shiftIndex';
     * lo becomes end + 1;
     * step i increments by 2, because we used two adjacent elements from runs array;
     * and shiftIndex increments by 1.
     * Shifting algorithm:
     * After using elements for 'mid' value we don't need them in next loop, so array is shrinking by
     * shifting useful elements to the beginning. We keep track of size of array by 'initialSize' and in
     * the end of each while loop we pass its value to 'runsLength' which is used in for loop.
     *
     * @param a array of Comparable elements
     */
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        Integer[] runs = new Integer[a.length];
        int initialSize = createInitialRuns(a, runs, 5);
        int runsLength = initialSize;
        while (runsLength >= 1) {
            int shiftIndex = 0;
            int lo = 0;
            for (int i = 0; i < runsLength; ) {
                int mid = runs[i];
                int end = i == runsLength - 1 ? a.length - 1 : runs[i + 1];
                merge(a, aux, lo, mid, end);
                initialSize--;
                if (i != a.length) {
                    runs[shiftIndex] = end;
                    lo = end + 1;
                    i += 2;
                    shiftIndex++;
                }
            }
            runsLength = initialSize;
        }
    }

    /**
     * Creates an array of indexes of sorted partitions in array of Comparable elements.
     * It considers the size of sorted partition. If it less than given size, then it uses
     * insertion sort to sort up the partition to desirable size, and then if some of next elements
     * violates sorting order it saves index of current element in array 'runs'
     *
     * @param a
     * @param runs
     * @param runSize
     * @return
     */
    public static int createInitialRuns(Comparable[] a, Integer[] runs, int runSize) {
        int runsIndexCount = 0;
        int runLength = runSize;
        int offset = 0;
        for (int i = 0; i < a.length - 1; i++) {
            runLength--;
            if (runLength > 0)
                insertionSortShort(i, offset, a);
            else if (less(a[i + 1], a[i])) {
                runs[runsIndexCount++] = i;
                runLength = runSize;
                offset = i + 1;
            }
        }
        return runsIndexCount;
    }
}

