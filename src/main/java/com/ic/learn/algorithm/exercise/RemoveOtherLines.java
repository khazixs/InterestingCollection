package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class RemoveOtherLines {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] data = sc.nextLine().toCharArray();
        char last = '_';
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<data.length;i++){
            char target = data[i];
            if (target=='_'&&last!='_'){
                sb.append(target);
                last=target;
            }else if (target!='_'){
                sb.append(target);
                last=target;
            }
        }
        String res = sb.toString();
        if (res.length()==0){
            System.out.println("");
            return;
        }
        if (res.charAt(res.length()-1)=='_'){
            res = res.substring(0,res.length()-1);
        }
        System.out.println(res);
    }
}
