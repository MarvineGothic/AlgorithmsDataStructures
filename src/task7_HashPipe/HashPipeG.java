package task7_HashPipe;

import java.util.Iterator;

/**
 * @author Sergiy Isakov
 */
@SuppressWarnings("all")
public class HashPipeG<K extends Comparable<K>, V> {
    private NopeG root;
    private int size;
    private int rootMaxSize = 0;
    private NopeG[] previousNopes;
    private NopeG nope;

    public HashPipeG() {
        size = 0;
        root = new NopeG(32, null, null);
    }

    static int hash(Object key) {
        return (key == null) ? 0 : trailingZeros(Integer.toBinaryString(key.hashCode())) + 1;
    }

    public static int trailingZeros(String str) {
        int i = str.length() - 1;
        while (str.charAt(i) == '0') {
            i--;
        }
        return str.length() - 1 - i;
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        nope = new NopeG(hash(key), key, val);
        NopeG prev = floorNope(key);

        if (prev.key != null && prev.key.equals(key))
            prev.value = val;
        else
            for (int i = 0; i < nope.hash; i++) {
                nope.next[i] = previousNopes[i].next[i];
                previousNopes[i].next[i] = nope;
            }
        size++;
    }

    private NopeG floorNope(K key) {
        NopeG floor = root;
        previousNopes = new NopeG[nope.hash];
        rootMaxSize = nope.hash - 1 > rootMaxSize ? nope.hash - 1 : rootMaxSize;

        boolean run = true;
        while (run) {
            if (floor.next[0] == null) {
                for (int i = 0; i < previousNopes.length; i++) if (previousNopes[i] == null) previousNopes[i] = floor;
                return floor;
            }

            for (int i = floor.equals(root) ? rootMaxSize : floor.next.length - 1; i >= 0; i--) {
                NopeG next = floor.next[i];

                if (next != null) {
                    if (((K) next.key).compareTo(key) <= 0) {
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
        NopeG result = floorNope((K) key);
        return result.hash <= h || result.next[h] == null ? null : (String) result.next[h].key;
    }

    public Iterable<K> keys(K lo, final K hi) {
        final K[] c = (K[]) new Comparable[]{lo};
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    @Override
                    public boolean hasNext() {
                        return c[0] == null ? false : c[0].compareTo(hi) <= 0 ? true : false;
                    }

                    @Override
                    public K next() {
                        final K next = c[0];
                        c[0] = floorNope(c[0]).next[0] == null ? null : (K) floorNope(c[0]).next[0].key;
                        return next;
                    }
                    @Override
                    public void remove(){};
                };
            }
        };
    }
}

class NopeG<K, V> {
    int hash;
    NopeG[] next;
    K key;
    V value;

    NopeG(int hash, K key, V value) {
        this.hash = hash;
        this.next = new NopeG[hash];
        this.key = key;
        this.value = value;
    }
}