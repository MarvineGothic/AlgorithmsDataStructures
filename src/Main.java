import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        int N=10;
        for (int i = 1; i < N; i *= 2)
            for (int j = 0; j < N; j++)
                System.out.println("n: " + i + " sum: " + sum++);
    }

    public static void sumDiv(int x) {
        int sum = -1;
        int N = x;
        for (int n = N; n > 0; n /= 2)
            //for (int i = 0; i < n; i++)
            System.out.println("n: " + n + " sum: " + (sum += n));
    }
}
