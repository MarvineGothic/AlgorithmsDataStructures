package task4_Foursum;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class foursumBenchmark {
    private static TreeMap<Integer, ArrayList<Integer>> fasterRuntimes = new TreeMap<>();
    private static TreeMap<Integer, ArrayList<Integer>> simpleRuntimes = new TreeMap<>();
    private static TreeMap<Integer, ArrayList<Integer>> fastestRuntimes = new TreeMap<>();

    public static void main(String[] args) {
        runTest();
        printSimpleResults();
        printFasterResults();
        printFastestResults();
    }

    private static void runTest() {
        try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "task4_foursum" + File.separator + "data"))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        try (Scanner s = new Scanner(new File(String.valueOf(path)))) {
                            System.out.println("Trying: " + path);
                            int N = Integer.parseInt(s.nextLine());
                            long[] vals = new long[N];
                            for (int i = 0; i < N; i++) vals[i] = Long.parseLong(s.nextLine());

                            StdRandom.shuffle(vals);

                            if (N < 1900) {
                                Long startSimple = System.currentTimeMillis();
                                System.out.println("Simple");
                                new Simple().evaluateFoursum(N, vals);
                                Long stopSimple = System.currentTimeMillis();
                                int runtimeSimple = (int) (stopSimple - startSimple);
                                if (simpleRuntimes.containsKey(N)) {
                                    simpleRuntimes.get(N).add(runtimeSimple);
                                } else {
                                    simpleRuntimes.put(N, new ArrayList<>());
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });


        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private static void printResults(TreeMap<Integer, ArrayList<Integer>> runtimes) {
        for (Map.Entry<Integer, ArrayList<Integer>> entry : runtimes.entrySet()) {
            int[] i = new int[5];
            for (int j = 0; j < entry.getValue().size(); j++) {
                i[j] = entry.getValue().get(j);
            }
            System.out.println("N = " + entry.getKey() + "\t\tAvg = " + StdStats.mean(i) + " ms\t\tstdDev = " + StdStats.stddev(i) + "\n");
        }
    }

    private TreeMap<Integer, ArrayList<Integer>> recordTestData(int N, long vals[], FourSum fourSumClass) {
        TreeMap<Integer, ArrayList<Integer>> result = new TreeMap<>();
        Long startFaster = System.currentTimeMillis();
        fourSumClass.evaluateFoursum(N, vals);
        Long stopFaster = System.currentTimeMillis();
        int runtimeFaster = (int) (stopFaster - startFaster);
        if (result.containsKey(N)) {
            result.get(N).add(runtimeFaster);
        } else {
            result.put(N, new ArrayList<>());
        }
        return result;
    }
}


/*private <T extends FourSum> TreeMap<Integer, ArrayList<Integer>> recordTestData(int N, long vals[], T fourSumClass){
        TreeMap<Integer, ArrayList<Integer>> result = new TreeMap<>();
        Long startFaster = System.currentTimeMillis();
        fourSumClass.evaluateFoursum(N, vals);
        Long stopFaster = System.currentTimeMillis();
        int runtimeFaster = (int) (stopFaster - startFaster);
        if (result.containsKey(N)) {
            result.get(N).add(runtimeFaster);
        } else {
            result.put(N, new ArrayList<>());
        }
        return result;
    }*/