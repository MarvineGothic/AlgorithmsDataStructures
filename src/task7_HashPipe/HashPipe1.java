package task7_HashPipe;

/**
 * @author Sergiy Isakov
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class HashPipe1<K extends Comparable<K>, V> {
    private Nope1[] first;
    private int size;

    public HashPipe1() {
        size = 0;
        first = new Nope1[32];
    }

    static int hash(Object key) {
        return (key == null) ? 0 : Integer.numberOfTrailingZeros(Integer.parseInt(Integer.toBinaryString(key.hashCode()))) + 1;
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        int index = hash(key) - 1;
        Nope1 x = getNope(key, index);
        if (x != null) {
            x.value = val;
            return;
        }

        for (int i = 0; i <= index; i++) {
            if (first[i] == null) first[i] = new Nope1();
            Nope1 floor = floorNope(key, i);
            if (floor == null)
                first[i].next = new Nope1(index + 1, key, val, first[i].next);
            else
                floor.next = new Nope1(index + 1, key, val, floor.next);
        }

        size++;
    }

    public V get(K key) {
        Nope1 x = getNope(key, hash(key) - 1);
        return x == null ? null : (V) x.value;
    }

    public K floor(K key, int index) {
        Nope1 x = floorNope(key, index);
        return x == null ? null : (K) x.key;
    }

    private Nope1 floorNope(K key, int index) {
        for (Nope1 x = first[index]; x != null; x = x.next)
            if (x.key != null && ((K) x.key).compareTo(key) <= 0 && (x.next == null || ((K) x.next.key).compareTo(key) > 0))
                return x;
        return null;
    }
    /*&& x.next != null && x.next.key != null*/
    public Nope1 getNope(K key, int index) {
        for (Nope1 x = first[index]; x != null; x = x.next)
            if (key.equals(x.key))
                return x;
        return null;
    }

    public K control(K key, int h) {
        Nope1 x = getNope(key, h);
        return x == null || x.next == null ? null : (K) x.next.key;
    }

    public Iterable<K> keys(K lo, K hi) {
        return null;
    }
}

@SuppressWarnings("all")
class Nope1<K, V> {
    int hash;
    K key;
    V value;
    Nope1<K, V> next;

    public Nope1() {
    }

    public Nope1(int hash, K key, V value, Nope1 next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
