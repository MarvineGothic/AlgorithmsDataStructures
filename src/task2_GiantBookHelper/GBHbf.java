package task2_GiantBookHelper;

import edu.princeton.cs.algs4.StdOut;

import java.io.*;

public class GBHbf {
    private static int lastIsolatedV;
    private static int giantComp;
    private static int connected;

    public static void main(String[] args) {

        /*String[] files = {"oneUF.txt", "twoUF.txt", "threeUF.txt", "threeUFm.txt", "fourUF.txt", "fiveUF.txt"};
        for (String s : files) {
            try {
                StdOut.println(s);
                printResult(new FileReader("data/" + s));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }*/
       Generator.test(1000, 1);
        //System.out.println(Generator.generator(10, 3));
    }

    public static void printResult(Reader s) {
        try (BufferedReader in = new BufferedReader(s)) {
            String line;
            int nElem = Integer.parseInt(in.readLine());
            MyUnionFind gbh = new MyUnionFind(nElem);
            while ((line = in.readLine()) != null) {
                int p = Integer.parseInt(line.trim().split("\\s+")[0]);
                int q = Integer.parseInt(line.trim().split("\\s+")[1]);
                gbh.erdosRenye(p, q);
            }
            lastIsolatedV = gbh.getLastIsolatedV();
            giantComp = gbh.getGiantComp();
            connected = gbh.getConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getLastIsolatedV() {
        return lastIsolatedV;
    }

    public static int getGiantComp() {
        return giantComp;
    }

    public static int getConnected() {
        return connected;
    }
}
        /*
        oneUF.txt:
        10 16 4 16
        twoUF.txt:
        100 254 78 254
        threeUF.txt:
        11 22 7 22
        fourUF.txt:
        12 12 8 13
        fiveUF.txt:
        99 233 72 233*/
