package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Line {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while (t-->0) {
            long x1 = sc.nextLong();
            long y1 = sc.nextLong();
            long x2 = sc.nextLong();
            long y2 = sc.nextLong();
            long x3 = sc.nextLong();
            long y3 = sc.nextLong();
            long x4 = sc.nextLong();
            long y4 = sc.nextLong();

            byte l1_p1 = (byte) Math.signum((y2-y1)*(x3-x1) - (y3-y1)*(x2-x1));
            byte l1_p2 = (byte) Math.signum((y2-y1)*(x4-x1) - (y4-y1)*(x2-x1));
            byte l2_p1 = (byte) Math.signum((y4-y3)*(x1-x3) - (y1-y3)*(x4-x3));
            byte l2_p2 = (byte) Math.signum((y4-y3)*(x2-x3) - (y2-y3)*(x4-x3));

            boolean p1, p2, p3, p4;
            p1 = p2 = p3 = p4 = true;
            if ((l1_p1^l1_p2) == 0) {
                if (l1_p1 != 0) {
                    out.println("NO");
                    continue;
                }
                else {
                    p1 = check_between_point(x1,y1,x2,y2,x3,y3);
                    p2 = check_between_point(x1,y1,x2,y2,x4,y4);
                }
            }

            if ((l2_p1^l2_p2) == 0) {
                if (l2_p1 != 0) {
                    out.println("NO");
                    continue;
                }
                else {
                    p3 = check_between_point(x3,y3,x4,y4,x1,y1);
                    p4 = check_between_point(x3,y3,x4,y4,x2,y2);
                }
            }
            if (!(p1 || p2 || p3 || p4)) {
                out.println("NO");
                continue;
            }

            out.println("YES");
        }

        sc.close();
        out.close();
    }
    static double distance (double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    static boolean check_between_point (double x1, double y1, double x2, double y2, double x3, double y3) {

        boolean ans = true;

        double d1 = distance(x1,y1,x3,y3);
        double d2 = distance(x2,y2,x3,y3);
        double d_T = distance(x1,y1,x2,y2);

        if (d1 > d_T || d2 > d_T) ans = false;

        return ans;
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
            byte[] buf = new byte[12800]; // line length
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
