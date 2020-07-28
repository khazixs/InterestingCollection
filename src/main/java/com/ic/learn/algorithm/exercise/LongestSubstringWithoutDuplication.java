package com.ic.learn.algorithm.exercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class LongestSubstringWithoutDuplication {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring1("abcfbedhgj"));
    }

    public static int lengthOfLongestSubstring1(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j))+1, i);/*更新开始记录的位置*/
            }
            ans = Math.max(ans, j - i + 1);/*看当前这段不重复字符的长度是否超过历史最长的不重复字符串的长度*/
            map.put(s.charAt(j), j);/*更新字符的位置*/
        }
        return ans;
    }
    public static int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        int n = s.length();
        int ans = 0, i = 0, j = 0;
        Set<Character> mySet = new HashSet<>();
        while (i < n && j < n) {
            if (!mySet.contains(s.charAt(j))) {
                mySet.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                mySet.remove(s.charAt(i++));
            }
        }
        return ans;
    }


    public static int longestSubstring(String data) {
        if (data == null || data.length() == 0) {
            return 0;
        }
        if (data.length() == 1) {
            System.out.println(data);
            return 1;
        }
        int length = data.length();
        Map<Character, Integer> map = new HashMap<>();
        map.put(data.charAt(0), 0);
        int[] results = new int[length];
        results[0] = 1;
        int start = -1;
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
        for (int i = 1; i < length; i++) {
            if (!map.containsKey(data.charAt(i))) {
                if (i - start >= results[i - 1]) {
                    results[i] = results[i - 1] + 1;
                    queue.clear();
                    queue.add(data.substring(start + 1, i + 1));
                } else {
                    results[i] = results[i - 1];
                }
                map.put(data.charAt(i), i);
            } else {
                int lastIndex = map.get(data.charAt(i));
                start = Math.max(lastIndex, start);
                results[i] = Math.max(i - start, results[i - 1]);
                if (i - start > results[i - 1]) {
                    queue.clear();
                    queue.add(data.substring(start + 1, i + 1));
                } else if (i - start == results[i - 1]) {
                    queue.add(data.substring(start + 1, i + 1));
                }
                map.replace(data.charAt(i), i);
            }
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        return results[length - 1];
    }
}
