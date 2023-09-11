package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Sho1 {
    private static boolean[] vis;
    private static long[] dis;
    private static long[] path;
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph g = new Graph(n);
        for (int i = 0; i < m; i++) g.add_edge(sc.nextInt()-1, sc.nextInt()-1, sc.nextLong());

        vis = new boolean[n];
        dis = new long[n];
        Arrays.fill(dis,Long.MAX_VALUE);

        g.Dijkstra(0);

        for (long p: dis) out.print(p+" ");
        out.println();

        sc.close();
        out.close();
    }

    static class Graph{
        private static List<List<Pair>> adj = new ArrayList<>();
        public Graph (int n){
            while (n-->0) adj.add(new ArrayList<>());
        }

        public void add_edge(int a, int b, long c) {
            adj.get(a).add(new Pair(b,c));
            //adj.get(b).add(new Pair(a,c));
        }

        public void Dijkstra (int k){
            PriorityQueue<Pair> pq = new PriorityQueue<>(new Compare());
            pq.add(new Pair(k,0));

            while (!pq.isEmpty()){
                Pair temp = pq.poll();
                vis[temp.f] = true;
                dis[temp.f] = Math.min(temp.s,dis[temp.f]);

                for (Pair z: adj.get(temp.f)){
                    if (!vis[z.f]){
                        pq.add(new Pair(z.f,dis[temp.f]+z.s));
                    }
                }
            }
        }
    }

    static class Compare implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return Long.compare(o1.s, o2.s);
        }
    }

    static class Pair{
        private int f;
        private long s;
        public Pair(int a, long b){
            f = a;
            s = b;
        }

        public int getF() {
            return f;
        }
        public long getS(){
            return s;
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
