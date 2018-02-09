package task2_GiantBookHelper;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class GBH {
    public static void main(String[] args) {
        int n = StdIn.readInt();                            // number of vertices
        MyUnionFind gbh = new MyUnionFind(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            gbh.erdosRenye(p, q);
        }
        StdOut.println(n + " "  + gbh.getLastIsolatedV() + " " + gbh.getGiantComp() + " " + gbh.getConnected());
    }
}
