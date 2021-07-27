package task6_Runsort;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

import static task6_Runsort.RunsortIndexes.sort;
import static task6_Runsort.SortFunctions.isSorted;
import static task6_Runsort.SortFunctions.less;


public class RunsortTest {
    private static Comparable[] toSort;

    public static void main(String[] args) {
        simpleTests();
       randomTests(10, 5_000_000);
        randomTests(20, 5_000_000);
        //normalizedDoubles(a);
        //StdStats.plotBars(toSort);
    }

    public static void normalizedDoubles(Comparable[] a) {
        double[] doubles = new double[a.length];
        if (a instanceof String[]) {
            for (int i = 0; i < doubles.length; i++) {
                doubles[i] = ((String) a[i]).toCharArray()[0];
                System.out.println(doubles[i]);
            }
        }
    }

    public static double minMax(double vi, double mina, double maxa, double newMina, double newMaxa) {
        return newMina + (vi - mina) * (newMaxa - newMina) / (maxa - mina);
    }

    public static void randomTests(int tests, int testSize) {
        double avg[] = new double[tests];
        for (int i = 0; i < tests; i++) {
            randomGenerator(testSize, 1000);
            long startTime = System.nanoTime();
            sort(toSort);
            avg[i] = (double) (System.nanoTime() - startTime) / 1_000_000_000;
            System.out.println((i + 1) + " done");
            System.out.println(isSorted(toSort));
        }
        System.out.printf("Average running time: %.2f sec\n", StdStats.mean(avg));
    }

    public static void simpleTests() {
        Comparable[] a = "RUNSORTEXAMPLE".split("");
        a = "MERGESORTEXAMPLE".split("");
        sort(a);
        System.out.println(Arrays.toString(a) + " " + isSorted(a));
        a = "SEARCHEXAMPLE".split("");
        sort(a);
        System.out.println(Arrays.toString(a) + " " + isSorted(a));
        a = "RTCHIMNOAERBKLD".split("");
        sort(a);
        System.out.println(Arrays.toString(a) + " " + isSorted(a));
        //a="CHIMNORTABEKLRD".split("");
        a = ("bed bug dad yes zoo\n" +
                "now for tip ilk dim\n" +
                "tag jot sob nob sky\n" +
                "hut men egg few jay\n" +
                "owl joy rap gig wee\n" +
                "was wad fee tap tar\n" +
                "dug jam all bad yet").split("\\s+");
        sort(a);
        System.out.println(Arrays.toString(a) + " " + isSorted(a));
    }




    /**
     * Just a generator for testing sorting algorithm
     *
     * @param testSize
     * @param maxElement
     */
    public static void randomGenerator(int testSize, int maxElement) {
        toSort = new Comparable[testSize];
        for (int i = 0; i < testSize; i++) {
            toSort[i] = StdRandom.uniform(maxElement);
        }
    }

    /**
     * Creates an array of indexes to partition array of comparable elements in sorted parts
     *
     * @param a
     * @param runs
     * @return
     */
    public static int createInitialRunsSimple(Comparable[] a, Integer[] runs) {
        int rI = 0;
        for (int i = 0; i < a.length - 1; i++)
            if (less(a[i + 1], a[i]))
                runs[rI++] = i;
        return rI;
    }

    /**
     * Printing out array of comparable elements in sorted partitions
     *
     * @param a
     * @param runs
     * @param runsIndexCount
     */
    public void checkPartiion(Comparable[] a, Integer[] runs, int runsIndexCount) {
        int k = 0;
        int end = 0;
        for (int i = 0; i < runs.length - 1; i++) {
            end = i >= runsIndexCount ? a.length - 1 : runs[i];
            for (int j = k; j <= end; j++) {
                System.out.print(a[j] + " ");
            }
            k = end + 1;
            System.out.println();
        }
    }
}


/*public static int findNextLess(Comparable[] a, int start) {
        int i;
        if (start >= a.length - 1) return a.length - 1;
        for (i = start; i < a.length - 1; i++) {
            if (i >= a.length - 1) return a.length;
            if (less(a[i + 1], a[i])) return i;
        }
        return i;
    }*/

