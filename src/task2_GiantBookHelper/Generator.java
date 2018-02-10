package task2_GiantBookHelper;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

class Generator {

    public static void main(String[] args) {
        Generator.test(10_000_000, 1);
    }

    /**
     * Method testing MyUnionFind by generating sites with given number of vertices.
     * @param numberOfVertices - number of vertices, needed to create a site
     * @param tests number of tests to run
     */
    static void test(int numberOfVertices, int tests) {
        double[] lastIsolatedVertex = new double[tests];
        double[] giantComponent = new double[tests];
        double[] connectedGraph = new double[tests];
        long start = System.nanoTime();
        for (int i = 0; i < tests; i++) {
            MyUnionFind uf = new MyUnionFind(numberOfVertices);
            while (uf.getConnected() == -1) {
                int p = StdRandom.uniform(numberOfVertices);
                int q = StdRandom.uniform(numberOfVertices);
                uf.erdosRenyi(p, q);
            }
            lastIsolatedVertex[i] = uf.getLastIsolatedV();
            giantComponent[i] = uf.getGiantComp();
            connectedGraph[i] = uf.getConnected();
        }
        // calculate Average and Deviation of lastIsolated Vertex
        double lVAverage = StdStats.mean(lastIsolatedVertex);
        double lVDeviation = StdStats.stddev(lastIsolatedVertex);
        //--------------------------------------------
        // calculate Average and Deviation of Giant Component
        double gCAverage = StdStats.mean(giantComponent);
        double gCDeviation = StdStats.stddev(giantComponent);
        //--------------------------------------------
        // calculate Average and Deviation of Connected Graph
        double cAverage = StdStats.mean(connectedGraph);
        double cDeviation = StdStats.stddev(connectedGraph);

        StdOut.printf("N: %d\nT: %d\nGiantComp: %s (%s)\nNo Isolated: %s (%s)\nConnected: %s (%s)\nTime: %s sec",
                numberOfVertices, tests, format(gCAverage), format(gCDeviation), format(lVAverage),
                format(lVDeviation), format(cAverage), format(cDeviation), format((System.nanoTime()-start)/1e9));
    }

    /**
     * Method to format double into String value
     * using scientific notation
     *
     * @param num
     * @return
     */
    public static String format(double num) {
        return String.format("%.2e", num);
    }
}
