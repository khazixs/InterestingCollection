package com.ic.learn.exercise;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String vetoes = scanner.nextLine();
        String[] names = vetoes.split(",");
        if (names.length == 0){
            System.out.println("error.0001");
        }else if(names.length == 1){
            System.out.println(names[0]);
        }
        /*Tom,Lily,Tom,Lucy,Lucy,Tom,Jack*/
        List<String> list = new ArrayList<>(Arrays.asList(names));
        Collections.sort(list);
        int maxVetoNum = 0;
        int start = 0;
        String result = names[0];
        for (int i = 1;i<list.size();i++){
            if (!list.get(i).equals(result)||i==(list.size()-1)){
                if ((i-start)>maxVetoNum){
                    result = names[i-1];
                    maxVetoNum = i-start;
                    start = i;
                }
            }
        }
        System.out.println(result);
    }
}
