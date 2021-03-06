package task2_GiantBookHelper;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class GiantBook {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        MyUnionFind uf = new MyUnionFind(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.erdosRenyi(p, q);
        }
        StdOut.println(n + " "  + uf.getLastIsolatedVertex() + " " + uf.getGiantComponent() + " " + uf.getGraphConnected());
    }
}
