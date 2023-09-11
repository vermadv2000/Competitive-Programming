package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class College_Life_4 {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while (t-->0){
            long n = sc.nextLong();
            long e = sc.nextLong();
            long h = sc.nextLong();

            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();

            // Checking for Order Possibility
            double x=0;
            byte y=0;
            if(e==h){
                y=1;
                x = e - n;
            }
            if(e>h){
                y=2;
                x = ((e + h) / 2d) - n;
            }
            if(e<h){
                y=3;
                x = (((2 * e) + h) / 3d) - n;
            }
            out.println(x);
            // Calculating Cost
            if(x<0) out.println(-1);
            if(x==0) {
                long cost = 0;
                switch (y){
                    case 1 : cost = e*c;
                    case 2 : cost = h*c + ((e-h)/2)*a;
                    case 3 : cost = e*c + ((h-e)/3)*b;
                }
                out.println(cost);
            }
            if(x>0) {
                long [] arr = {a,b,c};
                byte [] pos = {1,2,3};
                for(byte i=0; i<3; i++){
                    for(byte j = (byte) (i+1); j<3; j++) {
                        if (arr[i] > arr[j]) {
                            byte temp = pos[i];
                            pos[i] = pos[j];
                            pos[j] = temp;
                        }
                    }
                }
                //for (int p :pos) System.out.print(p+" ");
                long cost = 0;
                for(byte i=0;i<3;i++) {

                    if(pos[i] == 1){
                        long l =e/2;
                        if((l)>=n) {
                            cost += n*a;
                            break;
                        }
                        else {
                            cost += (l)*a;
                            e = e%2;
                            n-=(l);
                        }
                    }
                    if(pos[i] == 2){
                        long l =h/3;
                        if((l)>=n) {
                            cost += n*b;
                            break;
                        }
                        else {
                            cost += (l)*b;
                            h = h%3;
                            n-=(l);
                        }
                    }
                    if(pos[i] == 3) {
                        long s = Math.min(e,h);

                        if(s>=n){
                            cost += n*c;
                            break;
                        }
                        else {
                            cost += s*c;
                            e -= s;
                            h -= s;
                            n -= s;
                        }
                    }
                }
                out.println(cost);
            }
        }
        sc.close();
        out.close();
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
