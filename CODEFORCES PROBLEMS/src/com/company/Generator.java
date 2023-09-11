package com.company;

public class Generator {
    public static void main(String[] args) {
        System.out.println(819);
        int k = 2;
        for (int i = 0; i < 819; i++) {
            System.out.println(k+" "+40);
            k = (k == 40)?2:k+1;
        }
    }
}
