package Exam.Exam120531;

public class Question1 {
    public static void main(String[] args) {
        int N = 6;
        System.out.println(f(N));
        System.out.println((N - 1) + (N - 2));
        System.out.println(N-1+2);
        System.out.println(N-1+N-2);
        System.out.println(N-1+3);
    }

    public static int f(int N) {
        if (N > 1) return f(N - 1) + N - 2;
        else return 3;
    }

    public static void q1a() {
        int N = 50;
        double f = (N * N + 1) * (N * N + N);
        double g = Math.pow(N, 4);
        System.out.printf("f=%s g=%s\n", f, g);
        f = 8 * N * N;
        g = 3 * N * N + 8 * N;
        System.out.printf("f=%s g=%s\n", f, g);
        f = (N * (Math.log(N) / Math.log(2)) * (N + 7));
        g = N * N;
        System.out.printf("f=%s g=%s\n", f, g);
        f = Math.pow(2, N);
        g = Math.pow(2, N + 1);
        System.out.printf("f=%s g=%s\n", f, g);
    }

    public static void q1b() {
        int N = 10;
        int[] A = new int[N];
        int count = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                A[i] = j;
                count++;
            }
        }
        System.out.println(count);
    }
}
