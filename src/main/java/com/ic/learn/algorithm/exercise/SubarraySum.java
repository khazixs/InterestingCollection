package com.ic.learn.algorithm.exercise;

import java.util.HashMap;
import java.util.Map;
/*给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
* 说明 :
        1.数组的长度为 [1, 20,000]。
        2.数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。*/

public class SubarraySum {

    public static int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (mp.containsKey(pre - k))
                count += mp.get(pre - k);
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, -1, -2, 1, 6, -8};
        System.out.println(subarraySum(nums, -3));
    }
}
