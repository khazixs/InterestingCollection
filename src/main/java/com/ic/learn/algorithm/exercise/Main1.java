package com.ic.learn.algorithm.exercise;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    static Set<String> set = new HashSet<>();
    static int size;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int[] source = new int[4];
        source[0] = a;
        source[1] = b;
        source[2] = c;
        source[3] = d;
        size = N*N;
        help(source,new StringBuilder(),0);
        System.out.println(set.size()%998244353);
    }

    public static void help(int[] source,StringBuilder sb,int index){
        int length = sb.length();
        if (length==size){
            String data = sb.toString();
            if (!set.contains(data)){
                set.add(data);
            }else{
                return;
            }
        }
        for (int i = 0;i<4;i++){
            if (source[i]>0){
                source[i]--;
                sb.append(i);
                help(source,sb,index+1);
                source[i]++;
                sb.deleteCharAt(index);
            }
        }
    }
}
