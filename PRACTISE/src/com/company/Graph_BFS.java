package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Graph_BFS {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int q = sc.nextInt();
        while (q-->0) {
            int n = sc.nextInt();
            Graph g = new Graph(n);

            int m = sc.nextInt();
            for (int i = 0; i < m; i++) {
                g.add_edge(sc.nextInt() - 1, sc.nextInt() - 1);
            }

            int s = sc.nextInt();
            for (int p : g.bfs(n, m, s - 1)) out.print(p + " ");
            out.println();
            // g.print_all();
        }
        sc.close();
        out.close();
    }

    static class Graph {
        private List<List<Integer>> adj = new ArrayList<>();
        public Graph(int n) {
            for (int i = 0; i < n; i++) adj.add( new ArrayList<>());
        }

        public void add_edge(int x, int y){
            adj.get(x).add(y);
            adj.get(y).add(x);
        }

        public void print_all() {
            for (int i = 0; i < adj.size(); i++) {
                System.out.print(i+" == ");
                for (int z: adj.get(i)) System.out.print(z+"->");
                System.out.println();
            }
        }

        public List<Integer> bfs(int n, int m, int s) {

            int [] ans = new int[n];
            Queue<Integer> q = new LinkedList<>();

            q.add(s);
            ans[s]--;

            int curr, count = 1, temp, add = 6;
            while (!q.isEmpty()) {
                temp = count;
                count = 0;
                while(temp-->0) {
                    curr = q.poll();
                    for (int c : adj.get(curr)) {
                        if (ans[c] == 0) {
                            q.add(c);
                            ans[c] = add;
                            count++;
                        }
                    }
                }
                add += 6;
            }

            List<Integer> out = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (ans[i] > 0) out.add(ans[i]);
                if (ans[i] == 0) out.add(-1);
            }
            return out;
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

