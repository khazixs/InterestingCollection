package com.ic.learn.others;

import java.util.Scanner;

public class Main081 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int n = sc.nextInt();
        int[] pn = new int[n+1];
        int[] wn = new int[n+1];
        for (int i = 0;i<n;i++){
            pn[i] = sc.nextInt();
            wn[i] = sc.nextInt();
        }
        int[] r = new int[n+1];
        for (int i = 0;i<r.length;i++){
            int q = 0;
            for(int j = 0;j<pn.length;j++){
                if (i>=pn[j]){
                    q = Math.max(q,wn[j]+r[i-pn[j]]);
                }
            }
            r[i] = q;
        }
        System.out.println(r[n]);
    }
}
