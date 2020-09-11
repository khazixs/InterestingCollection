package com.ic.learn.algorithm.exercise;

import com.ic.learn.algorithm.exercise.Group;

import java.util.*;

public class TX2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int g = sc.nextInt();
        int[] gg= new int[g];
        List<List<Integer>> data = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < g; i++) {
            int num = sc.nextInt();
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                int k = sc.nextInt();
                if (!map.containsKey(k)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(k, list);
                } else {
                    map.get(k).add(i);
                }
                l.add(k);
            }
            data.add(l);
        }
        List<Integer> zeros = map.get(0);
        Queue<Integer> queue = new LinkedList<>();
    }

}
