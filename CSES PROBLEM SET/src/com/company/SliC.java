package com.company;

// TLE due to JAVA in CSES, but accepted in leetcode.

import java.util.*;
import java.lang.*;
import java.io.*;

class SliC {
    private static TreeSet<Pair> lower;
    private static TreeSet<Pair> upper;
    private static int k;
    private static long cost;
    public static void main(String[] args) throws IOException {
        FastIO sc = new FastIO();
        //PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        k = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        StringBuilder sb = new StringBuilder();
        if (k == 1) {
            for (int z: arr) sb.append(0).append(" ");
            sc.println(sb);
            sc.close();
            return;
        }

        cost = 0;
        lower = new TreeSet<>(new CompareBy_F_S());
        upper = new TreeSet<>(new CompareBy_F_S());

        lower.add(new Pair(arr[0],0));
        for (int i = 1; i < k; i++) add(new Pair(arr[i],i));

        if ((k&1) == 1) {
            sb.append(lower.last().f).append(" ");
            for (int r = k; r < n; r++) {
                remove(new Pair(arr[r - k], r - k));
                add(new Pair(arr[r],r));
                sb.append(lower.last().f).append(" ");
            }
        }
        else {
            sb.append(Math.min(lower.last().f, upper.first().f)).append(" ");
            for (int r = k; r < n; r++) {
                remove(new Pair(arr[r - k], r - k));
                add(new Pair(arr[r],r));
                sb.append(Math.min(lower.last().f, upper.first().f)).append(" ");
            }
        }

        sc.println(sb);

        sc.close();
        //out.close();
    }

    static void remove (Pair out){

        if (out.f > lower.last().f) upper.remove(out);
        else lower.remove(out);

        if (lower.isEmpty()) {
            lower.add(upper.first());
            upper.remove(upper.first());
        }
    }

    static void add (Pair in) {

        //int ls = lower.size(), us = upper.size();
        int temp = lower.last().f;

        if (in.f > temp) {
            upper.add(in);
            if (k/2 < upper.size()){
                lower.add(upper.first());
                upper.remove(upper.first());
            }
        }
        else {
            lower.add(in);
            if ((k+1)/2 < lower.size()) {
                upper.add(lower.last());
                lower.remove(lower.last());
            }
        }
    }

    static class Pair{
        private int f;
        private int s;
        public Pair(int a, int b){
            f = a;
            s = b;
        }
    }
    static class CompareBy_F_S implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            if (o1.f == o2.f) return Long.compare(o1.s,o2.s);
            else return Long.compare(o1.f,o2.f);
        }
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