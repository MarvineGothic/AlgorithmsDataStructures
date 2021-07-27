package task6_Runsort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import static task6_Runsort.SortFunctions.*;

/**
 * @author Sergiy Isakov seis@itu.dk
 */
@SuppressWarnings("unchecked")
public class RunsortNextLess {
    private static Comparable[] aux;

    public static void main(String[] args) {
        Comparable[] a = StdIn.readAllLines();
        sort(a);
        for (Comparable anA : a) StdOut.println(anA);
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        int runLength = 32;
        while (true) {
            int count = 0;
            int mid;
            int hi;
            for (int lo = 0; lo <= a.length - 1; ) {
                mid = findNextLess(a, lo, runLength);
                hi = findNextLess(a, mid + 1, runLength);
                merge(a,aux, lo, mid, hi);
                lo = hi + 1;
                count++;
            }
            if (count <= 1) break;
        }
    }

    public static int findNextLess(Comparable[] a, int start, int runLength) {
        int i;
        if (start >= a.length - 1) return a.length - 1;
        for (i = start; i < a.length - 1; i++) {
            if (i >= a.length - 1) return a.length;
            runLength--;
            if (runLength > 0) insertionSortShort(i, start,a);
            else if (less(a[i + 1], a[i])) return i;
        }
        return i;
    }
}
