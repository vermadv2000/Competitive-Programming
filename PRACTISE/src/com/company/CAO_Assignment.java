package com.company;

import java.io.*;
import java.util.*;

public class CAO_Assignment {
    public static void main(String[] args) throws IOException {

        // TAKING INPUT
        // PageTableFile
        Scanner sc = new Scanner(new File(args[0]));
        int t = 3;
        while (t-->0) {
            String s = sc.nextLine();
        }
        HashMap<Integer,Integer> VPN_PFN = new HashMap<>();
        while (sc.hasNextInt()) {
            VPN_PFN.put(sc.nextInt(), sc.nextInt());
        }
        sc.close();

        // TLBAccessesFile
        sc = new Scanner(new File(args[1]));
        int offset = sc.nextInt();
        ArrayList<Integer> access = new ArrayList<>();
        while (sc.hasNextInt()){
            access.add(sc.nextInt());
        }
        sc.close();

        // Replace Policy & entry
        String rep = args[2];
        int entry = Integer.parseInt(args[3]);

        // MAIN FUNCTION
        StringBuilder sb = new StringBuilder();                 // for storing answers
        LinkedList<Integer> new_tlb = new LinkedList<>();       // act as a new tlb of entry = args[3]
        HashSet<Integer> check = new HashSet<>();               // to check for hit and miss

        int misses = 0;
        for (int i = 0; i < access.size(); i++) {
            int x [] = convert_to_VPN(access.get(i),offset);    // custom function call to convert into VPN and store the offset value

            int add = (VPN_PFN.get(x[0]) << offset) + x[1];
            sb.append(add).append(" ");
            if (check.contains(x[0])){
                sb.append("HIT\n");
                new_tlb.remove((Integer) x[0]);
            }
            else {
                misses++;
                sb.append("MISS\n");
                if (new_tlb.size() == entry) {
                    if (rep.equals("LRU")) {
                        check.remove(new_tlb.get(0));
                        new_tlb.remove(0);
                    }
                    else {
                        check.remove(new_tlb.get(new_tlb.size()-1));
                        new_tlb.remove(new_tlb.size()-1);
                    }
                }
            }
            new_tlb.add(x[0]);
            check.add(x[0]);
        }

        // CREATING OUTPUT FILE
        File pr = new File("20116031"+"_"+args[0]+"_"+args[1]+"_"+args[2]+"_"+args[3]+".txt");

        FileWriter out = new FileWriter(pr);
        out.write("TOTAL_ACCESSES = "+access.size()+"\n");          // Accesses
        out.write("TOTAL_MISSES = "+misses+"\n");                   // Misses
        out.write("TOTAL_HITS = "+(access.size()-misses)+"\n");     // Hits

        out.write(String.valueOf(sb));                                  // Addresses
        out.close();
    }

    static int[] convert_to_VPN (int x, int offset) {
        int [] ret = new int[2];

        for (int i = 0; i < offset; i++) {
            ret[1] += Math.pow(2,i) * (x&1);
            x = x >> 1;
        }

        ret[0] = x;
        return ret;
    }
}

