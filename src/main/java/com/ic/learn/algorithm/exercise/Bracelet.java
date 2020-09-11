package com.ic.learn.algorithm.exercise;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Bracelet {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        int[] last = new int[c + 1];
        int[] early = new int[c + 1];
        Set<Integer> set = new HashSet<>();
        for (int index = 1; index <= n; index++) {
            int t = sc.nextInt();
            if (t == 0) continue;
            for (int color = 0; color < t; color++) {
                int tmp = sc.nextInt();
                if (set.contains(tmp)){
                    continue;
                }
                if (early[tmp] == 0) {
                    early[tmp] = index;
                    continue;
                }
                if (last[tmp] > 0 && index - last[tmp] + 1 <= m) {
                    set.add(tmp);
                }
                if (index + m - 1 > n && early[tmp] <= (index + m - 1) % n) {
                    set.add(tmp);
                }
                last[tmp] = index;
            }
        }
        System.out.println(set.size());
        sc.close();
    }
}
