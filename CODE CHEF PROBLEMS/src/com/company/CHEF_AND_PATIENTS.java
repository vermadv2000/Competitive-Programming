package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CHEF_AND_PATIENTS {
    public static void main(String[] args) {
        FastReader sc = new FastReader();

        int t = sc.nextInt();

        while(t-->0){
           int p = sc.nextInt();
           String s = sc.nextLine();
           String[] f =s.split(" ");
           int [] r = new int[p];
           String[] g =  f.clone();
           Arrays.sort(g);

           for(int j=1;j<p;j++){
               if(g[p - j].equals(g[p - j - 1])){
                   int c = 0;
                   for (int k=0;k<p;k++){
                       if(f[k].equals(g[p - j])){
                           r[k]=j+c;
                           c++;
                       }
                   }
                   j+=c;
               }
               else {
                   int x = IndexSearch(f,g[p-j]);
                   r[x]=j;
               }
           }
           int x = IndexSearch(f,g[0]);
           if(r[x]==0) r[x] = p;

           StringBuffer sb = new StringBuffer();
           for(int z :r){
               sb.append(z+" ");
           }
            System.out.println(sb);
        }
    }
    static int IndexSearch (String[] k ,String l){
        int index = -1;
        for(int i=0;i<k.length;i++){
            if (k[i].equals(l)){
                index=i;
                break;
            }
        }
        return index;
    }
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
}
