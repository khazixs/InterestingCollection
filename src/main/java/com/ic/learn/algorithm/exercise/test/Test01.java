package com.ic.learn.algorithm.exercise.test;

public class Test01 {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        int n = Integer.MAX_VALUE;
        int i = 0;
        while (n/2 != 0){
            i++;
            n<<=1;
        }
        System.out.println("这个数字是2的"+i+"次幂");
    }
}
