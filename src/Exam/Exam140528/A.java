package Exam.Exam140528;

import java.util.Iterator;

public class A<Key, Value> implements Iterable<Boolean>{
    private Key[] keys;
    private Value[] vals;
    private int N;

    public A(int capacity) {
        keys = (Key[]) new Object[capacity];
        vals = (Value[]) new Object[capacity];
        N = 0;
    }

    public static void main(String[] args) {
        int N = 2000_000_000;
        A a = new A(N/2);
        for (int i = 0; i < N; i++) {
            a.put(0, i);
        }
    }

    public Value get(Key key) {
        for (int i = 0; i < N; i++)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public void put(Key key, Value val) {
        for (int i = 0; i < N; i++)
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        keys[N] = key;
        vals[N] = val;
        N += 1;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return null;
    }
}

