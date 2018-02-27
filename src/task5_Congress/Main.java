package task5_Congress;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        String path = "C://Users/Admin/IdeaProjects/AD/src/task5_Congress/data/small-5-in.txt";
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            int states = Integer.parseInt(bf.readLine());
            int seats = Integer.parseInt(bf.readLine());
            String bfLine;

            PriorityQueue<State> stateQueue = new PriorityQueue<>(states, new State(null, 0));
            while ((bfLine = bf.readLine()) != null)
                stateQueue.add(new State(bfLine, Integer.parseInt(bf.readLine())));

            Congress.congressInProgress(stateQueue, seats);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
