package com.ic.learn.algorithm.important.again.binarySearch;

import org.junit.Test;

public class BinarySearch {

    /*二分查找必须是排序数组*/
    public int binarySearch(int[] nums, int target, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                return binarySearch(nums, target, mid + 1, end);
            } else {
                return binarySearch(nums, target, start, mid);
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(binarySearch(nums, 10, 0, nums.length - 1));
    }
}
