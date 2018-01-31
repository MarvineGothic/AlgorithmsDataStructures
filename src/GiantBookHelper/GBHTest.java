package GiantBookHelper;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static sun.misc.MessageUtils.out;

class GBHTest {
    static java.io.PrintStream orgout;
    static RecordingOutputStream record;
    @org.junit.BeforeClass
    public static void setUpStdOut() {
        orgout = System.out;
        record = new RecordingOutputStream(orgout);
        System.setOut(new java.io.PrintStream(record));
    }
    @org.junit.AfterClass
    public static void tearDownStdOut() {
        System.setOut(orgout);
        record = null;
    }

    @org.junit.Test
    public void test01() {
        record.start();

        Random rnd;
        /// Hint: This should pass, as soon as you create the two classes
        out("");
        org.junit.Assert.assertEquals("\n", record.stop());
    }

    static class RecordingOutputStream extends java.io.OutputStream {
        private final java.io.OutputStream out;
        private byte[] buffer = new byte[1 << 10];
        private int size = -1;

        public RecordingOutputStream(java.io.OutputStream out) {
            this.out = out;
        }

        @Override
        public void write(int b) throws java.io.IOException {
            if (size >= 0) {
                if (size == buffer.length) {
                    buffer = Arrays.copyOf(buffer, size * 2);
                }
                buffer[size++] = (byte) b;
            } else {
                out.write(b);
            }
        }

        public void start() {
            size = 0;
        }

        public String stop() {
            String retval = new String(buffer, 0, size);
            size = -1;
            return retval;
        }
    }
}