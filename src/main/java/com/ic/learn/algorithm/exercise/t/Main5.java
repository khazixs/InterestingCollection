package com.ic.learn.algorithm.exercise.t;

import sun.security.util.Length;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("F:\\project\\InterestingCollection\\src\\main\\resources\\doc\\test1.txt"));
//        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        for (int i = 0;i<N;i++){
            String[] s = sc.nextLine().split(" ");
            int nums = Integer.parseInt(s[0]);
            int[] data = new int[nums];
            for (int j = 0;j<nums;j++){
                data[j] = Integer.parseInt(s[j+1]);
            }
            System.out.println(checkIt(data)?"YES":"NO");
        }
    }

    private static boolean checkIt(int[] data) {
        if (data.length<4){
            return false;
        }
        int sum = 0;
        for (int v : data){
            sum+=v;
        }
        int l = sum/4;
        if (l*4 != sum){
            return false;
        }
        /*用count表示已经拼好的边个数*/
        int count = 0;
        int p = 0;
        while (count<4){
            for (int j = data.length-1;j>=0;j--){
                if (data[j]!=-1){
                    if (data[j]>l){
                        return false;
                    }
                    if (data[j] == l){
                        p = data[j];
                        data[j] = -1;
                        break;
                    }
                    if (p+data[j]<=l) {
                        p += data[j];
                        data[j] = -1;
                        if (p == l) {
                            break;
                        }
                    }
                }
            }
            if (p!=l){
                return false;
            }
            count++;
            p = 0;
        }
        return count == 4;
    }
}
