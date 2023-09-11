package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class DRMQ {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int q = sc.nextInt();

        int arr[] = sc.nextIntArray(n);

        Segment_Tree St = new Segment_Tree(n);
        St.build(arr);

        while (q-->0){
            int k = sc.nextInt();
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;

            if (k == 1) St.update(a,b+1);
            else out.println(St.query(a,b));
        }

        sc.close();
        out.close();
    }

    // Build, Query and Update will vary according to requirements.
    static class Segment_Tree {
        int st[];
        int n;
        public Segment_Tree (int n) {
            this.n = n;
            int size = (int)(Math.pow(2,Math.ceil(Math.log(n)/Math.log(2))));
            st = new int[2*size - 1];
        }

        public void build (int[] arr) {
            st[0] = build_util(arr,0,arr.length-1,0);
        }
        public int build_util(int[] arr, int l, int r, int curr) {

            if (l == r) return st[curr] = arr[l];

            int m = mid(l,r);
            return st[curr] = Math.min(build_util(arr, l, m, 2*curr + 1),build_util(arr, m+1, r, 2*curr+2));
        }

        int x;
        int y;
        public int query (int x, int y){
            this.x = x;
            this.y = y;

            return query_util(0,n-1,0);
        }
        public int query_util (int l, int r, int curr) {

            if (l >= x && r <= y) return st[curr];
            else if (l > y || r < x) return Integer.MAX_VALUE;
            else {
                int m = mid(l,r);
                return Math.min(query_util(l,m,2*curr+1),query_util(m+1,r,2*curr+2));
            }
        }

        int target;
        int value;
        public void update(int index, int val) {
            this.target = index;
            this.value = val;

            update_reach_index(0,n-1,0);
            update_tree_values(target);
        }
        public void update_reach_index(int l, int r, int curr){

            if (l == r) {
                st[curr] = value;
                target = curr;
                return;
            }

            int m = mid(l,r);
            if (m >= target) update_reach_index(l,m,2*curr + 1);
            else update_reach_index(m+1,r,2*curr + 2);
        }
        public void update_tree_values(int node){
            while (node > 0) {
                node = (node-1)/2;
                st[node] = Math.min(st[2*node + 1],st[2*node + 2]);
            }
        }

        int mid (int a, int b) {
            return a + (b-a)/2;
        }

        void print(){
            for (int z: st) System.out.print(z+" ");
            System.out.println();
        }
    }

    static class FastReader {
        final private int BUFFER_SIZE = 1 << 17;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[12800]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
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

        public long nextLong() throws IOException {
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

        public double nextDouble() throws IOException {
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

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
