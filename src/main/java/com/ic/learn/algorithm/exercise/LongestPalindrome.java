package com.ic.learn.algorithm.exercise;

public class LongestPalindrome {
    public static void main(String[] args) {
        String data = "abcbada";
        System.out.println(longestPalindrome(data));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLength = 0;
        String res = "";
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = s.length() - 1; j > i + maxLength; j--) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (help(s, i, j,dp)) {
                        maxLength = j - i;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        if (res.length() == 0) {
            return s.substring(0, 1);
        }
        return res;
    }

    private static boolean help(String data, int i, int j, boolean[][] dp) {
        int start = 0;
        int end = 0;
        if ((j - i) % 2 == 0) {
            start = (i + j) / 2 - 1;
            end = (i + j) / 2 + 1;
        } else {
            start = (i + j) / 2;
            end = start + 1;
        }
        while (start >= i && end <= j) {
            if (!dp[start][end]) {
                if (data.charAt(start) == data.charAt(end)) {
                    dp[start][end] = true;
                } else {
                    return false;
                }
            }
            start--;
            end++;
        }
        return true;
    }
}
