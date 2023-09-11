package com.company;

import java.io.*;
import java.util.*;

public class _20116031 {
    public static void main(String[] args) throws IOException {

        //INPUT LOADING

        Scanner scanner1 = new Scanner(new File(args[0]));
        Scanner scanner2 = new Scanner(new File(args[1]));
        String replacement_policy = args[2];
        int TLB_Size = Integer.parseInt(args[3]);
        for(int i=0; i<3;i++){
            String skip=scanner1.nextLine();
        }
        HashMap<Integer,Integer> VnP = new HashMap<>();
        while (scanner1.hasNextInt()) {
            VnP.put(scanner1.nextInt(), scanner1.nextInt());
        }
        scanner1.close();
        int osb = scanner2.nextInt();
        ArrayList<Integer> A = new ArrayList<>();
        while (scanner2.hasNextInt()){
            A.add(scanner2.nextInt());
        }
        scanner2.close();

        //working code starts here

        ArrayList<Integer> TLB = new ArrayList<>();
        String Physical_Addresses[] = new String[A.size()];

        int no_of_hits = 0;
        for (int i = 0; i < A.size(); i++) {
            int x = A.get(i), y = 0;
            for (int j = 0; j < osb; j++) {
                y += Math.pow(2,j) * (x&1);
                x = x >> 1;
            }

            int add = (VnP.get(x) << osb) + y;
            String out = add+" ";
            if (TLB.contains(x)){
                out += "HIT";
                no_of_hits++;
                TLB.remove((Integer) x);
            }
            else {
                out += "MISS";
                if (TLB.size() == TLB_Size) {
                    if (replacement_policy.equals("LRU")) {
                        TLB.remove(0);
                    }
                    else {
                        TLB.remove(TLB.size()-1);
                    }
                }
            }
            TLB.add(x);
            Physical_Addresses [i] = out;
        }

        File s = new File("20116031"+"_"+args[0]+"_"+args[1]+"_"+args[2]+"_"+args[3]+".txt");

        FileWriter out = new FileWriter("20116031"+"_"+args[0]+"_"+args[1]+"_"+args[2]+"_"+args[3]+".txt");
        out.write("TOTAL_ACCESSES = "+A.size()+"\nTOTAL_MISSES = "+(A.size()-no_of_hits)+"\nTOTAL_HITS = "+no_of_hits+"\n");
        for (String S: Physical_Addresses ) {
            out.write(S+"\n");
        }
        out.close();
    }
}



