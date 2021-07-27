package task6_Runsort;

import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class SortFunctions {
    // Methods from MergeBU.java

    public static void insertionSortShort(int i, int offset, Comparable[] a) {
        for (int j = i; j >= offset && less(a[j + 1], a[j]); j--)
            exch(a, j + 1, j);
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];  // this copying is unneccessary
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        for (Comparable anA : a)
            StdOut.println(anA);
    }

    public static void bottomDownSort(Comparable[] a, int lo, int hi) {
        Comparable[] aux = new Comparable[a.length];
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        bottomDownSort(a, lo, mid);
        bottomDownSort(a, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
}
