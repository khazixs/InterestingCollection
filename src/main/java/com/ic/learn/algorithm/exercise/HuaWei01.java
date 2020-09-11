package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HuaWei01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> data = new ArrayList<>();
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            if (s.equals("")){
                break;
            }
            data.add(s);
        }
        int length = data.size();
        String target = data.get(length-1);
        int limit = Integer.parseInt(data.get(length-2));
        target = help(target,limit);
        for (int i = 0;i<length-2;i++){
            String temp = help(data.get(i),limit);
            if (temp.equals(target)||temp.contains(target)){
                System.out.println(data.get(i));
            }
        }

    }
    public static String help(String source,int limit){
        if (source==null||source.equals("")){
            return "";
        }
        int length = source.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i<length;i++){
            int temp = source.charAt(i)-'0';
            if (temp<limit){
                sb.append(temp);
            }
        }
        return sb.toString();
    }
}
