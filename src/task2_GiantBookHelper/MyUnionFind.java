package task2_GiantBookHelper;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyUnionFind {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components
    private int numberOfVertices;          // number of vertices

    private Set<Integer> setOfVertices = new HashSet<>();   // set of used vertices
    private int time = 0;                                   // time fot each connection
    private int lastIsolatedVertex = -1;                    // time for last isolated vertex
    private int giantComponent = -1;                        // time for first giant component
    private int graphConnected = -1;                        // time for first connected graph


    public MyUnionFind(int n) {
        this.numberOfVertices = n;
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public static void main(String[] args) {
        generateTests(100, 100_000);
        generateTests(1000, 10_000);
        generateTests(10_000, 1000);
        generateTests(100_000, 100);
        generateTests(1000_000, 50);
        generateTests(10_000_000, 10);
    }

    /**
     * Method testing MyUnionFind by generating sites with given number of vertices.
     *
     * @param numberOfVertices - number of vertices, needed to create a site
     * @param tests            number of tests to run
     */
    private static void generateTests(int numberOfVertices, int tests) {
        double[] lastIsolatedVertices = new double[tests];
        double[] giantComponents = new double[tests];
        double[] connectedGraphs = new double[tests];
        long start = System.nanoTime();
        for (int i = 0; i < tests; i++) {
            MyUnionFind uf = new MyUnionFind(numberOfVertices);
            while (uf.getGraphConnected() == -1) {
                int p = StdRandom.uniform(numberOfVertices);
                int q = StdRandom.uniform(numberOfVertices);
                uf.erdosRenyi(p, q);
            }
            lastIsolatedVertices[i] = uf.getLastIsolatedVertex();
            giantComponents[i] = uf.getGiantComponent();
            connectedGraphs[i] = uf.getGraphConnected();
        }
        // calculate Average and Deviation of last Isolated Vertex
        double averageLIV = StdStats.mean(lastIsolatedVertices);
        double deviationLIV = StdStats.stddev(lastIsolatedVertices);
        // calculate Average and Deviation of Giant Component
        double averageGC = StdStats.mean(giantComponents);
        double deviationGC = StdStats.stddev(giantComponents);
        // calculate Average and Deviation of Connected Graph
        double averageConnectedGraphs = StdStats.mean(connectedGraphs);
        double deviationConnectedGraphs = StdStats.stddev(connectedGraphs);

        StdOut.printf("N: %d\nT: %d\n" +
                        "Average Time For Giant Component:      %s StdDev: (%s)\n" +
                        "Average Time For Last Isolated Vertex: %s StdDev: (%s)\n" +
                        "Average Time For Connected Graph:      %s StdDev: (%s)\n" +
                        "Testing Time:                          %s sec\n\n",
                numberOfVertices, tests, format(averageGC), format(deviationGC), format(averageLIV),
                format(deviationLIV), format(averageConnectedGraphs), format(deviationConnectedGraphs),
                format((System.nanoTime() - start) / 1e9));
    }

    /**
     * Method to format double into String value
     * using scientific notation
     *
     * @param num double number to be formatted
     * @return String of formatted number as x.xx
     */
    private static String format(double num) {
        return String.format("%.2e", num);
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code numberOfVertices})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one object
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < numberOfVertices}
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < numberOfVertices} and {@code 0 <= q < numberOfVertices}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < numberOfVertices} and {@code 0 <= q < numberOfVertices}
     */

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        setOfVertices.add(p);
        setOfVertices.add(q);
        if (rootP == rootQ) return;
        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    /**
     * Method that uses Erdos-Renyi model
     * and calculates the time of first giant component,
     * last isolated vertex and graphConnected graph
     *
     * @param p element one
     * @param q element two
     */

    public void erdosRenyi(int p, int q) {
        union(p, q);
        setOfVertices.add(p);
        setOfVertices.add(q);
        time++;
        if (setOfVertices.size() == numberOfVertices && lastIsolatedVertex == -1)
            lastIsolatedVertex = time;
        if (size[find(p)] >= (double) numberOfVertices / 2 && giantComponent == -1)
            giantComponent = time;
        if (count() == 1 && graphConnected == -1) graphConnected = time;
    }

    public int getLastIsolatedVertex() {
        return lastIsolatedVertex;
    }

    public int getGiantComponent() {
        return giantComponent;
    }

    public int getGraphConnected() {
        return graphConnected;
    }
}
