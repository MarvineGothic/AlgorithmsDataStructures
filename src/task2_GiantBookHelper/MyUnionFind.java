package task2_GiantBookHelper;


import java.util.HashSet;
import java.util.Set;

public class MyUnionFind {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components
    private int n;

    private Set<Integer> setOfVertices = new HashSet<>();
    private int time = 0;
    private int lastIsolatedV = -1;
    private int giantComp = -1;
    private int connected = -1;

    /**
     * Initializes an empty erdosRenyi–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MyUnionFind(int n) {
        this.n = n;
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }


    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one object
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
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
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
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
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
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
     * last isolated vertex and connected graph
     *
     * @param p element one
     * @param q element two
     */

    public void erdosRenyi(int p, int q) {
        union(p, q);
        setOfVertices.add(p);
        setOfVertices.add(q);
        time++;
        if (setOfVertices.size() == n && lastIsolatedV == -1)
            lastIsolatedV = time;
        if (size[find(p)] >= Math.ceil((double) n / 2) && giantComp == -1)
            giantComp = time;
        if (count() == 1 && connected == -1) connected = time;
    }

    public int getLastIsolatedV() {
        return lastIsolatedV;
    }

    public int getGiantComp() {
        return giantComp;
    }

    public int getConnected() {
        return connected;
    }
}
