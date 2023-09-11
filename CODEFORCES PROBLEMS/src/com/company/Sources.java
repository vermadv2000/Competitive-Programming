package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Sources {
    public static void main(String[] args) throws IOException {
        new Sources().TestCases();
    }
    void TestCases() throws IOException{
        int t = nextInt();
        while (t-->0) {
            solve();
        }
        out.flush();
    } // Put t = 1, for NO testcases // out.flush() is written in this method

    void solve () throws IOException {
        int l = nextInt();
        int r = nextInt();

        if (l == r) {
            ArrayList<Integer> x = Factors(l);
            if (x.size() == 2) out.println(-1);
            else {
                int z = x.get(2);
                int y = l/z;
                out.println(z+" "+(z*(y-1)));
            }
        }
        else {
            if ((r&1) == 1) r--;

            int x = r/2;
            if (x == 1) out.println(-1);
            else out.println(2+" "+(2*(x-1)));
        }
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
    HashSet<Integer> primeFactors(int n) {
        HashSet<Integer> ans = new HashSet<>();
        while (n%2==0) {
            ans.add(2);
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i+= 2) {
            while (n%i == 0) {
                ans.add(i);
                n /= i;
            }
        }

        if (n > 2) ans.add(n);

        return ans;
    }

    boolean check_Integer (double a) {return Math.ceil(a) == Math.floor(a);}
    long find_root (long x) {
        if (x < 0) return -1;

        long t = (long) Math.sqrt(x);
        for (long i = Math.max(0,t-10); i < t+10; i++) {
            if (i*i == x) return i;
        }

        return -1;
    } // Gives root if possible OR -1 if root is unreal or NOT Integer value.

    static class Pair implements Comparable<Pair> {
        long f;
        long s;
        public Pair (long f, long s) {
            this.f = f;
            this.s = s;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.s,o.s);
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
    ArrayList<Integer> Factors_by_sieve(int n) {
        ArrayList<Integer> ret = new ArrayList<>();
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
    } // Longest Prefix Suffix array for pattern searching

    // DSU with 1-based indexing
    int[] parent; long[] size;
    void build_DSU (int n) {
        parent = new int[n+1];
        size = new long[n+1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    int find_parent (int node) {
        if (node == parent[node]) return node;
        return find_parent(parent[node]);
    }
    boolean union_by_size (int u, int v) {
        u = find_parent(u);
        v = find_parent(v);

        if (u == v) return false;

        if (size[u] > size[v]) {
            parent[v] = u;
            size[u] += size[v];
        }
        else {
            parent[u] = v;
            size[v] += size[u];
        }

        return true;
    }


    // Segment tree with 0-based indexing
    /*
        Write Build, Update, Query according to requirement.
        In Query, there are 3 conditions
        1. range lies completely. (return value)
        2. range doesn't lies at all. (return 0)
        3. range overlaps. (Recursive call in both directions)
     */
    long[] seg;
    void build_seg_tree (long[] arr) {
        int n = arr.length;
        seg = new long[4*n+1];
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

    static class FastReader {
        final private int BUFFER_SIZE = 1 << 17;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException
        {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[1280000]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    }
                    else {
                        continue;
                    }
                }
                buf[cnt++] = (byte)c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        public int[] nextIntArray(int size) throws IOException {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] nextLongArray(int size) throws IOException {
            long[] arr = new long[size];
            for (int i = 0; i < size; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        public Double[] nextDoubleArray(int size) throws IOException {
            Double[] arr = new Double[size];
            for (int i = 0; i < size; i++) {
                arr[i] = nextDouble();
            }
            return arr;
        }

        public int[][] nextIntMatrix(int n, int m) throws IOException {
            int[][] arr = new int[n][m];
            for (int i = 0; i < n; i++) {
                arr[i] = nextIntArray(m);
            }
            return arr;
        }

        public long[][] nextLongMatrix(int n, int m) throws IOException {
            long[][] arr = new long[n][m];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLongArray(m);
            }
            return arr;
        }

        public ArrayList<Integer> nextIntList(int size) throws IOException {
            ArrayList<Integer> arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(nextInt());
            }
            return arrayList;
        }

        public ArrayList<Long> nextLongList(int size) throws IOException {
            ArrayList<Long> arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(nextLong());
            }
            return arrayList;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}