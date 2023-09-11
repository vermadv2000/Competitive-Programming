package com.company;

import java.util.*;

public class rough {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }

    public int solve(ArrayList<Integer> A) {

        int n = A.size();
        int[] arr = new int[n];

        arr[n-1] = A.get(n-1);
        for (int i = n-2; i >= 0; i--) arr[i] = Math.max(arr[i+1],A.get(i));

        TreeSet<Integer> x = new TreeSet<>();

        int ans = 0;
        //x.add(A.get(0));
        for (int i=1; i<n-1; i++) {
            Integer k = x.lower(A.get(i));

            x.add(A.get(i-1));
            if (k == null) continue;
            else {
                if (arr[i+1] > A.get(i)) ans = Math.max(ans, k + A.get(i) + arr[i+1]);
            }
        }

        return ans;
    }
}
