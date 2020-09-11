package com.ic.learn.others;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class XC1 {
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        String mode = sc.nextLine();
        String str = sc.nextLine();
        String target = sc.nextLine();
        help(mode.toCharArray(),0);
        StringBuilder res = new StringBuilder();
        for (int i = 0;i<str.length()-mode.length();i++){
            String a = str.substring(i,i+mode.length());
            boolean f = false;
                for (String s : set){
                    if (s.equals(a)){
                        res.append(target);
                        f =true;
                        break;
                    }
                }
                if (!f){
                    res.append(a);
                }
                i = i+mode.length();
        }
        System.out.println(res.toString());
    }

    public static void help(char[] mode,int index){
        if (index==mode.length){
            StringBuffer sb = new StringBuffer();
            for (char c : mode){
                sb.append(c);
            }
            set.add(sb.toString());
        }

        for (int i = index;i<mode.length;i++){
            swap(mode,index,i);
            help(mode,index+1);
            swap(mode,index,i);
        }
    }
    public static void swap(char[] mode,int index,int i){
        char t = mode[index];
        mode[index] = mode[i];
        mode[i] = t;
    }
}
