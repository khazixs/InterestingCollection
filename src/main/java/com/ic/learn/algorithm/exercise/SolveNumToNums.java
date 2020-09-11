package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class SolveNumToNums {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();
        int solve = 2;
        if (data<2){
            System.out.println(data);
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (solve<=data){
            while (data%solve==0){
                sb.append(solve);
                sb.append("*");
                data = data/solve;
            }
            solve++;
        }
        String res = sb.toString();
        res = res.substring(0,res.length()-1);
        System.out.println(res);
    }
}
