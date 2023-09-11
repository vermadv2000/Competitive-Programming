package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Buffered_Reader {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(sc.readLine());
        while (t-->0) {
            int n = Integer.parseInt(sc.readLine());
            String[] s = sc.readLine().split(" ");
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) arr[i] = Long.parseLong(s[i]);

            Arrays.sort(arr);

            long sum1 = arr[0], sum2 = 0;
            boolean flag = false;
            for (int i = 1; i < n/2; i++) {
                sum1 += arr[i];
                sum2 += arr[n-i];

                if (sum1 < sum2) {
                    flag = true;
                    break;
                }
            }

            if ((n&1) == 1 && !flag) {
                sum1 += arr[n/2];
                sum2 += arr[n/2 + 1];
                if (sum1 < sum2) flag = true;
            }
            out.println((flag)?"YES":"NO");
        }
        sc.close();
        out.close();
    }
}
