package com.company;

import java.awt.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Djikstra {
    public static void main(String[] args) throws IOException {
        new Djikstra().TestCases();
    }
    void TestCases() throws IOException{
        int t = 1;
        while (t-->0) {
            solve();
        }
        out.flush();
    } // Put t = 1, for NO testcases // out.flush() is written in this method

    ArrayList<ArrayList<Point>> adj = new ArrayList<>();
    void solve () throws IOException {
        int n = nextInt();
        int m = nextInt();

        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        while (m-->0) {
            int a = nextInt()-1;
            int b = nextInt()-1;
            int w = nextInt();

            adj.get(a).add(new Point(b,w));
            adj.get(b).add(new Point(a,w));
        }

        PriorityQueue<Triplet> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent,-1);
        long[] dis = new long[n];
        Arrays.fill(dis,Long.MAX_VALUE);

        pq.add(new Triplet(0,0,-1));
        //dis[0] = 0;
        //vis[0] = true;
        while (!pq.isEmpty()) {
            Triplet p = pq.poll();

            if (dis[(int)p.f] > p.s) {
                dis[(int) p.f] = p.s;
                parent[(int)p.f] = (int) p.t;
            }
            //dis[(int) p.f] = Math.min(dis[(int) p.f],p.s);

            if (!vis[(int) p.f]) {
                vis[(int) p.f] = true;
                for (Point z: adj.get((int)p.f)) {
                    pq.add(new Triplet(z.x,(long)z.y+dis[(int)p.f],p.f));
                }
            }
        }

        //printLongArray(dis);
        //printIntArray(parent);

        ArrayList<Integer> ans = new ArrayList<>();
        int i = n-1;
        while (i != 0) {
            ans.add(i+1);
            i = parent[i];
            if (i == -1) {
                out.println(-1);
                return;
            }
        }
        ans.add(1);

        for (int j = 0; j < ans.size(); j++) {
            out.print(ans.get(ans.size()-j-1)+" ");
        }
        out.println();
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    void printIntArray (int[] arr) {
        for (int z : arr) out.print(z+" ");
        out.println();
    }
    void printIntList (ArrayList<Integer> arr) {
        for (int z : arr) out.print(z+" ");
        out.println();
    }
    void printLongArray (long[] arr) {
        for (long z : arr) out.print(z+" ");
        out.println();
    }
    void printLongList (ArrayList<Long> arr) {
        for (long z : arr) out.print(z+" ");
        out.println();
    }
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
    ArrayList<Integer> nextIntList (int n) throws IOException {
        ArrayList<Integer> lis = new ArrayList<>(n);
        for (int i = 0; i < n; i++) lis.add(nextInt());
        return lis;
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

    boolean is_Sorted_int (int[] arr) {
        for (int i = 0; i < arr.length-1; i++) if (arr[i] > arr[i+1]) return false;
        return true;
    }
    boolean is_Sorted_long (long[] arr) {
        for (int i = 0; i < arr.length-1; i++) if (arr[i] > arr[i+1]) return false;
        return true;
    }

    long gcd(long a, long b) { return (b==0?a:gcd(b,a%b)); }
    long lcm(long a, long b) { return (a / gcd(a, b)) * b; }
    ArrayList<Integer> Factors(int n) {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i=1; i<=Math.sqrt(n); i++) {
            if (n%i==0) {
                // If divisors are equal, print only one
                if (n/i != i) ret.add(n/i);
                ret.add(i);
            }
        }
        return ret;
    }

    boolean check_Integer (double a) {return Math.ceil(a) == Math.floor(a);}

    static class Pair implements Comparable<Pair> {
        int f;
        long s;
        public Pair (int f, long s) {
            this.f = f;
            this.s = s;
        }

        @Override
        public int compareTo(Pair o) {
            //if (this.s == o.s) return Long.compare(this.f,o.f);
            return Long.compare(this.s,o.s);
        }
    } // Comparable on basis of second then first.
    static class Triplet implements Comparable<Triplet> {
        long f;
        long s;
        long t;
        public Triplet (long f, long s, long t) {
            this.f = f;
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Triplet o) {
            if (this.s == o.s) return Long.compare(this.f,o.f);
            else return Long.compare(this.s,o.s);
        }
    } // Implement comparable accordingly.

    long mod = 1_000_000_007;
    long mod_Multiply(long a,long b) {
        return (a*b)%mod;
    }
    long mod_factorial (long n) {

        long fact =1;
        for(int i=1; i<=n; i++) {
            fact = mod_Multiply(fact,i);
        }
        return fact%mod;
    }
    long mod_power(long x, long y) {
        long temp;
        if (y == 0) return 1;
        temp = mod_power(x, y / 2);

        if ((y&1) == 0) return mod_Multiply(temp,temp);
        else {
            if (y > 0) return mod_Multiply(x,mod_Multiply(temp,temp));
            else return (mod_Multiply(temp,temp)) / x;
        }
    }

    void Print_all_subsequence (int i, int[] x) {
        if (i >= x.length) {
            printIntArray(x);
            return;
        }

        for (int j = i; j < x.length; j++) {
            XOR_Swap(i,j,x);
            Print_all_subsequence(i+1,x);
            XOR_Swap(i,j,x);
        }
    }
    void XOR_Swap (int i, int j, int[] x) {
        if (i == j) return;

        x[i] = x[i]^x[j];
        x[j] = x[i]^x[j];
        x[i] = x[i]^x[j];
    } // works only for numbers..

    boolean[] prime;
    void sieveOfEratosthenes(int n) {
        prime = new boolean[n + 1];
        Arrays.fill(prime,true);
        prime[0] = prime[1] = false;
        //for (int i = 2; i <= n; i++) prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            if (prime[p])
            {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
    } // Gives prime numbers less than equal to n in boolean[] array prime.
}
