package Exam.Exam140528;

public class Main {
    private static int counter = 0;

    public static void main(String[] args) {
        fib(5);
        System.out.println(counter);
    }

    public static int fib(int N) {
        counter++;
        if (N == 1) return 1;
        if (N == 0) return 1;
        return fib(N - 1) + fib(N - 2);
    }
}

