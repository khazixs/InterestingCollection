package com.ic.learn.algorithm.exercise.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer,Integer> userMap = new HashMap<>();
        for (int i = 0;i<n;i++){
            int next = sc.nextInt();
            if (userMap.containsKey(next)){
                int t = userMap.get(next);
                userMap.replace(next,t+1);
            }else{
                userMap.put(next,1);
            }
        }
        int q = sc.nextInt();
        for (int i = 0;i<q;i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            int k = sc.nextInt();
            if (k<l||k>r){
                System.out.println(0);
            }else{
                if (userMap.get(k)==null){
                    System.out.println(0);
                }else{
                    System.out.println(userMap.get(k));
                }
            }
        }
    }
}
