package com.company;

import java.util.*;
import java.io.*;
import java.lang.*;

public class temp {
    static int[] a, b;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //String s = sc.nextLine();
        int k = sc.nextInt();

        System.out.println(solve(k));
    }

    static int solve(int A) {
        if (A == 1) return 1;
        if (A == 2) return 3;

        int x = (int) (Math.log(A + 2) / Math.log(2));
        int c = (int) Math.pow(2, x);
        if (((A+2)&(A+1)) == 0) {
            x = 2*x - 2;
            return (int)Math.pow(2,x)-1;
        }
        A -= c - 2;

        c /= 2;
        x--;
        System.out.println(A+" "+x+" "+c);
        StringBuilder sb = new StringBuilder();
        String temp;
        if (A > c) {
            A -= c;

            System.out.println(A+" "+x+" "+c);
            temp = Integer.toBinaryString(A-1);
            sb.append("1");
            sb.append("0".repeat(Math.max(0, x - temp.length()))).append(temp);
            System.out.println(sb);
            temp = String.valueOf(sb.reverse());
            sb.reverse();
            sb.append(temp);
            System.out.println(sb);
        } else {
            temp = Integer.toBinaryString(A-1);
            sb.append("1");
            sb.append("0".repeat(Math.max(0, x - temp.length()))).append(temp);
            temp = String.valueOf(sb.reverse());
            sb.reverse();
            sb.deleteCharAt(sb.length() - 1);
            sb.append(temp);
        }

        return Integer.parseInt(String.valueOf(sb),2);
    }
}



