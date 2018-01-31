package GiantBookHelper;

import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GBHbf {
    public static void main(String[] args) {

        String[] files = {"oneUF.txt", "twoUF.txt", "threeUF.txt", "fourUF.txt", "fiveUF.txt"};
        for (String s : files) {
            Set<Integer> setOfVertices = new HashSet<>();
            int lastIsolatedV = -1;
            int giantComp = -1;
            int connected = -1;
            int time = 0;
            try (BufferedReader in = new BufferedReader(new FileReader("data/" + s))) {
                String line;
                int n = Integer.parseInt(in.readLine());
                GBH gbh = new GBH(n);
                while ((line = in.readLine()) != null) {
                    int p = Integer.parseInt(line.trim().split("\\s+")[0]);
                    int q = Integer.parseInt(line.trim().split("\\s+")[1]);
                    gbh.union(p, q);

                    setOfVertices.add(p);
                    setOfVertices.add(q);
                    time++;
                    if (setOfVertices.size() == n && lastIsolatedV == -1) {
                        lastIsolatedV = time;
                    }

                    int max = Arrays.stream(gbh.getSize()).max().getAsInt();
                    if (giantComp == -1) {
                        if (max >= Math.ceil((double) n / 2))
                            giantComp = time;
                    }

                    if (gbh.getCount() == 1) connected = time;

                    //System.out.println(time + ": " + "count: " + gbh.getCount() + " size: "+ Arrays.toString(gbh.getSize()));
                }
                System.out.println(s + ": ");
                StdOut.println(n + " " + lastIsolatedV + " " + giantComp + " " + connected);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
