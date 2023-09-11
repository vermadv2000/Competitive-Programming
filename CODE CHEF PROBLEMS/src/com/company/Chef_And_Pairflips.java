package com.company;

    //PAIRFLIP

import java.util.*;
import java.lang.*;
import java.io.*;

public class Chef_And_Pairflips {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int e = sc.nextInt();

            String [] A = new String[n];
            for(int i=0; i<n; i++){
                A[i] = sc.readLine();
            }
            String [] B = new String[n];
            for(int i=0; i<n; i++){
                B[i] = sc.readLine();
            }


            int y = -1;
            boolean r1 = true, c1 = true;
            ArrayList<Integer> rc = new ArrayList<>();

            // CHECKING ROW
            int row_count = 0;
            StringBuilder sr = new StringBuilder();
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(A[i].charAt(j)!=B[i].charAt(j) && B[i].charAt(j)!='?'){
                        rc.add(j+1);
                    }
                    if(B[i].charAt(j)=='?' && y==-1){
                        y = j+1;
                    }
                }
                if(rc.size()%2!=0 && y==-1){
                    r1 = false;
                    rc.clear();
                    break;
                }
                if(rc.size()%2==0){
                    for(int k=0; k<rc.size(); k+=2){
                        sr.append("R "+(i+1)+" "+rc.get(k)+" "+rc.get(k+1)+"\n");
                    }
                    row_count += rc.size()/2;
                }
                else {
                    sr.append("R "+(i+1)+" "+y+" "+rc.get(0)+"\n");
                    for(int k=1; k<rc.size(); k+=2){
                        sr.append("R "+(i+1)+" "+rc.get(k)+" "+rc.get(k+1)+"\n");
                    }
                    row_count += (rc.size()+1)/2;
                }
                y = -1;
                rc.clear();
            }

            // CHECKING IN COLUMN
            int column_count = 0;
            StringBuilder sco = new StringBuilder();
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(A[j].charAt(i)!=B[j].charAt(i) && B[j].charAt(i)!='?'){
                        rc.add(j+1);
                    }
                    if(B[j].charAt(i)=='?' && y==-1){
                        y = j+1;
                    }
                }
                if(rc.size()%2!=0 && y==-1){
                    c1 = false;
                    break;
                }
                if(rc.size()%2==0){
                    for(int k=0; k<rc.size(); k+=2){
                        sco.append("C "+(i+1)+" "+rc.get(k)+" "+rc.get(k+1)+"\n");
                    }
                    column_count += rc.size()/2;
                }
                else {
                    sco.append("C "+(i+1)+" "+y+" "+rc.get(0)+"\n");
                    for(int k=1; k<rc.size(); k+=2){
                        sco.append("C "+(i+1)+" "+rc.get(k)+" "+rc.get(k+1)+"\n");
                    }
                    column_count += (rc.size()+1)/2;
                }
                y = -1;
                rc.clear();
            }

            // PRINTING ANSWER
            if(!r1 && !c1) {
                out.println(-1);
            }
            else if(r1 && !c1){
                out.println(row_count);
                if(e==1) out.print(sr);
            }
            else if (!r1 && c1){
                out.println(column_count);
                if(e==1) out.print(sco);
            }
            else {
                out.println(row_count+" "+column_count);
                if (e == 1) {
                    //if ((row_count <= column_count)) {
                        out.print(sr);
                    //} else {
                        out.print(sco);
                    //}
                }
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
            byte[] buf = new byte[10000000]; // line length
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
