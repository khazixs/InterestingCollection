package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.*;

public class SXF1 {
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int arrNum = sc.nextInt();
        List<String[]> oss = new ArrayList<>();
        List<String[]> keyss = new ArrayList<>();
        sc.nextLine();
        for (int i = 0; i < arrNum; i++) {
            sc.nextLine();
            String[] os = sc.nextLine().split(" ");
            String[] keys = sc.nextLine().split(" ");
            oss.add(os);
            keyss.add(keys);
        }
        for (int i = 0; i < oss.size(); i++) {
            help(oss.get(i), keyss.get(i), oss.get(i)[0].length());
            System.out.println(res == Integer.MAX_VALUE ? "NOT POSSIBLE" : res);
            res = Integer.MAX_VALUE;
        }
    }

    public static void help(String[] os, String[] keys, int size) {
        for (int i = 0; i < (Math.pow(2, size) - 1); i++) {
            Queue<String> queue1 = new PriorityQueue<>();
            Queue<String> queue2 = new PriorityQueue<>();
            for (int k = 0;k<os.length;k++){
                String s = os[k];
                Integer sn = (Integer.parseUnsignedInt(s,2)^i);
                String sns = Integer.toBinaryString(sn);
                while (sns.length()!=size){
                    sns = "0"+sns;
                }
                queue1.offer(sns);
                queue2.offer(keys[k]);
            }
            boolean can = true;
            while (!queue1.isEmpty()) {
                if (!queue1.poll().equals(queue2.poll())) {
                    can = false;
                    break;
                }
            }
            if (can) {
                int count = 0;
                for (int j = 0;j<size;j++){
                    if ((i>>j&1)==1){
                        count++;
                    }
                }
                res = Math.min(count, res);
            }
        }
    }

    @Test
    public void test(){
        String a = "1010";
        int s = Integer.parseUnsignedInt(a,2);
        System.out.println(s);
    }
}
