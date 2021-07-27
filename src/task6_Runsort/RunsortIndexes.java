package task6_Runsort;

import static task6_Runsort.SortFunctions.*;

@SuppressWarnings("unchecked")
public class RunsortIndexes {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        Integer[] array = getRunIndexes(a);
        int k = 0;
        for (int i = 0; i < array.length; ) {
            int end = i == array.length - 1 ? a.length - 1 : array[i + 1] - 1;
            merge(a, aux, k, array[i] - 1, end);
            k = end + 1;
            i += 2;
        }
        if (array.length > 1) sort(a);
    }

    public static Integer[] getRunIndexes(Comparable[] a) {
        int runSize = 0;
        int runLength = runSize;
        int offset = 0;
        Integer[] runIndexes;
        Integer[] temp = new Integer[a.length];
        int rI = 0;
        for (int i = 0; i < a.length - 1; i++) {
            runLength--;
            if (runLength > 0)
                insertionSortShort(i, offset, a);
            else if (less(a[i + 1], a[i])) {
                temp[rI++] = i + 1;
                runLength = runSize;
                offset = i + 1;
            }

        }
        runIndexes = new Integer[rI];
        System.arraycopy(temp, 0, runIndexes, 0, rI);
        return runIndexes;
    }
}