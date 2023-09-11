package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class BooS {
    public static void main(String[] args) throws IOException {
        FastIO sc = new FastIO();
        //PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int x = sc.nextInt();

        int[] price = sc.nextIntArray(n);
        int[] pages = sc.nextIntArray(n);

        int[][] dp = new int[n+1][x+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                if (j >= price[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-price[i-1]] + pages[i-1]);
                }
                else dp[i][j] = dp[i-1][j];
            }
        }

//        for (int i = 0; i < n+1; i++) {
//            for (int j = 0; j < x+1; j++) {
//                sc.print(dp[i][j]+" ");
//            }
//            sc.println();
//        }
        sc.println(dp[n][x]);

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
