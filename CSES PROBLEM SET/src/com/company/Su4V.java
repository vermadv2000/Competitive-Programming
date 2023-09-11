package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Su4V {
    private static HashMap<Long,LinkedList<Pair>> x;
    private static int[] ans;
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        long k = sc.nextLong();

        long[] arr = sc.nextLongArray(n);

        if (n < 4) {
            out.println("IMPOSSIBLE");
            out.close();
            return;
        }

        x = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                long z = arr[i] + arr[j];

                if (z <= k-2) {
                    LinkedList<Pair> temp;
                    if (x.containsKey(z)) temp = x.get(z);
                    else temp = new LinkedList<>();
                    temp.add(new Pair(i,j));
                    x.put(z, temp);
                }
            }
        }

        if (!find_sum(k)) out.println("IMPOSSIBLE");
        else out.println(ans[0]+" "+ans[1]+" "+ans[2]+" "+ans[3]);

        sc.close();
        out.close();
    }

    static boolean find_sum (long k){

        for (long sum: x.keySet()){
            if (x.containsKey(k - sum)) {
                for (Pair p1: x.get(sum)) {
                    for (Pair p2 : x.get(k - sum)) {
                        if (Compare(p1, p2)) {
                            ans = new int[]{p1.f+1, p1.s+1, p2.f+1, p2.s+1};
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    static boolean Compare (Pair a, Pair b) {
        return (a.f != b.f && a.f != b.s && a.s != b.f && a.s != b.s);
    }

    static class Pair{
        private int f;
        private int s;
        public Pair(int a, int b){
            f = a;
            s = b;
        }
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
