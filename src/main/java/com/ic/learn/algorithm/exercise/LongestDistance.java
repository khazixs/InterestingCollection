package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class LongestDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] data = sc.next().toCharArray();
        char target1 = sc.next().charAt(0);
        char target2 = sc.next().charAt(0);

        int res = -1;
        int first1 = -1;
        int first2 = -1;
        int index1 = 0;
        int index2 = 0;
        if (data.length==0){
            System.out.println(-1);
            return;
        }
        for (int i=0;i<data.length;i++){
            char t = data[i];
            if (Math.abs(t-target1)==0||Math.abs(t-target1)==32){
                if (first1==-1){
                    first1 = i;
                }else{
                    if (first2!=-1){
                        res = Math.max(res,i-first2);
                    }
                }
            }

            if (Math.abs(t-target2)==0||Math.abs(t-target2)==32){
                if (first2==-1){
                    first2 = i;
                }else{
                    if (first1!=-1){
                        res = Math.max(res,i-first1);
                    }
                }
            }
        }
        if (first1!=-1&&first2!=-1){
            res = Math.max(res,Math.abs(first1-first2));
        }
        System.out.println(res);
    }
}
