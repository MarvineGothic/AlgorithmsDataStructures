package task4_Foursum;

import java.util.Arrays;
import java.util.Scanner;

public class Faster2NlogN implements FourSum{
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int N = Integer.parseInt(S.nextLine());
        long[] vals = new long[N];
        for (int i = 0; i < N; i += 1) vals[i] = Long.parseLong(S.nextLine());
        findFourElements(vals, 0);
    }

    public static void findFourElements(long[] array, int givenSum) {
        int n = array.length;
        int i, j;
        int size = (n * (n - 1)) / 2;
        PairSum[] aux = new PairSum[size];
        for (int m = 0; m < aux.length; m++) {
            aux[m] = new PairSum();
        }

        int k = 0;
        for (i = 0; i < n - 1; i++)
            for (j = i + 1; j < n; j++) {
                aux[k].sum = array[i] + array[j];
                aux[k].first = i;
                aux[k].sec = j;
                k++;
            }
        Arrays.sort(aux);

        i = 0;
        j = size - 1;
        while (i < size && j >= 0) {
            if ((aux[i].sum + aux[j].sum == givenSum) && aux[i].noCommon(aux[j])) {
                /*System.out.printf("%d, %d, %d, %d\n", array[aux[i].first], array[aux[i].sec],
                        array[aux[j].first], array[aux[j].sec]);*/
                System.out.println(true);
                return;
            } else if (aux[i].sum + aux[j].sum < givenSum)
                i++;
            else j--;
        }
        System.out.println(false);
    }
}

class PairSum implements Comparable<PairSum> {
    int first = 0;
    int sec = 0;
    long sum = 0;

    public boolean noCommon(PairSum b) {
        return this.first != b.first && this.first != b.sec && this.sec != b.first && this.sec != b.sec;
    }

    @Override
    public int compareTo(PairSum o) {
        return Long.compare(this.sum, o.sum);
    }
}
