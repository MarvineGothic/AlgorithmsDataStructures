package task7_HashPipe;

import java.util.Iterator;

/**
 * @author Sergiy Isakov
 */
@SuppressWarnings("all")
public class HashPipe {
    private Nope root;
    private int size;
    private int rootMaxSize = 0;
    private Nope[] previousNopes;
    private Nope nope;

    public HashPipe() {
        size = 0;
        root = new Nope(32, null, null);
    }

    static int hash(String key) {
        return (key == null) ? 0 : trailingZeros(Integer.toBinaryString(key.hashCode())) + 1;
    }

    private static int trailingZeros(String str) {
        int i = str.length() - 1;
        while (str.charAt(i) == '0') {
            i--;
        }
        return str.length() - 1 - i;
    }

    public int size() {
        return size;
    }

    public void put(String key, Integer val) {
        nope = new Nope(hash(key), key, val);
        Nope prev = floorNope(key);

        if (prev.key != null && prev.key.equals(key))
            prev.value = val;
        else
            for (int i = 0; i < nope.hash; i++) {
                nope.next[i] = previousNopes[i].next[i];
                previousNopes[i].next[i] = nope;
            }
        size++;
    }

    private Nope floorNope(String key) {
        Nope floor = root;
        int nopeHash = nope == null ? 1 : nope.hash;

        previousNopes = new Nope[nope == null ? 1 : nopeHash];
        rootMaxSize = nopeHash - 1 > rootMaxSize ? nopeHash - 1 : rootMaxSize;

        boolean run = true;
        while (run) {
            if (floor.next[0] == null) {
                for (int i = 0; i < previousNopes.length; i++) if (previousNopes[i] == null) previousNopes[i] = floor;
                return floor;
            }

            for (int i = floor.equals(root) ? rootMaxSize : floor.next.length - 1; i >= 0; i--) {
                Nope next = floor.next[i];

                if (next != null) {
                    if ((next.key).compareTo(key) <= 0) {
                        floor = next;
                    } else if (i == 0) run = false;
                }

                if (i < previousNopes.length)
                    previousNopes[i] = floor;
                if (floor.equals(next)) break;
            }
        }
        return floor;
    }

    public String control(String key, int h) {
        Nope result = floorNope(key);
        return result.hash <= h || result.next[h] == null ? null : result.next[h].key;
    }

    public Iterable<String> keys(String lo, final String hi) {
        final String[] c = {lo};
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    @Override
                    public boolean hasNext() {
                        return c[0] == null ? false : c[0].compareTo(hi) <= 0 ? true : false;
                    }

                    @Override
                    public String next() {
                        final String next = c[0];
                        c[0] = floorNope(c[0]).next[0] == null ? null : floorNope(c[0]).next[0].key;
                        return next;
                    }
                    @Override
                    public void remove(){};
                };
            }
        };
    }
}

class Nope {
    int hash;
    Nope[] next;
    String key;
    Integer value;

    Nope(int hash, String key, Integer value) {
        this.hash = hash;
        this.next = new Nope[hash];
        this.key = key;
        this.value = value;
    }
}