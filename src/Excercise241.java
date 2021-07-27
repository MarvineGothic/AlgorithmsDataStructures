import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;


// Execise session 5
public class Excercise241 {
    public Excercise241() {

    }

    public static void main(String[] args) {
        Excercise241 ex = new Excercise241();
        ex.runExercises();
    }

    public void runExercises() {
        ex241();
    }

    // Exercise 2.4.1
    private void ex241() {
        MaxPQ<String> pq = new MaxPQ<String>();
        pq.insert("P");
        pq.insert("R");
        pq.insert("I");
        pq.insert("O");        // P R I O
        printMaxFromPQ(pq);                                                // *
        pq.insert("R");                                                        // R
        printMaxFromPQ(pq);
        printMaxFromPQ(pq);                                // * *
        pq.insert("I");                                                        // I
        printMaxFromPQ(pq);                                                    // *
        pq.insert("T");                                                        // T
        printMaxFromPQ(pq);                                                    // *
        pq.insert("Y");                                                        // Y
        printMaxFromPQ(pq);
        printMaxFromPQ(pq);
        printMaxFromPQ(pq);            // * * *
        pq.insert("Q");
        pq.insert("U");
        pq.insert("E");                        // Q U E
        printMaxFromPQ(pq);
        printMaxFromPQ(pq);
        printMaxFromPQ(pq);            // * * *

        StdOut.println();
    }

    // Helper method for ex241
    private void printMaxFromPQ(MaxPQ<String> pq) {
        StdOut.print(pq.delMax() + " ");
    }
}

