package com.ic.learn.algorithm.exercise;

public class Jump {
    static int count;
    public static int jump(int[] numbers){
        help(numbers,0);
        return count;
    }
    public static void help(int[] numbers,int start){
        if (start>=numbers.length-1){
            return;
        }
        if (numbers[start]+start>=numbers.length-1){
            count++;
            return;
        }
        int d = numbers[start];
        int nextIndex = 0;
        int betterValue = 0;
        for (int i = 1;i<=d;i++){
            if ((numbers[start+i]+i)>=betterValue){
                betterValue = numbers[start+i]+i;
                nextIndex = start + i;
            }
        }
        count++;
        help(numbers,nextIndex);
    }

    public static void main(String[] args) {
        /*2,3,1,1,4*/
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
