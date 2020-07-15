package com.ic.learn.algorithm.exercise;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String> un = new HashMap<>();
        Map<String, Integer> ut = new HashMap<>();
        int line = 0;
        while (line<1000) {
            line++;
            String str = sc.nextLine();
            if (str==null||str.equals("")||!sc.hasNextLine()){
                break;
            }
            String[] array = str.split(" ");
            String url = array[1];
            Integer time = Integer.parseInt(array[2]);
            String id = array[0];
            if (!un.containsKey(url)) {
                un.put(url, id);
                ut.put(url, time);
            } else {
                String user = un.get(url);
                user = user + " " + id;
                un.replace(url, user);
                Integer t = ut.get(url);
                t += time;
                ut.replace(url, t);
            }
        }
        int res = 0;
        String resUrl = "";
        for (String u : ut.keySet()) {
            if (ut.get(u) > 180) {
                int n = getDiffUsers(un.get(u));
                if (n > res) {
                    res = n;
                    resUrl = u;
                }
            }
        }
        if (resUrl.equals("")) {
            System.out.println("null");
        } else {
            System.out.println(resUrl);
        }

    }

    public static int getDiffUsers(String str) {
        String[] arr = str.split(" ");
        Set<String> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        return set.size();
    }
}
/*12678687 www.toutiao.com 31
12678687 www.byte.com 60
12678687 www.bytedance.com 210
12678685 www.toutiao.com 20
12678685 www.byte.com 90
12678683 www.toutiao.com 15
12678683 www.byte.com 40
12678688 www.toutiao.com 15*/