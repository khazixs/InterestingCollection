package com.ic.learn.algorithm.exercise;

import org.junit.Test;

public class GetCoinCount {
    public int GetCoinCount (int N) {
        if (N>=1024){
            return 0;
        }
        if (N==0){
            return 1;
        }
        int data = 1024 - N;
        int result = 0;
        result+=data/64;
        data = data%64;
        result+=data/16;
        data = data%16;
        result+=data/4;
        data = data%4;
        result+=data;
        return result;
    }
    @Test
    public void test(){
        System.out.println(GetCoinCount(1000));
    }
}
