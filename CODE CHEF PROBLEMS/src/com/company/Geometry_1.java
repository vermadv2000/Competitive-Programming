package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Geometry_1 {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while (t-->0) {
            int n = sc.nextInt();
            int q = sc.nextInt();

            double X [] = new double[n];
            double Y [] = new double[n];

            for (int i = 0; i < n; i++) {
                X[i] = sc.nextDouble();
                Y[i] = sc.nextDouble();
            }

            // Area Calculation
            double area = 0;
            int j = n - 1;
            for (int i = 0; i < n; i++)
            {
                area += (X[j] + X[i]) * (Y[j] - Y[i]);
                j = i;
            }
            area = Math.abs(area/2.0);

            // Perimeter
            double peri = 0;
            for (int i = 0; i < n-1; i++) {
                peri += distance(X[i], Y[i], X[i+1], Y[i+1]);
            }
            peri += distance(X[n-1], Y[n-1], X[0], Y[0]);

            // Sum of cot's
            double cot_sum = 0;
            double i1, j1, i2, j2, x;
            for (int k = 1; k < n-1; k++) {
                i1 = X[k+1] - X[k]; j1 = Y[k+1] - Y[k];
                i2 = X[k-1] - X[k]; j2 = Y[k-1] - Y[k];

                x = dot_product(i1, j1, i2, j2);

                cot_sum += Math.sqrt((1+x)/(1-x));
            }
            // for point first
            i1 = X[1] - X[0]; j1 = Y[1] - Y[0];
            i2 = X[n-1] - X[0]; j2 = Y[n-1] - Y[0];

            x = dot_product(i1, j1, i2, j2);

            cot_sum += Math.sqrt((1+x)/(1-x));

            // for point last
            i1 = X[0] - X[n-1]; j1 = Y[0] - Y[n-1];
            i2 = X[n-2] - X[n-1]; j2 = Y[n-2] - Y[n-1];

            x = dot_product(i1, j1, i2, j2);

            cot_sum += Math.sqrt((1+x)/(1-x));

            while(q-->0) {
                double d = sc.nextDouble()*sc.nextDouble();
                String ans = String.format("%.7f",area + peri*d + d*d*cot_sum);
                out.println(ans);
            }
        }

        sc.close();
        out.close();
    }
    static double distance (double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }

    static double dot_product (double i1, double j1, double i2, double j2) {
        return (i1*i2 + j1*j2)/(Math.sqrt(i1*i1 + j1*j1)*Math.sqrt(i2*i2 + j2*j2));
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
