package task1_Warmup;
/******************************************************************************
 *  Compilation:  javac UF.java
 *  Execution:    java UF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                http://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                http://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Weighted quick-erdosRenyi by rank with path compression by halving.
 *
 *  % java UF < tinyUF.txt
 *  true
 *
 ******************************************************************************/

// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
// import edu.princeton.cs.algs4.UF;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UFWbr {
    public static void main(String[] args) {
        String[] files = {"tinyUF.txt", "mediumUF.txt", "largeUF.txt"};
        long startTime = System.nanoTime();
        for (String s : files) {
            try (BufferedReader in = new BufferedReader(new FileReader("data/" + s))) {
                String line;
                line = in.readLine();
                UF uf = new UF(Integer.parseInt(line));
                while ((line = in.readLine()) != null) {
                    int p = Integer.parseInt(line.split("\\s+")[0]);
                    int q = Integer.parseInt(line.split("\\s+")[1]);
                    if (uf.connected(p, q)) continue;
                    uf.union(p, q);
                }
                long time = System.nanoTime() - startTime;
                String elapsed = String.format("%2f", (time) / 1e6);
                System.out.println(TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS));
                StdOut.println("Using: UF\nConnected: " + uf.connected(0, 1) + "\nTime elapsed: " + elapsed + " ms");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                StdOut.println("File is not found");
            } catch (IOException e) {
                e.printStackTrace();
                StdOut.println("Input/output exception");
            }
        }
    }
}