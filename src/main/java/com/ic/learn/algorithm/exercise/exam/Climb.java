package com.ic.learn.algorithm.exercise.exam;

public class Climb {
    public static void main(String[] args) {
        System.out.println(climb(3,false));
    }

    public static int climb(int n,boolean flag){
        int count = 0;
        if (n<=0){
            return 0;
        }
        if (n==1){
            count++;
        }else if(n==2){
            count+=2;
        }
        if(!flag){
            flag = true;
            count += climb(n-3,true);
        }
        return count+climb(n-2,false)+climb(n-1,false);
    }
}
