package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Winter {
    private static boolean[] check;
    private static final ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        new Codechef().TestCases();
    }
    void TestCases() throws IOException{
        int t = 1;
        while (t-->0) {
            solve();
        }
        out.flush();
    } // Put t = 1, for NO testcases // out.flush() is written in this method

    void solve () throws IOException {
        int n = nextInt();
        int m = nextInt();
        int q = nextInt();

        check = new boolean[n];
        Graph g = new Graph(n);
        while (m-->0) {
            int x = nextInt();
            int y = nextInt();

            g.add_edge(x-1,y-1);
        }

        while (q-->0) {
            int t = nextInt();
            int z = nextInt();

            if (t == 3) out.println((check[z-1])?"YES":"NO");
            else if (t == 1) {
                check[z-1] = true;
                arr.add(z-1);
            }
            else g.frozen(z);
        }
    }

    static class Graph{
        private static final List<List<Integer>> adj = new ArrayList<>();
        public Graph (int n){
            while (n-->0) adj.add(new ArrayList<>());
        }

        public void add_edge(int a, int b) {
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        public void frozen (int k){
            if (arr.isEmpty()) return;

            ArrayList<Integer> temp = new ArrayList<>();
            while (k-->0){
                if (arr.isEmpty()) return;
                for (Integer y : arr) {
                    for (int z : adj.get(y)) {
                        if (!check[z]) {
                            check[z] = true;
                            temp.add(z);
                        }
                    }

                    adj.get(y).clear();
                }

                arr.clear();
                arr.addAll(temp);
                temp.clear();
            }
        }
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
}
