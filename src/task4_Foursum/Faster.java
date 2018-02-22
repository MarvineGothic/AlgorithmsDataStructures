package task4_Foursum;

import java.util.Arrays;
import java.util.Scanner;

public class Faster implements FourSum{
    private static String err;
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int N = Integer.parseInt(S.nextLine());
        long[] vals = new long[N];
        for (int i = 0; i < N; i += 1) vals[i] = Long.parseLong(S.nextLine());
        System.out.println(new Faster().evaluateFoursum(N, vals));
        if (err != null) System.err.println(err);
        System.exit(0);
    }

    public boolean evaluateFoursum(int N, long[] vals) {
        int l, r;
        Arrays.sort(vals);
        // N^3
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                l = j + 1;
                r = N - 1;
                while (l < r) {
                    if (vals[i] + vals[j] + vals[l] + vals[r] == 0) {
                        err = (i + " " + j + " " + r + " " + l);
                        return true;
                    } else if (vals[i] + vals[j] + vals[l] + vals[r] < 0) l++;
                    else r--;
                }
            }
        }
        return false;
    }
}