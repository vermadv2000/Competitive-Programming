package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class RemD {
    public static void main(String[] args) throws IOException {
        FastIO sc = new FastIO();
        //PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        if (n < 10) {
            sc.println(1);
            sc.close();
            return;
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i < 10; i++) dp[i] = 1;

        for (int i = 10; i <= n; i++) {
            int ans = 10000000;
            for (int j = i; j > 0; j /= 10) {
                int k = j%10;
                if (k != 0 && k <= j) ans = Math.min(ans,dp[i-k]+1);
            }

            dp[i] = ans;
        }

//        for (int z: dp) sc.print(z+" ");
//        sc.println();
        sc.println(dp[n]);

        sc.close();
        //out.close();
    }

    static class FastIO extends PrintWriter {
        private InputStream stream;
        private byte[] buf = new byte[1<<16];
        private int curChar, numChars;

        // standard input
        public FastIO() { this(System.in,System.out); }
        public FastIO(InputStream i, OutputStream o) {
            super(o);
            stream = i;
        }
        // file input
        public FastIO(String i, String o) throws IOException {
            super(new FileWriter(o));
            stream = new FileInputStream(i);
        }

        // throws InputMismatchException() if previously detected end of file
        private int nextByte() {
            if (numChars == -1) throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars == -1) return -1; // end of file
            }
            return buf[curChar++];
        }

        // to read in entire lines, replace c <= ' '
        // with a function that checks whether c is a line break
        public String next() {
            int c; do { c = nextByte(); } while (c <= ' ');
            StringBuilder res = new StringBuilder();
            do { res.appendCodePoint(c); c = nextByte(); } while (c > ' ');
            return res.toString();
        }
        public int nextInt() { // nextLong() would be implemented similarly
            int c; do { c = nextByte(); } while (c <= ' ');
            int sgn = 1; if (c == '-') { sgn = -1; c = nextByte(); }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res = 10*res+c-'0';
                c = nextByte();
            } while (c > ' ');
            return res * sgn;
        }


        public double nextDouble() { return Double.parseDouble(next()); }

        public int[] nextIntArray(int size) throws IOException {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }
    }
}
