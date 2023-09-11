package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Number_of_1_bits {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        int arr [] = sc.nextIntArray(t);
        //out.println(count_set_bits(t));
        //out.println(Different_bits(arr));
        out.println(XORing_Sub_arrays(arr));

        sc.close();
        out.close();
    }
    static int XORing_Sub_arrays (int a []){

        if ((a.length & 1) == 0) return 0;

        int ans = a[0];
        for (int i=2; i<a.length; i+=2) {
            ans ^= a[i];
        }

        return ans;
    }
    static int Different_bits (int [] a){

        int bits [] = new int[32];
        long count = 0;
        long mod = 1000000007;

        for (int i=0; i<a.length; i++){
            int j=0;
            while(a[i]>0) {
                bits[j] += a[i]&1;
                j++;
                a[i] = a[i]>>1;
            }
        }
        for(int i=0; i<32; i++){
            count = (count + ((long) (a.length - bits[i]) * bits[i])%mod)%mod;
        }
        return (int) ((2 * count) % mod);
    }
    static int count_set_bits (int a){

        if (a==0) return 0;
        if (a==1) return 1;

        int mod = 1000000007;
        int n = (int)(Math.log(a)/Math.log(2));

        int count = (((a+1-(int)Math.pow(2,n))%mod + (n*(int)Math.pow(2,n-1)%mod) )%mod);
        return (count%mod + count_set_bits((a-(int)Math.pow(2,n)))%mod) % mod;
    }
    static long divide (long a, long b){

        boolean sign = a > 0 == b > 0;
        a = Math.abs(a);
        b = Math.abs(b);

        System.out.println(a+"  "+b);

        if (b == 0) return Integer.MAX_VALUE;

        long ans = 0, temp = 0;
        for(int i=31; i>=0; --i){
            if(temp+(b <<i) <= a) {
                temp += b <<i;
                ans |= 1L<<i;
            }
        }
        return (sign)? ans: -ans;
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
            byte[] buf = new byte[128]; // line length
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
