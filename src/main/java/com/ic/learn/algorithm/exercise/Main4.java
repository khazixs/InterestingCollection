package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chars = str.toCharArray();
        int left = 0;
        int right = 0;
        int res = 0;
        for (int i = 0;i<chars.length;i++){
            if (chars[i]=='('){
                left++;
            }
            if (chars[i]==')'){
                right++;
            }
            if (chars[i]=='-'){
                res = res+Math.min(left,right);
                right = 0;
                left = 0;
            }
        }
        res = res+Math.min(left,right);
        System.out.println(res);
    }
}
