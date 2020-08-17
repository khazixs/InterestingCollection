package com.ic.learn.algorithm.exercise;

public class Game24Points {
    public boolean Game24Points (int[] arr) {
        return getResult(arr,0,0);
    }

    public boolean getResult(int[] arr,int i,int result){
        if (result == 24){
            return true;
        }
        if (i<arr.length){
            return getResult(arr,i+1,result+arr[i])
                    ||getResult(arr,i+1,result-arr[i])
                    ||getResult(arr,i+1,result==0?1: result*arr[i])
                    ||getResult(arr,i+1,result==0?1: result/arr[i]);
        }else{
            return false;
        }
    }
}
