package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Casimirs_String_Solitaire {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(sc.readLine());
        while (t-->0) {
            String s = sc.readLine();
            if ((s.length()&1) == 1) {
                out.println("NO");
                continue;
            }

            int a = 0, b = 0, c = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'A') a++;
                else if (s.charAt(i) == 'B') b++;
                else c++;
            }
            out.println(((a+c) == b)?"YES":"NO");
        }
        sc.close();
        out.close();
    }
}
