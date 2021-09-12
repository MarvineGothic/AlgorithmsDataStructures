
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class Runsort {
    private static Comparable[] aux;      // auxiliary array for merges

    public static void main(String[] args) {
        Comparable[] a = StdIn.readAllStrings();
        sort(a);
        for (Comparable anA : a) StdOut.println(anA);
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        int mid = -1;
        int hi;
        int lo = 0;
        int runs = 0;
        for (int i = 0; i < a.length - 1; i++)
            if (runs % 2 == 1 && less(a[i + 1], a[i])) {
                hi = i;
                merge(a,  lo, mid, hi);
                lo = i + 1;
                runs++;
            } else if (less(a[i + 1], a[i])) {
                mid = i;
                runs++;
            }
        if (runs > 1) sort(a);
        if (runs == 1) merge(a,  0, mid, a.length - 1);
    }
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
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
}