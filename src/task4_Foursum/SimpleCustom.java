package task4_Foursum;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdRandom;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class SimpleCustom {
    public static void main(String[] args) {
        List<Path> pathsList = new ArrayList<>();
        String line;
        try (Stream<Path> paths = Files.walk(Paths.get("C://Users/Admin/IdeaProjects/AD/src/task4_Foursum/data"))) {
            paths.filter(Files::isRegularFile).forEach(pathsList::add);

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Path path : pathsList) {
            System.out.println(path);
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {

                int N = Integer.parseInt(reader.readLine());
                long[] vals = new long[N];
                for (int i = 0; i < N; i += 1) vals[i] = Long.parseLong(reader.readLine());
                Arrays.sort(vals);
                // your code goes here and uses the following
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        for (int k = j + 1; k < N; k++) {
                            /*if (BinarySearch.indexOf(vals, -vals[i] - vals[j] - vals[k]) > k) {
                                System.err.println(i + " " + j + " " + k);
                                System.out.println(true);
                                // System.exit(0);
                            }*/
                        }
                    }
                }
                System.out.println(false);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
