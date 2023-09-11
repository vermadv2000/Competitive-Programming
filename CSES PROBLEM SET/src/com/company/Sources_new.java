package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Sources_new {
    public static void main(String[] args) throws IOException {
        new Sources_new().TestCases();
    }
    void TestCases() throws IOException{
        int t = nextInt();
        while (t-->0) {
            //System.out.println(t);
            solve1();
        }
        out.flush();
    } // Put t = 1, for NO testcases // out.flush() is written in this method

    // Use ArrayList instead of array..
    // Array may give TLE in worst case but arraylist will never.
    // Especially in codeforces.
    void solve1 () throws IOException {

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

    boolean is_Sorted_int (int[] arr, int l, int r) {
        for (int i = l; i < r-1; i++) if (arr[i] > arr[i+1]) return false;
        return true;
    }
    boolean is_rev_Sorted_int (int[] arr, int l, int r) {
        for (int i = l; i < r-1; i++) if (arr[i] < arr[i+1]) return false;
        return true;
    }
    boolean is_Sorted_long (long[] arr) {
        for (int i = 0; i < arr.length-1; i++) if (arr[i] > arr[i+1]) return false;
        return true;
    }

    long gcd(long a, long b) { return (b==0?a:gcd(b,a%b)); }
    long lcm(long a, long b) { return (a / gcd(a, b)) * b; }

    ArrayList<Integer>[] factors;
    void precompute_factors (int n) {
        factors = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) factors[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j+=i) {
                factors[j].add(i);
            }
        }
    }
    ArrayList<Integer> Factors(int n) {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i=1; i<=Math.sqrt(n); i++) {
            if (n%i==0) {
                ret.add(i);
                // If divisors are equal, print only one
                if (n/i != i) ret.add(n/i);
            }
        }
        return ret;
    }

    boolean check_Integer (double a) {return Math.ceil(a) == Math.floor(a);}

    static class Pair implements Comparable<Pair> {
        int f;
        int s;
        public Pair (int f, int s) {
            this.f = f;
            this.s = s;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.f == o.f) return Integer.compare(this.s,o.s);
            return Integer.compare(-this.f,-o.f);
        }
    } // Comparable on basis of first then second.
    static class Triplet {
        long f;
        int s;
        int t;
        public Triplet (long f, int s, int t) {
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
        StringBuilder sb = new StringBuilder();
        sb.delete(0,n);


        return mod_Multiply(fact[n],mod_inverse(mod_Multiply(fact[r],fact[n-r])));
    } // to use nCr firstly call precompute function in testcases.

    long mod = 1_000_000_007;
    long mod_Multiply(long a,long b) {
        return (a*b)%mod;
    }
    // Mod inverse when mod is prime. such in case when we have to find modulo inverse for 10^9 + 7;
    // Then its simply equal to a^(mod-2);
    long mod_inverse (long a) {
        return mod_power(a,mod-2);
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
    } // Call for sieve before using this function.

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

    // Segment tree
    /*
        Write Build, Update, Query according to requirement.
        In Query, there are 3 conditions
        1. range lies completely. (return value)
        2. range doesn't lies at all. (return 0)
        3. range overlaps. (Recursive call in both directions)
     */
    long[] seg;
    void build (long[] arr) {
        int n = arr.length;
        seg = new long[2*n+1];
        build_util(0,0,n-1,arr);
    }
    void build_util (int idx, int l, int r, long[] arr) {
        if (l == r) {
            seg[idx] = arr[l];
            return;
        }

        int mid = (l+r)/2;
        build_util(2*idx+1,l,mid,arr);
        build_util(2*idx+2,mid+1,r,arr);
        // write statement according to requirement.
        seg[idx] = seg[2*idx+1]+seg[2*idx+2];
    }
    void update (int idx, int i, int val, int l, int r) {
        if (l == r && l == i) {
            seg[idx] = val;
            return;
        }

        int mid = (l+r)/2;
        if (i <= mid) update(2*idx+1,i,val,l,mid);
        else update(2*idx+2,i,val,mid+1,r);
        seg[idx] = seg[2*idx+1]+seg[2*idx+2];
    }
    long query (int idx, int l, int r, int a, int b) {
        // Condition 1
        if (a <= l && b >= r) return seg[idx];

        // Condition 2
        if (r < a || l > b) return 0;

        // Condition 3
        int mid = (l+r)/2;
        return query(2*idx+1,l,mid,a,b) + query(2*idx+2,mid+1,r,a,b);
    }
}
