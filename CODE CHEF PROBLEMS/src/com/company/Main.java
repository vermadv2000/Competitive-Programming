package com.company;

import java.math.BigInteger;
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        new Main().TestCases();
    }
    void TestCases() throws IOException{
        int t = 1;
        while (t-->0) {
            solve();
        }
        out.flush();
    } // Put t = 1, for NO testcases // out.flush() is written in this method

    void solve () throws IOException {
        //printIntArray(LPS_array("1001001100"));
        out.println(1e9%mod);
    }

    public int solve(ArrayList<String> A) {
        int m = A.size();

        long[] arr = new long[m];
        for (int i = 0; i < m; i++) {
            int n = A.get(i).length();
            int[] z = LPS_array(A.get(i));
            if (check_Integer((double) n/(n-z[n-1]))) arr[i] = n-z[n-1];
            else arr[i] = n;
        }

        for (int i = 0; i < m; i++) {
            for (long j = 1; j <= 2*arr[i]-1; j++) {
                long num = j*(j+1)/2;
                if (check_Integer((double) num/arr[i])){
                    arr[i] = j;
                    break;
                }
            }

        }

        BigInteger ans = BigInteger.valueOf(arr[0]);
        for (int i = 1; i < m; i++) {
            ans = lcm(ans,BigInteger.valueOf(arr[i]));
        }

        ans = ans.remainder(BigInteger.valueOf(mod));
        return ans.intValue();
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
    BigInteger lcm(BigInteger a, BigInteger b) { return (a.divide(a.gcd(b))).multiply(b);}
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
    } //Gives all (NOT ONLY PRIME) factors of a number n.

    boolean check_Integer (double a) {return Math.ceil(a) == Math.floor(a);}

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
    } // Comparable on basis of first then second.
    static class Triplet {
        long f;
        long s;
        long t;
        public Triplet (long f, long s, long t) {
            this.f = f;
            this.s = s;
            this.t = t;
        }
    } // Implement comparable accordingly.

    long[] fact;
    void precompute_fact() {
        fact = new long[100005];
        fact[0] = 1;
        for (int i = 1; i < 100005; i++) fact[i] = mod_Multiply(i,fact[i-1]);
    }
    long nCr (int n, int r) {
        if (n < r) return -1;
        r = Math.min(r,n-r);

        if (r == 0) return 1;
        if (r == 1) return n;

        return mod_Multiply(fact[n],mod_inverse(mod_Multiply(fact[r],fact[n-r])));
    } // to use nCr firstly call precompute function in testcases.

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
    long mod_factorial (long n) {

        long fact =1;
        for(int i=1; i<=n; i++) {
            fact = mod_Multiply(fact,i);
        }
        return fact%mod;
    }
    long mod_power(long x, long y) {
        long temp;
        if (y == 0)
            return 1;
        temp = mod_power(x, y / 2);

        if (y % 2 == 0) return mod_Multiply(temp,temp);
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
    HashSet<Integer> Factors_by_sieve(int n) {
        HashSet<Integer> ret = new HashSet<>();
        while (n != 1)
        {
            ret.add(prime[n]);
            n /= prime[n];
        }
        return ret;
    } // Call for sieve before using this function. // Change HashSet to Arraylist to get all occurrences of a prime factor.

    int[] LPS_array (String s) {
        int[] arr = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(j)) {
                arr[i] = j + 1;
                j++;
            }
            else {
                boolean f = true;
                while (j > 0) {
                    j = arr[j-1];
                    if (s.charAt(i) == s.charAt(j)) {
                        arr[i] = j + 1;
                        j++;
                        f = false;
                        break;
                    }
                }

                if (f) arr[i] = 0;
            }
        }

        return arr;
    } // Longest Prefix Suffix array for pattern searching.

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    void printIntList (ArrayList<Integer> arr) {
        for (int z : arr) out.print(z+" ");
        //out.println();
    }
    void printIntArray (int[] arr) {
        for (int z : arr) out.print(z+" ");
        out.println();
    }
    void printLongArray (long[] arr) {
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
}
