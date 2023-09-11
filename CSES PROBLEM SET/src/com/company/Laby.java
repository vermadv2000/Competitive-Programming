package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

class Pair {

    public int getF() {
        return f;
    }

    public int getS() {
        return s;
    }

    private int f;
    private int s;

    public Pair(int i, int j) {
        this.f = i;
        this.s = j;
    }

}
class Laby {

    private static String[] s;
    private static boolean[][] vis;
    private static char[][] dir;
    private static int[] index;
    private static Stack<Character> st;

    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        s = new String[n];
        index = new int[4];
        boolean flag1 = true, flag2 = true;
        for (int i = 0; i < n; i++) {
            s[i] = sc.readLine();
            if (flag1 || flag2) {
                for (int j = 0; j < m; j++) {
                    if (s[i].charAt(j) == 'A') {
                        index[0] = i;
                        index[1] = j;
                        flag1 = false;
                    }
                    if (s[i].charAt(j) == 'B') {
                        index[2] = i;
                        index[3] = j;
                        flag2 = false;
                    }
                }
            }
        }

        dir = new char[n][m];
        vis = new boolean[n][m];
        st = new Stack<>();

        if (bfs(index[0],index[1])){
            out.println("YES\n"+st.size());
            StringBuilder sb = new StringBuilder();
            while (!st.isEmpty()) sb.append(st.pop());
            out.println(sb);
        }
        else out.println("NO");

        sc.close();
        out.close();
    }

    static boolean valid (int i, int j) {

        if (i < 0 || i >= s.length || j < 0 || j >= s[0].length()) return false;
        return s[i].charAt(j) != '#' && !vis[i][j];
    }

    static boolean bfs (int i, int j) {

        vis[i][j] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i,j));

        while (!q.isEmpty()) {

            Pair temp = q.poll();
            i = temp.getF();
            j = temp.getS();

            if (i == index[2] && j == index[3]) {

                while (i != index[0] || j != index[1]) {

                    char c;
                    st.push(c = dir[i][j]);

                    if (c == 'D') i--;
                    if (c == 'R') j--;
                    if (c == 'U') i++;
                    if (c == 'L') j++;
                }
                return true;
            }

            if (valid(i + 1, j)) {
                vis[i+1][j] = true;
                dir[i+1][j] = 'D';
                q.add(new Pair(i+1,j));
            }
            if (valid(i, j + 1)) {
                vis[i][j+1] = true;
                dir[i][j+1] = 'R';
                q.add(new Pair(i,j+1));
            }
            if (valid(i - 1, j)) {
                vis[i-1][j] = true;
                dir[i-1][j] = 'U';
                q.add(new Pair(i-1,j));
            }
            if (valid(i, j - 1)) {
                vis[i][j-1] = true;
                dir[i][j-1] = 'L';
                q.add(new Pair(i,j-1));
            }
        }
        return false;
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
