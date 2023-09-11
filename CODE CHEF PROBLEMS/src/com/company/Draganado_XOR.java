package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Draganado_XOR {
    public static void main(String[] args) throws IOException {
        new Draganado_XOR().TestCases();
    }
    void TestCases() throws IOException{
        int t = nextInt();
        while (t-->0) {
            solve();
        }
        out.flush();
    } // Put t = 1, for NO testcases // out.flush() is written in this method

    void solve () throws IOException {
        int n = nextInt();
        int[] arr = nextIntArray(n);

        n = Math.min(n,64);
        List<List<Pair>> x = new ArrayList<>();
        for (int i = 0; i < 32; i++) x.add(new ArrayList<Pair>());

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int k = Integer.bitCount(arr[i]^arr[j]);

                if (x.get(k).size() != 0) {
                    for (Pair z: x.get(k)) {
                        if (i != z.f && i != z.s && j != z.f && j != z.s) {
                            out.println((z.f+1)+" "+(z.s+1)+" "+(i+1)+" "+(j+1));
                            return;
                        }
                    }
                }
                x.get(k).add(new Pair(i,j));
            }
        }

        out.println(-1);
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;
    String next () throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    int nextInt () throws IOException {
        return Integer.parseInt(next());
    }
    int[] nextIntArray (int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nextInt();
        return arr;
    }
    long nextLong () throws IOException {
        return Long.parseLong(next());
    }
    long[] nextLongArray (int n) throws IOException {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nextLong();
        return arr;
    }
    ArrayList<Long> nextLongList (int n) throws IOException {
        ArrayList<Long> lis = new ArrayList<>(n);
        for (int i = 0; i < n; i++) lis.add(nextLong());
        return lis;
    }
    double nextDouble () throws IOException {
        return Double.parseDouble(next());
    }
    char nextChar () throws IOException {
        return next().charAt(0);
    }
    String nextLine () throws IOException {
        return br.readLine().trim();
    }

    static class Pair {
        int f;
        int s;
        public Pair (int f, int s) {
            this.f = f;
            this.s = s;
        }
    }
}
