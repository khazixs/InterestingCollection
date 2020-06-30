package com.ic.learn.algorithm.exercise;

/*在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。*/
public class SingleNumber {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{9, 1, 7, 9, 7, 9, 7}));
    }

    public static int singleNumber(int[] nums) {
        int a = 0, b = 0;
        /*根据实际状况写出状态变换真值表，在根据真值表写出逻辑表达式*/
        for (int i : nums) {
            int temp = a;
            a = (~a & b & i) | (a & ~b & ~i);
            b = ~temp & (b ^ i);
        }
        return b;
    }
}
