package com.company;

import java.util.*;
import java.lang.*;

public class nptel {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] dp = new int[n][2];
        dp[0][0] = dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

//        int[] dp = new int[n+1];
//        Arrays.fill(dp,Integer.MAX_VALUE);
//        for (int i = 0; i <= Math.min(n,9); i++) dp[i] = 1;
//
//        for (int i = 10; i < n+1; i++) {
//            int a = i;
//            boolean[] num = new boolean[10];
//            while (a > 0) {
//                num[a%10] = true;
//                a /= 10;
//            }
//
//            //System.out.println(x+" "+ret);
//            for (int j = 1; j < 10; j++) {
//                if (num[j]) {
//                    dp[i] = Math.min(dp[i],1+dp[i-j]);
//                }
//            }
//        }

        System.out.println(dp[n-1][0]);
        //System.out.println(solve(n));
    }

    static int solve (int x) {
        if (x < 10) return 1;

        //if (dp[x] != -1) return dp[x];
        int ret = Integer.MAX_VALUE, a = x, num = 0;
        while (a > 0) {
            num = Math.max(num,a%10);
            a /= 10;
        }

        //System.out.println(x+" "+ret);
        return Math.min(ret,1+solve(x-num));
    }
}


