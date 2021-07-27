package task6_Runsort;

import static task6_Runsort.RunsortShift.createInitialRuns;
import static task6_Runsort.SortFunctions.merge;

@SuppressWarnings("unchecked")
public class RunsortShrink {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        Integer[] runs = new Integer[a.length];
        Integer[] temp;
        int initialSize = createInitialRuns(a, runs, 5);
        int runsLength = initialSize;
        while (initialSize >= 1) {
            int lo = 0;
            for (int i = 0; i < initialSize; ) {
                int mid = runs[i];
                int end = i == initialSize - 1 ? a.length - 1 : runs[i + 1];
                merge(a, aux, lo, mid, end);
                runs[i] = -1;
                runsLength--;
                lo = end + 1;
                i += 2;
            }

            temp = runs;
            runs = new Integer[runsLength];
            int index = 0;
            for (int j = 0; j < initialSize; j++)
                if (temp[j] != -1) {
                    runs[index] = temp[j];
                    index++;
                }
            initialSize = runsLength;
        }
    }
}
