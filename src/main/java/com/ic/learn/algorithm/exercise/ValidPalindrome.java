package com.ic.learn.algorithm.exercise;

public class ValidPalindrome {
    static boolean skip = true;
    public static void main(String[] args) {

        System.out.println(validPalindrome("puupu"));
    }
    public static boolean validPalindrome(String s){
        int start = 0;
        int end  = s.length() -1;
        while (start<=end){
            if (!(s.charAt(start) == s.charAt(end))){
                if (skip){
                    skip = false;
                    return validPalindrome(s.substring(start+1,end+1))||validPalindrome(s.substring(start,end));
                }
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
