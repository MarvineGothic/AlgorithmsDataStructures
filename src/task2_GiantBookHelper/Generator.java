package task2_GiantBookHelper;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {
    private static List<Integer> lastIsolatedV = new ArrayList<>();
    private static List<Integer> giantComp = new ArrayList<>();
    private static List<Integer> connected = new ArrayList<>();


    public static void test(int n, int tests) {
        long start = 0;
        long lap = 0;
        for (int i = 0; i < tests; i++) {
            int valid = -1;
            String file;
            while (valid == -1) {
                start = System.nanoTime();
                System.out.println("Start generator");
                file = generator(n, 2);
                //System.out.println(file);
                GBHbf.printResult(new StringReader(file));
                valid = GBHbf.getConnected();
                System.out.println("Valid: " + valid);
            }
            lap = System.nanoTime();
            System.out.printf("Successfully generated: time %s sec\n", format((lap - start) / 1e9));
            StdOut.println(n + " " + GBHbf.getLastIsolatedV() + " " + GBHbf.getGiantComp() + " " + GBHbf.getConnected());
            lastIsolatedV.add(GBHbf.getLastIsolatedV());
            giantComp.add(GBHbf.getGiantComp());
            connected.add(GBHbf.getConnected());
        }

        int[] lIVArray = {lastIsolatedV.size()};
        for (Integer i : lastIsolatedV) {
            lIVArray[lIVArray.length - 1] = i;
        }
        double lIVAv = StdStats.mean(lIVArray);
        double stdDevLIV = StdStats.stddev(lIVArray);
        //--------------------------------------------
        int[] gCArray = {giantComp.size()};
        for (Integer i : giantComp) {
            gCArray[gCArray.length - 1] = i;
        }
        double gCAv = StdStats.mean(gCArray);
        double stdDevGC = StdStats.stddev(gCArray);
        //--------------------------------------------
        int[] conArray = {connected.size()};
        for (Integer i : connected) {
            conArray[conArray.length - 1] = i;
        }
        double conAv = StdStats.mean(conArray);
        double stdDevCon = StdStats.stddev(conArray);

        StdOut.printf("N: %d\nT: %d\nNo Isolated: %s (%s)\nGiantComp: %s (%s)\nConnected: %s (%s)\nTime elapsed: %s sec",
                n, tests, format(lIVAv), format(stdDevLIV), format(gCAv), format(stdDevGC), format(conAv), format(stdDevCon), format((System.nanoTime() - lap) / 1e9));
    }

    public static String generator(int numElem, double size) {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        StringBuilder sb = new StringBuilder(String.valueOf(numElem));
        // double x = StdRandom.uniform(1.0, numElem * size);
        for (int i = 0; i < Math.ceil(numElem * size); i++) {
            int a = -1;
            int b = -1;
            while (/*(Collections.frequency(listA, a) > numElem)||(Collections.frequency(listB, b) > numElem) ||*/ a == b || a == -1 || b == -1) {
                a = StdRandom.uniform(numElem);
                b = StdRandom.uniform(numElem);
            }
            //System.out.println("Add:" + a);
            /*listA.add(a);
            listB.add(b);*/
            sb.append("\n").append(a).append(" ").append(b);
        }
        return sb.toString();
    }

    public static String format(double num) {
        return String.format("%.2e", num);
    }
}
