package com.ic.learn.algorithm.again.fibonacci;

import org.junit.Test;

public class Fibonacci {
    public int fibonacci(int n){
        int[] result = new int[n+1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2;i<=n;i++){
            result[i] = result[i-1]+result[i-2];
        }
        return result[n];
    }

    @Test
    public void test(){
        System.out.println(fibonacci(10));
    }
}
