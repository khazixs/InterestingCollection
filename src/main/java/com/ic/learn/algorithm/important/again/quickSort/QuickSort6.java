package com.ic.learn.algorithm.important.again.quickSort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort6 {
    int n = 0;
    @Test
    public void test(){
        int[] nums = new int[]{3,2,1,4,6,5};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(n);
    }

    public void quickSort(int[] nums){
        if (nums == null||nums.length==0||nums.length==1){
            return;
        }
        sort(nums,0,nums.length-1);
    }
    public void sort(int[] nums,int left,int right){
        if (left<right){
            int base = nums[left];
            int i = left;
            int j = right;
            while (i!=j){
                while (i<j&&nums[j]>=base){
                    j--;
                }
                while (i<j&&nums[i]<=base){
                    i++;
                }
                if (i<j){
                    int t = nums[i];
                    nums[i] = nums[j];
                    nums[j] = t;
                }
            }
            nums[left] = nums[i];
            nums[i] = base;
            sort(nums,0,i-1);
            sort(nums,i+1,right);
        }
    }
}
