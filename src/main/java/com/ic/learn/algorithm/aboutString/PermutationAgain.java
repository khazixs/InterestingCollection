package com.ic.learn.algorithm.aboutString;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PermutationAgain {
    public static Set<String> permutation(String sourceData) {
        char[] chars = sourceData.toCharArray();
        Set<String> mySet = new HashSet<>();
        return helper(mySet, chars, 0);
    }

    private static Set<String> helper(Set<String> mySet, char[] chars, int index) {
        int pc = index;
        for (; pc < chars.length; pc++) {
            swap(chars, pc, index);
            mySet.add(String.valueOf(chars));
            helper(mySet, chars, index + 1);
            swap(chars, index, pc);
        }
        return mySet;
    }

    private static void swap(char[] chars, int pc, int index) {
        char temp = chars[index];
        chars[index] = chars[pc];
        chars[pc] = temp;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("abcd").toArray()));
    }
}
