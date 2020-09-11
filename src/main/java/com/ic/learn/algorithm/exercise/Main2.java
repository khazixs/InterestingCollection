package com.ic.learn.algorithm.exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
*
7
1 2
1 3
2 4
2 5
3 6
3 7
* */
public class Main2 {
    static int res = 2;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("E:\\project\\InterestingCollection\\src\\main\\resources\\doc\\Main2.txt"));
        int nodes = sc.nextInt();
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> nodeList = new ArrayList<>();
        while(sc.hasNextInt()) {
            int temp = sc.nextInt();
            List<Integer> a = new ArrayList<>();
            if (!map.containsKey(temp)) {
                nodeList.add(temp);
                a.add(sc.nextInt());
                map.put(temp, a);
            } else {
                map.get(temp).add(sc.nextInt());
            }
        }
        if (nodeList.size() <= 2) {
            System.out.println(0);
        }
        Map<Integer, Integer> mapCount = new HashMap<>();
        getCount(mapCount,map,1,0);
        for (Integer i : mapCount.values()){
            res = Math.max(res,i);
        }
        System.out.println(res);
    }

    public static void getCount(Map<Integer, Integer> mapCount, Map<Integer, List<Integer>> map, int root, int count) {
        if (!map.containsKey(root)){
            return;
        }
        List<Integer> list = map.get(root);
        if (list.size() > 1) {
            count++;
        }
        for (int i = 0; i < list.size(); i++) {
            root = list.get(i);
            mapCount.put(root, count);
            getCount(mapCount,map,root,count);
        }
    }
}
