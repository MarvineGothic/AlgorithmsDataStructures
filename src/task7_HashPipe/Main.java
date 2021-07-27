package task7_HashPipe;

public class Main {
    public static void main(String[] args) {
        HashPipeG hp = new HashPipeG();
        String[] s = "SEARCHEXAMPLE".split("");
        for (int i = 0; i < s.length; i++) {
            hp.put(s[i], i);
        }
        hp.keys("E", "R").forEach(System.out::println);

       // System.out.printf("%s\n%s\n%s\n",hp.control("H",3), hp.control("H",2), hp.control("P", 4));
        long start = System.nanoTime();
        HashPipeG H = new HashPipeG();
        Test100_000(H);
       // System.out.println((System.nanoTime() - start) / 1000_000_000);

       H.keys("0", "30").forEach(System.out::println);
        test01();
        Test02();
        TestBig();
    }

    public static void TestBig() {
        // Test
        int i = 0;
        int n = 0;
        n = 10_000;

        HashPipeG H = new HashPipeG();

        for (int j = 0; j < n; j++)
            H.put("" + j, j);
    }

    public static void Test100_000(HashPipeG H) {
        // Test
        int i = 0;
        int n = 0;
        n = 100;

        for (int j = 0; j <= n; j++) {
            H.put("" + j, j);
        }
    }


    public static void test01() {
        int i = 0;
        //        String [] in = new String[0];
        String[] in = new String[26];
        i = 0;
        for (char c = 'A'; c <= 'Z'; c++) in[i++] = "" + c;

        HashPipeG H = new HashPipeG();
        commonTest(in, H);

    }

    public static void Test02() {
        // Test
        int i = 0;
        //        String [] in = new String[0];
        String[] in = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        //        in = ex;

        HashPipeG H = new HashPipeG();

        commonTest(in, H);
    }

    private static void commonTest(String[] in, HashPipeG H) {
        for (int j = 0; j < in.length; j++) {
            H.put(in[j], j);
            System.out.print("Insert: ");
            System.out.println(in[j]);
            for (int g = 0; g < j; g++) {
                for (int h = 0; h < 32; h++) {
                    String ctrl = H.control(in[g], h);
                    if (ctrl != null) System.out.print(ctrl);
                    else System.out.print(".");
                    System.out.print(" ");
                }
                System.out.print(" : ");
                System.out.println(in[g]);
            }
        }
    }
}
