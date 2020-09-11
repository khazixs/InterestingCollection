package com.ic.learn.others;

import java.util.List;

public class Relative_7 {
    public static int res = 0;
    public static void main(String[] args) {
        int[] digit = new int[]{1,1,2};
        System.out.println(relative_7(digit));
    }
    public static int relative_7 (int[] digit) {
        help(digit, 0);
        return res;
    }

    public static void help(int[] digit,int index){
        if (index > digit.length-1){
            return;
        }
        if (index == digit.length-1){
            if (confirm(digit)){
                res++;
            }
            return;
        }
        for (int i = index;i< digit.length;i++){
                exchange(digit,i,index);
                help(digit,index+1);
                exchange(digit,i,index);
        }
    }

    public static boolean confirm(int[] digit){
        int temp = 0;
        int length = digit.length;
        for (int i = 0;i < length;i++){
            temp = temp + digit[i]*(int)Math.pow(10,length-1-i);
        }
        System.out.println(temp);
        return temp%7==0;
    }

    public static void exchange(int[] digit,int left,int right){
        int temp = digit[left];
        try {
            digit[left] = digit[right];
            digit[right] = temp;
        }catch (Exception e){
            System.out.println(right);
        }
    }
}
