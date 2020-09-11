package com.ic.learn.others;

import java.util.Scanner;

public class DIANXIN3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(",");
        int[] data = new int[s.length];
        for(int i = 0;i<data.length;i++){
            data[i] = Integer.parseInt(s[i]);
        }
        int[] maxYes = new int[data.length];
        int[] maxNo = new int[data.length];

        for(int i = 0;i<data.length;i++){
            maxYes[i] = i>0?Math.max(maxYes[i-1],maxNo[i-1]+data[i]):Math.max(data[i],0) ;
            maxNo[i] = i>0?Math.max(maxYes[i-1],maxNo[i-1]):0;
        }
        System.out.println(Math.max(maxYes[data.length-1],maxNo[data.length-1]));
    }
}
