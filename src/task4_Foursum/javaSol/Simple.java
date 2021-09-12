package task4_Foursum.javaSol;

import task4_Foursum.FourSum;

import java.util.Scanner;

public class Simple implements FourSum {
    private static String err;

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int N = Integer.parseInt(S.nextLine());
        long[] vals = new long[N];
        for (int i = 0; i < N; i += 1) vals[i] = Long.parseLong(S.nextLine());
        System.out.println(new Simple().evaluateFoursum(N, vals));
        if (err != null) System.err.println(err);
        System.exit(0);
    }

    public boolean evaluateFoursum(int N, long[] vals) {
        long startTime = System.nanoTime();
        // N^4
        for (int i = 0; i < N - 3; i++)
            for (int j = i + 1; j < N - 2; j++)
                for (int k = j + 1; k < N - 1; k++)
                    for (int l = k + 1; l < N; l++) {
                        if ((System.nanoTime() - startTime) > 61_000_000_000.0) {
                            System.out.println("Time Limit Exceeded");
                            return false;
                        }
                        if (vals[i] + vals[j] + vals[k] + vals[l] == 0) {
                            err = (i + " " + j + " " + k + " " + l);
                            return true;
                        }
                    }
        return false;
    }
}