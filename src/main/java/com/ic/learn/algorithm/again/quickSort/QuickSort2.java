package com.ic.learn.algorithm.again.quickSort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort2 {
   public void quickSort(int[] nums){
       if (nums == null || nums.length == 0 || nums.length == 1){
           return;
       }
       sort(nums,0,nums.length-1);
   }

   public void sort(int[] nums,int left,int right){
       if (left>right){
           return;
       }
       int base = nums[left];
       int i = left,j = right;
       while (i!=j){
           while (i<j&&nums[j]>=base){
               j--;
           }
           while (i<j&&nums[i]<=base){
               i++;
           }
           if (i<j){
               int tmp = nums[i];
               nums[i] = nums[j];
               nums[j] = tmp;
           }
       }

       nums[left] = nums[i];
       nums[i] = base;
       sort(nums,left,i-1);
       sort(nums,i+1,right);
   }

    @Test
    public void test(){
        int[] nums = new int[]{1,3,5,7,9,2,4,6,8,10};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
