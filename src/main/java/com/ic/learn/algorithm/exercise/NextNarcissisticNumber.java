package com.ic.learn.algorithm.exercise;

import org.junit.Test;

public class NextNarcissisticNumber {
    public long nextNarcissisticNumber(int n) {
        // write code here
        long target = 1;
        while (target<=(long)n||!help(target)) {
            target++;
        }
        return target;
    }

    public boolean help(long target) {
        char[] s = String.valueOf(target).toCharArray();
        int n = s.length;
        long sum = 0;
        for (int i = 0;i<n;i++){
            sum+=Math.pow(s[i]-'0',n);
        }
        return sum==target;
    }

    @Test
    public void test() {
        int num = 8;
        System.out.println(nextNarcissisticNumber(num));
    }
}
