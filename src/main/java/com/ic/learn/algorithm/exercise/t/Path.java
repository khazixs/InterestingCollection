package com.ic.learn.algorithm.exercise.t;

import java.util.*;

public class Path {
    public static void main(String[] args) {
        List<List<String>> list = answer("(A,B);(A,C);(C,E);(C,B);(C,D);(D,B);(E,B)");
        for (List<String> l : list){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
    static List<List<String>> answer(String inputStr){
        List<List<String>> res = new ArrayList<>();
        int cityNum = 0;
        List<String> map = new ArrayList<>();
        String[] a = inputStr.replaceAll("[;,]"," ").replaceAll("[()]","").split(" ");
        for (String s : a) {
            if (!map.contains(s)) {
                map.add(s);
                cityNum++;
            }
        }
        int[][] matrix = new int[cityNum][cityNum];
        for (int i = 0;i<a.length;i+=2){
            matrix[map.indexOf(a[i])][map.indexOf(a[i+1])] = 1;
        }
        Stack<String> stack = new Stack<>();
        stack.push("A");
        String next = "";
        while (!stack.isEmpty()){
            while(!(next = getNext(stack.peek(),map,matrix)).equals("")){
                if (next.equals("B")){
                    stack.push("B");
                    String[] b = new String[stack.size()];
                    List<String> path = new ArrayList<>(Arrays.asList(stack.toArray(b)));
                    res.add(path);
                }else{
                    stack.push(next);
                }
            }
            stack.pop();
        }
        return res;
    }

    static String getNext(String now,List<String> map,int[][] matrix){
        int col = matrix[0].length;
        for (int i = 0;i<col;i++){
            if (matrix[map.indexOf(now)][i] == 1){
                matrix[map.indexOf(now)][i] = -1;
                return map.get(i);
            }
        }
        return "";
    }

}
