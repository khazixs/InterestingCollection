package com.ic.learn.algorithm.exercise;

import javax.xml.transform.Source;

public class IsSubString {
    public static boolean isSubsequence(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (lenS<lenT){
            return false;
        }
        int indexS = 0,indexT = 0;
        boolean get = false;
        while (indexT<lenT){
            char target = t.charAt(indexT++);
            get = false;
            while (indexS<lenS){
                if (target == s.charAt(indexS++)){
                    get = true;
                    break;
                }
            }
            if (!get){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ahcbgdc";
        String t = "abc";
        System.out.println(isSubsequence(s,t));
    }
}
