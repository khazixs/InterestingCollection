package com.ic.learn.algorithm.exercise;

public class MyPow {
    public static void main(String[] args) {
        System.out.println(myPow(0.00001,2147483647));
    }

    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n==0){
            return 1;
        }
        if (n==1){
            return x;
        }
        if ((n & 1) == 1) {
            if (n < 0) {
                return myPow(1 / x, (-n - 1) / 2) * myPow(1 / x, (-n - 1) / 2) * (1 / x);
            } else {
                return myPow(x, (n - 1) / 2) * myPow(x, (n - 1) / 2) * x;
            }
        } else {
            if (n < 0) {
                return myPow(1 / x, -n / 2) * myPow(1 / x, -n / 2);
            } else {
                return myPow(x, n / 2) * myPow(x, n / 2);
            }
        }
    }
}
