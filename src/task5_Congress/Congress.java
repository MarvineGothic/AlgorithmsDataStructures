package task5_Congress;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Congress {
    public static void main(String[] args) {
        int states = StdIn.readInt();
        int seats = StdIn.readInt();

        PriorityQueue<State> stateQueue = new PriorityQueue<>(states, new State(null, 0));
        while (!StdIn.isEmpty()) {
            StdIn.readLine();
            stateQueue.add(new State(StdIn.readLine(), StdIn.readInt()));
        }
        congressInProgress(stateQueue, seats);
    }

    public static void congressInProgress(PriorityQueue<State> queue, int seats) {
        seats -= queue.size();
        while (seats > 0) {
            State state = queue.poll();
            state.addOneSeat();
            state.quotient = Congress.quotientHuntingtonHill(state.population, state.seatsAllocated);
            queue.add(state);
            seats--;
        }
        for (State state : queue)
            StdOut.println(state.stateName + " " + state.seatsAllocated);
    }

    public static double quotientHuntingtonHill(int population, int seats) {
        return population / (Math.sqrt(1.0 * seats * (seats + 1)));
    }
}

class State implements Comparator<State> {
    String stateName;
    int population;
    int seatsAllocated = 1;
    double quotient = 0.0;

    State(String stateName, int population) {
        this.stateName = stateName;
        this.population = population;
        this.quotient = Congress.quotientHuntingtonHill(population, seatsAllocated);
    }

    public void addOneSeat() {
        seatsAllocated++;
    }

    @Override
    public int compare(State o1, State o2) {
        return Double.compare(o2.quotient, o1.quotient);
    }
}
