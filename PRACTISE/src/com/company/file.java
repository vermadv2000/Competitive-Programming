package com.company;

import java.io.*;

public class file {
    public static void main(String[] args) throws IOException {

        File s = new File("20116031.txt");
        if (s.createNewFile()) System.out.println("YES");
        else System.out.println("NO");

        FileWriter out = new FileWriter(s);
        out.write("asdf  "+6354+" "+65656+"\n");
        out.write("fdsa  "+64+" "+66+"\n");
        out.close();
    }
}
