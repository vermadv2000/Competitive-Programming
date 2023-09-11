package com.company;

import java.io.*;
import java.util.*;
import java.lang.*;

public class Striver_DP {
    public static void main(String[] args) throws IOException {
        new Striver_DP().Solve();
    }

    public void Solve () throws IOException {

        out.println();
        out.flush();
    }

    public static long countWaysToMakeChange(int denominations[], int value){
        Arrays.sort(denominations);

        long[] arr = new long[value+1];
        arr[0] = 1;
        for (int i = 0; i < denominations.length; i++) {
            for (int j = denominations[i]; j < value+1; j++) {
                arr[j] += arr[j-denominations[i]];
            }
        }

        return arr[value];
    }

    int maximumChocolates(int r, int c, int[][] grid) {

        int[][][] dp = new int[r][c][c];

        for (int i = 0; i < c; i++) dp[0][0][i] = grid[0][0];
        for (int i = 0; i < c; i++) dp[0][i][c-1] = grid[0][c-1];

        for (int i = r-2; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < c; k++) {
                    for (int j1 = -1; j1 <= 1; j1++) {
                        if (j + j1 < 0 || j + j1 > c) continue;
                        for (int j2 = -1; j2 <= 1; j2++) {
                            if (k + j2 < 0 || k + j2 > c) continue;

                            dp[i][j + j1][j + j2] += (j + j1 == k + j2) ? (grid[i - 1][j + j1]) : (grid[i - 1][j + j1] + grid[i - 1][k + j2]);
                        }
                    }
                }
            }
        }

        return dp[0][0][c-1];
    }

    long[] fact;
    void precompute_fact() {
        fact = new long[100005];
        fact[0] = 1;
        for (int i = 1; i < 100005; i++) fact[i] = mod_Multiply(i,fact[i-1]);
    }

    long mod = 1_000_000_007;
    long mod_Multiply(long a,long b) {
        return (a*b)%mod;
    }
    long x_gcd, y_gcd;
    long mod_inverse(long a) {
        long g = gcd_extended(a,mod);
        return (x_gcd % mod + mod) % mod;
    }
    long gcd_extended (long a, long b) {
        if (a == 0) {
            x_gcd = 0;
            y_gcd = 1;
            return b;
        }

        // To store results of recursive call
        long gcd = gcd_extended(b % a, a);
        long x1 = x_gcd, y1 = y_gcd;

        // Update x and y using results of recursive
        // call
        long tmp = b / a;
        x_gcd = y1 - tmp * x1;
        y_gcd = x1;

        return gcd;
    }

    long gcd(long a, long b) { return (b==0?a:gcd(b,a%b)); }
    long lcm(long a, long b) { return (a / gcd(a, b)) * b; }

    static class Pair implements Comparable<Pair> {
        long f;
        long s;
        public Pair (long f, long s) {
            this.f = f;
            this.s = s;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.f == o.f) return Long.compare(this.s,o.s);
            else return Long.compare(this.f,o.f);
        }
    }
    static class Triplet implements Comparable<Triplet> {
        long f;
        long s;
        long t;
        public Triplet (long f, long s, long t) {
            this.f = f;
            this.s = s;
            this.t = t;
        }

        public int compareTo (Triplet o) {
            return Long.compare(o.f,this.f);
        }
    }

    int[] prime;
    void sieve(int n) {
        prime = new int[n + 1];
        for (int i = 1; i <= n; i++) prime[i] = i; // Marking all values to itself.

        for (int i = 4; i <= n; i+=2) prime[i] = 2; // Marking multiples of 2 separately.
        for (int p = 3; p * p <= n; p+=2) {
            if (prime[p] == p)
            {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    if (prime[i] == i) prime[i] = p;
            }
        }
    } // Gives smallest prime factor of a number less than equal to n in int[] array prime, Number is prime if prime[i] == i.
    ArrayList<Integer> Factors_by_sieve(int n) {
        ArrayList<Integer> ret = new ArrayList<>();
        while (n != 1)
        {
            ret.add(prime[n]);
            n /= prime[n];
        }
        return ret;
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
    String[] nextStringArray (int n) throws IOException {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) arr[i] = nextLine();
        return arr;
    }
}
