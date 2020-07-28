package com.ic.learn.algorithm.exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/*输出所有的字符串排列*/
public class Permutation {
    public static Set<String> permutation(String str) {
        Set<String> res = new HashSet<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        helper(res, 0, str.toCharArray());
        return res;

    }

    private static void helper(Set<String> res, int index, char[] s) {
        if (index == s.length - 1) {
            res.add(String.valueOf(s));
            return;
        }
        for (int i = index; i < s.length; i++) {
            /*i==index表示不发生交换的原始情况*/
            /*s[index]!=s[i]表示结果中收入，其他两个位置不同时的结果，但是无法防止超过两个都是一样的情况出现重复，所以将数组换成set去重*/
            if (i == index || s[index] != s[i]) {
                swap(s, index, i);
                /*考虑问题，先不看迭代后的情况而是把每一层迭代的选择都列出来，便于分析规律和字母问题分界线*/
                helper(res, index + 1, s);
                swap(s, index, i);
            }
        }
    }

    public static void swap(char[] c, int a, int b) {
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("abcd").toArray()));
    }
}
