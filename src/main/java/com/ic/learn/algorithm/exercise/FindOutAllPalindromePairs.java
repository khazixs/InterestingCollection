package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FindOutAllPalindromePairs {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null||words.length==0||words.length==1){
            return result;
        }
        for (int i = 0;i< words.length-1;i++){
            for (int j = i+1;j< words.length;j++){
                if (isPalindromePair(words[i],words[j])){
                    result.add(new ArrayList<>(Arrays.asList(i,j)));
                }
                if (isPalindromePair(words[j],words[i])){
                    result.add(new ArrayList<>(Arrays.asList(j,i)));
                }
            }
        }
        return result;
    }

    public boolean isPalindromePair(String str1, String str2) {
        String str = str1 + str2;
        int length = str.length();
        int i = 0;
        int j = length - 1 - i;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j = length - 1 - i;
        }
        return true;
    }

    @Test
    public void test() {
        String[] words = new String[]{"abcd","dcba","lls","s","sssll"};
        List<List<Integer>> res = palindromePairs(words);
        for (List<Integer> r : res){
            System.out.println(Arrays.toString(r.toArray()));
        }
    }

    @Test
    public void test2() {
            int[] nums = new int[]{1,2,3,3,3,4,4,5};
            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] != nums[i]) {
                    i++;
                    nums[i] = nums[j];
                }
            }
        }
}

