package GiantBookHelper;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashSet;
import java.util.Set;

public class GBH {
    public static void main(String[] args) {
        Set<Integer> setOfVertices = new HashSet<>();
        int lastIsolatedV = 0;
        int giantComp = 0;
        int connected = 0;
        int time = 0;
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
            setOfVertices.add(p);
            setOfVertices.add(q);
            time++;
            if (setOfVertices.size() == n) {
             //   System.out.println(time);
                lastIsolatedV = time;
            }
            if ((p == n / 2 || q == n / 2) && giantComp == 0) {
                giantComp = time;

              //  System.out.println(giantComp);
            }
            if (connected == 0 && lastIsolatedV != 0) {
                connected = time;
               // System.out.println("First time connected" + connected);
            }
        }
        StdOut.println(n + " " + lastIsolatedV + " " + giantComp + " " + connected);
    }
}
