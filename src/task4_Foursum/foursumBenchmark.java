package task4_Foursum;

import edu.princeton.cs.algs4.StdStats;
import task4_Foursum.javaSol.Simple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class foursumBenchmark {

    public static void main(String[] args) {
        int testSize = 3200;
        /*printResults("Simple method results:", runTest(100, new Simple(), 100));
        printResults("Simple method results:", runTest(10, new Simple(), 200));
        printResults("Simple method results:", runTest(5, new Simple(), 400));*/
        printResults("Faster method results:", runTest(20, new Faster(), testSize));
        printResults("Fastest method results:", runTest(20, new Fastest(), testSize));
    }

    private static TreeMap<Integer, ArrayList<Integer>> runTest(int numberOfTests, FourSum fourSumClass, int testSize) {
        //System.out.println("Tests: " + fourSumClass.getClass().getSimpleName());
        TreeMap<Integer, ArrayList<Integer>> result = new TreeMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "task4_foursum" + File.separator + "data"))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        try (Scanner s = new Scanner(new File(String.valueOf(path)))) {
                            String fileName = String.valueOf(path.getFileName());

                            int N = Integer.parseInt(s.nextLine());
                            long[] vals = new long[N];
                            for (int i = 0; i < N; i++) vals[i] = Long.parseLong(s.nextLine());

                            if (!(fourSumClass instanceof Simple && N > 800) && N == testSize) {
                                //System.out.println("Trying: " + fileName);
                                for (int i = 0; i < numberOfTests; i++) {
                                    shuffleArray(vals);

                                    Long startTime = System.currentTimeMillis();
                                    fourSumClass.evaluateFoursum(N, vals);
                                    Long stopTime = System.currentTimeMillis();
                                    int runTime = (int) (stopTime - startTime);
                                    if (runTime > 60_000) return;
                                    result.computeIfAbsent(N, list -> new ArrayList<>()).add(runTime);
                                }
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void printResults(String message, Map<Integer, ArrayList<Integer>> runtimes) {
        for (Map.Entry<Integer, ArrayList<Integer>> entry : runtimes.entrySet()) {
            int T = entry.getValue().size();
            int[] i = new int[T];
            for (int j = 0; j < T; j++) {
                i[j] = entry.getValue().get(j);
            }
            System.out.printf("%s\nN = %d\t\tT = %d\t\tMin = %s\t\tAvg = %.2f ms\t\tMax = %s\t\tstdDev = %.2f\n",
                    message, entry.getKey(), T, StdStats.min(i), StdStats.mean(i), StdStats.max(i), StdStats.stddev(i));
        }
    }

    private static void shuffleArray(long[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            long a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}