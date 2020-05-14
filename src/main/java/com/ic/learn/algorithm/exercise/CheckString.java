package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class CheckString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            check(sc.nextLine());
        }
    }

    public static void check(String data) {
        char[] chars = data.toCharArray();
        int flag1 = -1;/*不为-1表示出现了两个一样的字母*/
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1] && flag1 == -1) {
                flag1 = i - 1;
            } else if (chars[i] == chars[i - 1] && flag1 != -1&&i-flag1==2) {
                chars[flag1] = '*';
                i = flag1;
                flag1 = i-2;
            } else if (chars[i] == chars[i - 1] && flag1 != -1 && i - flag1 == 3) {
                chars[i - 1] = chars[i-2];
                chars[i-2] = chars[flag1];
                chars[flag1] = '*';
                i = i - 1;
                flag1 = flag1+1;
            } else if (chars[i] == chars[i - 1] && flag1 != -1 && i - flag1 > 3) {
                flag1 = i - 1;
            } else if (i-flag1>2){
                flag1 = -1;
            }
        }
        for (char a : chars) {
            if (a != '*') {
                stringBuilder.append(a);
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
