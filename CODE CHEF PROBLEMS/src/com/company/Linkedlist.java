package com.company;

import java.util.Scanner;

public class Linkedlist {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Linkedlist l = new Linkedlist();
        int t = sc.nextInt();
        while(t-->0){
            int x = sc.nextInt();
            l.add_node(x);
        }

        //PRINTING LIST
        node p = l.head;
        while (p!=null){
            System.out.print(p.key+" ");
            p = p.next;
        }
        System.out.println();

    }
    class node {
        int key;
        node next;

        node (int k){
            key = k;
            next = null;
        }
    }

    public node head = null;
    public node y = null;
    public void add_node(int data){

        node add = new node(data);
        if(head == null){
            head = add;
        }
        else {
            y.next = add;
        }
        y = add;
    }
}
