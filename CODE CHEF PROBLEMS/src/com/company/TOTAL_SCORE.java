package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TOTAL_SCORE {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }

        long nextLong() { return Long.parseLong(next()); }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int t = sc.nextInt();

        for(int i=0;i<t;i++){
            int pa = sc.nextInt();
            int pr = sc.nextInt();

            String points = sc.nextLine();
            String [] po = points.split(" ");
            long [] score = new long[pa];

            for(int k=0;k<pa;k++) {
                String a = sc.next();
                long x = 0;
                for (int j = 0; j < pr; j++) {
                    if(a.charAt(j)=='1') x = x + Integer.parseInt(po[j]);
                }
                score[k] = x;
            }
           for (long c : score){
               System.out.println(c);
           }
        }
    }
}
