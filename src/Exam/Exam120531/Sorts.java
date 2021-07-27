package Exam.Exam120531;

import java.util.ArrayList;
import java.util.Arrays;

import static task6_Runsort.SortFunctions.*;

public class Sorts {

    public static void main(String[] args) {
        /*final Integer[][] integerArray = {{6425, 5467, 4857, 5479, 4794, 2386, 5678, 9974}};
        ArrayList<Integer[]> integers = new ArrayList<>();
        //Integer[] c = {2386, 5467, 4857, 5479, 4794, 6425, 5678, 9974};
        Integer[] d = {5678, 5467, 4857, 5479, 4794, 2386, 6425, 9974}; // quicksort
        Integer[] e = {9974, 6425, 5678, 5479, 4794, 2386, 4857, 5467}; // heapsort
        Integer[] f = {4857, 5467, 5479, 6425, 2386, 4794, 5678, 9974}; // mergesort
        Integer[] g = {2386, 4794, 4857, 5479, 5467, 6425, 5678, 9974}; // selection
        Integer[] h = {4794, 9974, 6425, 2386, 5467, 4857, 5678, 5479}; // LSD
        Integer[] i = {4857, 5467, 6425, 5479, 4794, 2386, 5678, 9974}; // Insertion
        Integer[] j = {2386, 4857, 4794, 5467, 5479, 5678, 6425, 9974}; // MSD
        //integers.add(c);
        integers.add(d);
        integers.add(e);
        integers.add(f);
        integers.add(g);
        integers.add(h);
        integers.add(i);
        integers.add(j);
        integers.forEach(integ -> {
            System.out.print(SelectionSort(integerArray[0], integ));
            integerArray[0] = new Integer[]{6425, 5467, 4857, 5479, 4794, 2386, 5678, 9974};
            System.out.print(InsertionSort(integerArray[0], integ));
            integerArray[0] = new Integer[]{6425, 5467, 4857, 5479, 4794, 2386, 5678, 9974};
            System.out.print(MergeSort(integerArray[0], integ));
            integerArray[0] = new Integer[]{6425, 5467, 4857, 5479, 4794, 2386, 5678, 9974};
            System.out.print(QuickSort(integerArray[0], integ));
            integerArray[0] = new Integer[]{6425, 5467, 4857, 5479, 4794, 2386, 5678, 9974};
            System.out.print(HeapSort(integerArray[0], integ));
            integerArray[0] = new Integer[]{6425, 5467, 4857, 5479, 4794, 2386, 5678, 9974};
        });*/
        final String[][] s = {"oin gloin bombur bifur".split(" ")};
       // final String[][] st = {"KRATELEPUIMQCXOS".split("")};
        String[] a = "bombur bifur bofur fili gloin kili oin ori".split(" ");
        String[] b = "bofur bifur fili bombur gloin ori kili oin".split(" ");
        String[] c = "bifur bombur gloin oin bofur fili kili ori".split(" ");
        String[] d = "bifur bofur bombur oin gloin fili kili ori".split(" "); // merge
        String[] e = "kili gloin bombur bifur bofur fili oin ori".split(" "); // quick
        ArrayList<String[]> strings = new ArrayList<>();
        strings.add(a);
        strings.add(b);
        strings.add(c);
        strings.add(d);
        strings.add(e);
        strings.forEach(str->{
            /*System.out.println(SelectionSort(s[0], str));
            s[0] = "oin gloin bombur bifur bofur fili kili ori".split(" ");*/
            System.out.println(InsertionSort(s[0], str));
            s[0] = "oin gloin bombur bifur bofur fili kili ori".split(" ");
            /*System.out.println(MergeSort(s[0], str));
            s[0] = "oin gloin bombur bifur bofur fili kili ori".split(" ");*/
            //System.out.println(QuickSort(s[0], str));
            //s[0] = "oin gloin bombur bifur bofur fili kili ori".split(" ");
        });
    }

    public static String SelectionSort(Comparable[] a, Comparable[] compare) {
        String result = "";
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
            if (Arrays.equals(a, compare))
                result = ("Selection sort detected: " + Arrays.toString(compare) + "\n");

            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
        return result;
    }

    public static String InsertionSort(Comparable[] a, Comparable[] compare) {
        String result = "";
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
                if (Arrays.equals(a, compare)) result = ("Insertion sort detected: " +
                        Arrays.toString(compare))+"\n";
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
        return result;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static boolean sort(Comparable[] a, Comparable[] aux, int lo, int hi, Comparable[] compare) {
        boolean fits = false;
        if (hi <= lo) return false;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, compare);
        if (Arrays.equals(a, compare)) fits = true;
        sort(a, aux, mid + 1, hi, compare);
        if (Arrays.equals(a, compare)) fits = true;
        merge(a, aux, lo, mid, hi);
        if (Arrays.equals(a, compare)) fits = true;
        return fits;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static String MergeSort(Comparable[] a, Comparable[] compare) {
        String result = "";
        Comparable[] aux = new Comparable[a.length];
        if (sort(a, aux, 0, a.length - 1, compare)) result = "Merge Sort detected: " + Arrays.toString(compare)+"\n";
        assert isSorted(a);
        return result;
    }

    public static String QuickSort(Comparable[] a, Comparable[] compare) {
        String result = "";
        //StdRandom.shuffle(a);

        if (Arrays.equals(a, compare))
            result = ("Quick Sort detected: " + Arrays.toString(compare))+"\n";
        sort(a, 0, a.length - 1, compare);
        assert isSorted(a);
        return result;
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static boolean sort(Comparable[] a, int lo, int hi, Comparable[] compare) {
        boolean fits = false;
        if (hi <= lo) return false;
        int j = partition(a, lo, hi, compare);
        //System.out.println(Arrays.toString(a));
        if (Arrays.equals(a, compare)) fits = true;
        sort(a, lo, j - 1, compare);
        if (Arrays.equals(a, compare)) fits = true;
        sort(a, j + 1, hi, compare);
        if (Arrays.equals(a, compare)) fits = true;
        assert isSorted(a, lo, hi);
        return fits;
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi, Comparable[] compare) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
            if (Arrays.equals(a, compare)) System.out.println("Quick Sort detected: " + Arrays.toString(compare));
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);
        if (Arrays.equals(a, compare)) System.out.println("Quick Sort detected: " + Arrays.toString(compare));

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static String HeapSort(Comparable[] pq, Comparable[] compare) {
        String result = "";
        boolean fits = false;
        int n = pq.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
            if (Arrays.equals(pq, compare)) fits = true;
        }
        while (n > 1) {
            Hexch(pq, 1, n--);
            if (Arrays.equals(pq, compare)) fits = true;
            sink(pq, 1, n);
            if (Arrays.equals(pq, compare)) fits = true;
        }
        if (fits) {
            result = ("Heap Sort detected: " + Arrays.toString(compare)) +"\n";
        }
        return result;
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            Hexch(pq, k, j);
            k = j;
        }
    }

    private static void Hexch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

}
