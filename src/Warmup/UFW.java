package Warmup;

/******************************************************************************
 *  Compilation:  javac UF.java
 *  Execution:    java UF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                http://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                http://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Weighted quick-union by rank with path compression by halving.
 *
 *  % java UF < tinyUF.txt
 *  true
 *
 ******************************************************************************/

// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
// import edu.princeton.cs.algs4.UF;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UFW {
    public static void main(String[] args) {
        String[] files = {"tinyUF.txt", "mediumUF.txt", "largeUF.txt"};
        for (String s : files) {
            try (BufferedReader in = new BufferedReader(new FileReader("data/" + s))) {
                String line;
                line = in.readLine();
                WeightedQuickUnionUF uf = new WeightedQuickUnionUF(Integer.parseInt(line));
                while ((line = in.readLine()) != null) {
                    int p = Integer.parseInt(line.split("\\s+")[0]);
                    int q = Integer.parseInt(line.split("\\s+")[1]);
                    if (uf.connected(p, q)) continue;
                    uf.union(p, q);
                }
                StdOut.println(s + ": " + uf.connected(0, 1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                StdOut.println("File is not found");
            } catch (IOException e) {
                e.printStackTrace();
                StdOut.println("Input/output exception");
            }
        }

        /*int n = StdIn.readInt();
        UF uf = new UF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }
        StdOut.println(uf.connected(0, 1));*/
    }
}