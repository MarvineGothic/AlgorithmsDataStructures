package task4_Foursum;

import edu.princeton.cs.algs4.StdStats;

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
    private static TreeMap<String, ArrayList<Integer>> fasterRuntimes = new TreeMap<>();
    private static TreeMap<String, ArrayList<Integer>> simpleRuntimes = new TreeMap<>();
    private static TreeMap<String, ArrayList<Integer>> fastestRuntimes = new TreeMap<>();

    public static void main(String[] args) {
        simpleRuntimes.putAll(runTest(1000, new Simple(), 100));
        fasterRuntimes.putAll(runTest(1, new Faster(), 100));
        fastestRuntimes.putAll(runTest(1, new Fastest(), 100));
        printSimpleResults();
        printFasterResults();
        printFastestResults();
    }

    private static TreeMap<String, ArrayList<Integer>> runTest(int numberOfTests, FourSum fourSumClass, int testSize) {
        System.out.println("Tests: " + fourSumClass.getClass().getSimpleName());
        TreeMap<String, ArrayList<Integer>> result = new TreeMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "task4_foursum" + File.separator + "data"))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        try (Scanner s = new Scanner(new File(String.valueOf(path)))) {
                            String fileName = String.valueOf(path.getFileName());
                            System.out.println("Trying: " + fileName);
                            int N = Integer.parseInt(s.nextLine());
                            long[] vals = new long[N];
                            for (int i = 0; i < N; i++) vals[i] = Long.parseLong(s.nextLine());
                            int count = 0;

                            int newNumberOfTests = numberOfTests;

                            if (!(fourSumClass instanceof Simple && N > 1900) && N == testSize)
                                for (int i = 0; i < newNumberOfTests; i++) {
                                    shuffleArray(vals);

                                    Long startTime = System.currentTimeMillis();
                                    fourSumClass.evaluateFoursum(N, vals);
                                    Long stopTime = System.currentTimeMillis();
                                    int runTime = (int) (stopTime - startTime);
                                    if (runTime > 60_000) return;
                                    if (result.containsKey(fileName)) {
                                        result.get(fileName).add(runTime);
                                    } else {
                                        result.put(fileName, new ArrayList<>());
                                    }
                                    count++;
                                }
                            System.out.println("Tests number: " + count);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void printSimpleResults() {
        System.out.println("Simple method results:");
        printResults(simpleRuntimes);
    }

    private static void printFasterResults() {
        System.out.println("Faster method results:");
        printResults(fasterRuntimes);
    }

    private static void printFastestResults() {
        System.out.println("Fastest method results:");
        printResults(fastestRuntimes);
    }

    private static void printResults(TreeMap<String, ArrayList<Integer>> runtimes) {
        for (Map.Entry<String, ArrayList<Integer>> entry : runtimes.entrySet()) {
            int[] i = new int[entry.getValue().size()];
            for (int j = 0; j < entry.getValue().size(); j++) {
                i[j] = entry.getValue().get(j);
            }
            System.out.println("N = " + entry.getKey() + "\t\tAvg = " + StdStats.mean(i) + " ms\t\tstdDev = " + StdStats.stddev(i) + "\n");
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