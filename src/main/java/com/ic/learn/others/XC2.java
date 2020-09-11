package com.ic.learn.others;

import org.aspectj.weaver.ast.Or;

import java.util.*;

public class XC2 {
    static Queue<String> queue = new PriorityQueue<>();
    static Map<String,Integer> order = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] mode = str.split(" ");

        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < mode.length - 1; i++) {
            String[] from = mode[i].split("");
            String[] to = mode[i + 1].split("");
            List<String> list = new ArrayList<>(Arrays.asList(to));
            for (String f : from) {
                if (map.containsKey(f)) {
                    map.get(f).addAll(list);
                } else {
                    map.put(f, new HashSet<>(list));
                }
            }
        }
        for (String s : mode[0].split("")) {
            help(map, s, s, mode.length, 1);
        }
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
    static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {

            return 0;
        }
    }

    public static void help(Map<String, Set<String>> map, String from, String path, int length, int index) {
        if (index == length) {
            if (map.containsKey(from) && map.get(from) != null) {
                queue.offer(path + "--circular dependency");
            } else {
                queue.offer(path);
            }
        }
        if (map.containsKey(from)) {
            Set<String> list = map.get(from);
            for (String to : list) {
                if (path.contains(to)&&index!=length-1) {
                    return;
                } else {
                    int len = path.length();
                    path = path + to;
                    help(map, to, path, length, index + 1);
                    path = path.substring(0,len);
                }
            }
        }
    }
}
