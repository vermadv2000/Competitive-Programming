package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Poli {

    static int n;
    static int[] parent;
    static int[][] adj;
    public static void main(String[] args) throws IOException {

        FastIO sc = new FastIO();

        n = sc.nextInt();
        int m = sc.nextInt();

        adj = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            adj[a][b] = 1;
            adj[b][a] = 1;
        }

        int[][] x = new int[n+1][n+1];
        for (int i = 0; i < n+1; i++) for (int j = 0; j < n+1; j++) x[i][j] = adj[i][j];
        parent = new int[n+1];

        while (check_path(1,n)) {

            int temp = Integer.MAX_VALUE;
            for (int v = n; v != 1; v = parent[v]) {
                int u = parent[v];
                temp = Math.min(temp,adj[u][v]);
            }

            for (int v = n; v != 1; v = parent[v]) {
                int u = parent[v];
                adj[u][v] -= temp;
                adj[v][u] += temp;
            }
        }

        vis = new boolean[n+1];
        dfs(1);

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                sc.print(adj[i][j]+" ");
            }
            sc.println();
        }
        sc.println();
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                sc.print(x[i][j]+" ");
            }
            sc.println();
        }
        sc.println();
        for (int j = 0; j < n+1; j++) {
            sc.print(vis[j]+" ");
        }
        sc.println();

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j < n+1; j++) {
                if (x[i][j] > 0 && (vis[j] != vis[i])) {
                    sb.append(i+" "+j+"\n");
                    count++;
                }
            }
        }

        sc.println(count+"\n"+sb);

        sc.close();
    }

    static boolean check_path (int s, int t) {

        Arrays.fill(parent,-1);
        boolean[] vis = new boolean[n+1];

        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int i=1; i<n+1; i++) {

                if (!vis[i] && adj[x][i] > 0) {

                    if (i == t) {
                        parent[i] = x;
                        return true;
                    }

                    vis[i] = true;
                    parent[i] = x;
                    q.add(i);
                }
            }
        }

        return false;
    }

    static boolean[] vis;
    static void dfs (int i) {
        vis[i] = true;
        for (int j = 1; j < n+1; j++) {
            if (adj[i][j] > 0 && !vis[j]) dfs(j);
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
