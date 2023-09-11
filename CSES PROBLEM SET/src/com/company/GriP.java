package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class GriP {
    private static int n;
    private static String[] s;
    private static long[][] dp;
    public static void main(String[] args) throws IOException {
        FastIO sc = new FastIO();
        //PrintWriter out = new PrintWriter(System.out);

        n = sc.nextInt();
        s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }

        if (s[0].charAt(0) == '*' || s[n-1].charAt(n-1) == '*') {
            sc.println(0);
            sc.close();
            return;
        }

        dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        sc.println(solve(0,0));

        sc.close();
        //out.close();
    }

    static long solve (int x, int y) {

        if (x == n-1 && y == n-1) return 1L;
        if (dp[x][y] != -1) return dp[x][y];

        long mod = 1000000007L;
        long ret = 0;
        if (y+1 < n && s[x].charAt(y+1) != '*') ret += solve(x,y+1);
        if (x+1 < n && s[x+1].charAt(y) != '*') ret += solve(x+1,y);

        if (ret > mod) ret -= mod;
        return dp[x][y] = ret;
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
