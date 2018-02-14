package task3_RandomQueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class RandomQueue<Item> implements Iterable<Item>
{
    private Item[] elements;
    // Mine takes ca. 60 lines, my longest method has 5 lines.

    public RandomQueue() { // create an empty random queue
        elements = (Item[]) new Object[0];
    }
    public boolean isEmpty() {// is it empty?
        return size()==0;
    }
    public int size() {// return the number of elements
        return elements.length;
    }
    public void enqueue(Item item) {// add an item
        elements = Arrays.copyOf(elements, size()+1);
        elements[size()-1] = item;
    }
    public Item sample(){ // return (but do not remove) a random item
        return elements[StdRandom.uniform(size())];
    }
    public Item dequeue(){ // remove and return a random item
        int indexOfRemovedElement = StdRandom.uniform(size());
        Item removedElement = (Item) elements[indexOfRemovedElement];
        Object[] tempElements = new Object[elements.length-1];
        System.arraycopy(elements, 0, tempElements, 0, indexOfRemovedElement);
        System.arraycopy(elements, indexOfRemovedElement+1, tempElements, indexOfRemovedElement, tempElements.length-indexOfRemovedElement);
        elements = (Item[]) tempElements;
        return removedElement;
    }
    public Iterator<Item> iterator() { // return an iterator over the items in random order
        return new RandomQueueIterator();
    }
    private class RandomQueueIterator implements Iterator<Item> {
        int currentIndex = 0;

        public RandomQueueIterator() {
            StdRandom.shuffle(elements);
        }
        public boolean hasNext()  {
            return size() != currentIndex;
        }
        public void remove()      { throw new UnsupportedOperationException();  }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[currentIndex++];
        }
    }

    // The main method below tests your implementation. Do not change it.
    public static void main(String args[])
    {
        // Build a queue containing the Integers 1,2,...,6:
        RandomQueue<Integer> Q= new RandomQueue<Integer>();
        for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!

        // Print 30 die rolls to standard output
        StdOut.print("Some die rolls: ");
        for (int i = 1; i < 30; ++i) StdOut.print(Q.sample() +" ");
        StdOut.println();

        // Let's be more serious: do they really behave like die rolls?
        int[] rolls= new int [10000];
        for (int i = 0; i < 10000; ++i)
            rolls[i] = Q.sample(); // autounboxing! Also cool!
        StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
        StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n",
                StdStats.stddev(rolls));

        // Now remove 3 random values
        StdOut.printf("Removing %d %d %d\n", Q.dequeue(), Q.dequeue(), Q.dequeue());
        // Add 7,8,9
        for (int i = 7; i < 10; ++i) Q.enqueue(i);
        // Empty the queue in random order
        while (!Q.isEmpty()) StdOut.print(Q.dequeue() +" ");
        StdOut.println();

        // Let's look at the iterator. First, we make a queue of colours:
        RandomQueue<String> C= new RandomQueue<String>();
        C.enqueue("red"); C.enqueue("blue"); C.enqueue("green"); C.enqueue("yellow");

        Iterator<String> I= C.iterator();
        Iterator<String> J= C.iterator();

        StdOut.print("Two colours from first shuffle: "+I.next()+" "+I.next()+" ");

        StdOut.print("\nEntire second shuffle: ");
        while (J.hasNext()) StdOut.print(J.next()+" ");

        StdOut.println("\nRemaining two colours from first shuffle: "+I.next()+" "+I.next());
    }
}
