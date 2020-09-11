package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TX1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] nums1 = new int[n1];
        for (int i = 0;i<n1;i++){
            nums1[i] = sc.nextInt();
        }
        int n2 = sc.nextInt();
        int[] nums2 = new int[n2];
        for (int i = 0;i<n2;i++){
            nums2[i] = sc.nextInt();
        }
        int i1 =0,i2=0;
        List<Integer> res = new ArrayList<>();
        while (i1<nums1.length&&i2<nums2.length){
            if (nums1[i1]==nums2[i2]){
                res.add(nums1[i1]);
            }
            if (nums1[i1] < nums2[i2]) {
                i2++;
            } else {
                i1++;
            }
        }
        for (int i = 0;i<res.size()-1;i++){
            System.out.print(res.get(i));
            System.out.print(" ");
        }
        System.out.println(res.get(res.size()-1));
    }
}
