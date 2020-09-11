package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class MergeFraction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] source = sc.nextLine().split(" ");
        int firstUp = source[0].charAt(0) - '0';
        int firstDown = source[0].charAt(2) - '0';
        String sign = source[1];
        int secondUp = source[2].charAt(0) - '0';
        int secondDown = source[2].charAt(2) - '0';
        int resultUp = 0;
        int resultDown = 0;
        switch (sign) {
            case "+":
                resultDown = firstDown * secondDown;
                resultUp = firstUp * secondDown + firstDown * secondUp;
                break;
            case "-":
                resultDown = firstDown * secondDown;
                resultUp = firstUp * secondDown - firstDown * secondUp;
                break;
            case "*":
                resultDown = firstDown * secondDown;
                resultUp = firstUp * secondUp;
                break;
            case "/":
                resultDown = firstDown * secondUp;
                resultUp = firstUp * secondDown;
        }
        System.out.println(handle(resultUp, resultDown));
    }

    public static String handle(int resultUp, int resultDown) {
        if (resultUp == 0){
            return "0";
        }
        boolean flag = true;
        if (resultUp < 0) {
            flag = false;
            resultUp = -resultUp;
        }
        if (resultDown==resultUp){
            return flag?"1":"-1";
        }
        int target = 2;
        while (target <= resultUp && target <= resultDown) {
            while ((resultUp / target * target) == resultUp) {
                if ((resultDown / target * target) == resultDown) {
                    resultUp = resultUp / target;
                    resultDown = resultDown / target;
                } else {
                    break;
                }
            }
            target++;
        }
        return flag ? resultUp + "/" + resultDown : "-" + resultUp + "/" + resultDown;
    }
}
