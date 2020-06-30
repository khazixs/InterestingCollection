package com.ic.learn.algorithm.exercise;

public class BitOperation {
    public static void main(String[] args) {
        int a = 7;
        int b = 33241236;
        System.out.println(a>>1);/*右移运算可以代替除2*/
        System.out.println(b&2-1);/*当除数是2的n次幂时，可以用位与运算代替求余,可以利用这个特性快速判断奇偶性*/
    }
}
