package com.company;

import java.util.*;
import java.io.*;

class Room {
    public static void main(String[] args) throws IOException {
        FastIO sc = new FastIO();

        int n = sc.nextInt();
        Triplet[] p = new Triplet[n];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int d = sc.nextInt();

            p[i] = new Triplet(a,d,i);
        }

        Arrays.sort(p,Comparator.comparingInt(c -> c.a));

        int[] ans = new int[n];
        int num = 2;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.f));
        pq.add(new Pair(p[0].b,1));
        ans[p[0].c] = 1;
        for (int i = 1; i < n; i++) {
            Triplet temp = p[i];

            if (pq.peek().f < temp.a) {
                pq.add(new Pair(temp.b, pq.peek().s));
                ans[temp.c] = pq.poll().s;
            }
            else {
                pq.add(new Pair(temp.b, num));
                ans[temp.c] = num;
                num++;
            }

            //for (Pair z: pq) out.print(z.f+"->"+z.s+" ");
            //out.println();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(--num).append("\n");
        for (int z: ans) sb.append(z).append(" ");
        sc.println(sb);

        sc.close();
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
    }

    static class Pair{
        private int f;
        private int s;
        public Pair(int a, int b){
            f = a;
            s = b;
        }
    }
    static class Triplet{
        private int a;
        private int b;
        private int c;

        public Triplet(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
