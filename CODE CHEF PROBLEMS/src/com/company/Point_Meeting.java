package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Point_Meeting {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while (t-->0) {
            int n = sc.nextInt();
            Double [] x = sc.nextDoubleArray(n);
            Double [] y = sc.nextDoubleArray(n);

            int ans = Integer.MAX_VALUE;
            Double h, k;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // middle point
                    h = (x[i] + x[j])/2;
                    k = (y[i] + y[j])/2;
                    ans = Math.min(ans, steps(h,k,x,y));

                    // x & y intersection
                    h = x[i];
                    k = y[j];
                    ans = Math.min(ans, steps(h,k,x,y));

                    // diagonal (x+y=c1 , x-y=c2) intersection
                    Double c1 = x[i] + y[i];
                    Double c2 = x[j] - y[j];
                    h = (c1+c2)/2;
                    k = (c1-c2)/2;
                    ans = Math.min(ans, steps(h,k,x,y));

                    Double c;
                    // x+y=c , x intersection
                    c = x[i] + y[i];
                    h = x[j];
                    k = c - x[j];
                    ans = Math.min(ans, steps(h,k,x,y));

                    // x+y=c , y intersection
                    c = x[i] + y[i];
                    h = c - y[j];
                    k = y[j];
                    ans = Math.min(ans, steps(h,k,x,y));

                    // x-y=c , x intersection
                    c = x[i] - y[i];
                    h = x[j];
                    k = x[j] - c;
                    ans = Math.min(ans, steps(h,k,x,y));

                    // x-y=c , y intersection
                    c = x[i] - y[i];
                    h = c + y[j];
                    k = y[j];
                    ans = Math.min(ans, steps(h,k,x,y));
                }
            }
            out.println(ans);
        }

        sc.close();
        out.close();
    }
    static int steps (Double h, Double k, Double [] x, Double [] y) {
        int count = 0;

        for (int i = 0; i < x.length; i++) {
            Double p1_x = h - x[i];
            Double p1_y = k - y[i];

            if (p1_x == 0 && p1_y == 0) {}
            else if (p1_x == 0 || p1_y == 0) count++;
            else if (Math.abs(p1_x) == Math.abs(p1_y)) count++;
            else count += 2;
        }
        return count;
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
