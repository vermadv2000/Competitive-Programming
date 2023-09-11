package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
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
        int n = nextInt();
        long[] arr = nextLongArray(n);

        int index = -1;
        long gcd = arr[0], pre = arr[0];
        for (int i = 1; i < n-1; i++) {
            gcd = gcd(gcd,arr[i]);

            if (gcd == 1) {
                index = i;
                break;
            }
            pre = gcd;
        }

        if (index == -1) {
            out.println(0);
            return;
        }

        gcd = pre;
        boolean f = true;
        for (int i = n-1; i > index; i--) {
            gcd = gcd(gcd,arr[i]);
            if (gcd == 1) {
                f = false;
                break;
            }
        }

        if (f) {
            out.println(1+"\n"+(index+1)+" "+n);
            return;
        }

        gcd = arr[0];
        for (int i = 1; i < n-1; i++) {
            if (i == index-1) continue;

            gcd = gcd(gcd,arr[i]);

            if (gcd == 1) {
                out.println(-1);
                return;
            }
        }

        out.println(1+"\n1 "+(index));
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

    long gcd(long a,long b) { return (b==0?a:gcd(b,a%b)); }
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

    boolean[] prime;
    void sieveOfEratosthenes(int n) {
        prime = new boolean[n + 1];
        Arrays.fill(prime,true);
        //for (int i = 2; i <= n; i++) prime[i] = true;

        prime[0] = false;
        prime[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (prime[p])
            {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
    } // Gives prime numbers less than equal to n in boolean[] array prime.

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