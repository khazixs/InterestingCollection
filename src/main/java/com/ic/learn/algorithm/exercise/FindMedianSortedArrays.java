package com.ic.learn.algorithm.exercise;

import java.util.Arrays;

public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }

    public static double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1;
        int right = -1;
        int aStart = 0;
        int bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && nums2 != null && nums2.length != 0) {
            return help(nums2);
        } else if ((nums2 == null || nums2.length == 0) && nums1 != null && nums1.length != 0) {
            return help(nums1);
        }

        assert nums1 != null;
        assert nums2 != null;
        int n = nums1.length + nums2.length;
        int[] array = new int[n];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] <= nums2[index2]) {
                array[index] = nums1[index1];
                index1++;
            } else {
                array[index] = nums2[index2];
                index2++;
            }
            index++;
        }
        while (index1 < nums1.length && index < n) {
            array[index++] = nums1[index1++];
        }

        while (index2 < nums2.length && index < n) {
            array[index++] = nums2[index2++];
        }
        return help(array);
    }

    private static double help(int[] nums) {
        System.out.println(Arrays.toString(nums));
        if ((nums.length & 1) == 1) {
            return nums[nums.length / 2];
        }
        return (double) (nums[nums.length / 2 - 1] + nums[nums.length / 2]) / 2;
    }
}
