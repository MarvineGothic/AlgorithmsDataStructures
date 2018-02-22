package task3_RandomQueue;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Test4Iterator {
    public static void main(String[] args) {
        // Build a queue containing the Integers 1,2,...,6:
        RandomQueue<Integer> Q= new RandomQueue<Integer>();
        for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!

        boolean passedAll = true;
        int sum = 0;
        int rep = 100;
        for(int x=0;x<rep;x++) {
            int [] taken = new int[7];
            Iterator<Integer> I= Q.iterator();
            int j =0;
            while (I.hasNext()){
                j++;
                int k = I.next();
                if( k <1 || k > 6 )
                    StdOut.printf("Not an element %d (at %d deque)\n",k, j);
                taken[k]++;
                if( 3 == j ) sum += k;
            }

            boolean allOut = true;
            for (int i = 1; i < 7; ++i) if(taken[i] != 1) allOut = false;
            if( ! allOut) {
                for (int i = 1; i < 7; ++i)
                    StdOut.printf("Taken[%d] = %d\n", i,taken[i]);
                passedAll = false;
            }
        }
        if( passedAll)
            StdOut.println("All Out on all iterators passed");
        {
            double mean = (1.0*sum)/rep;
            double minM =3.0, maxM = 4.1;
            if( mean > minM && mean < maxM )
                StdOut.printf("Standard mean on iterator between %5.2f and %5.2f\n", minM,maxM);
            else
                StdOut.printf("Standard mean on iterator outside %5.2f and %5.2f:  %5.2f\n",
                        minM,maxM,mean);
        }
    }
}