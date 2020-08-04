package com.ic.learn.algorithm.important;

import java.util.LinkedHashSet;
import java.util.Set;

public class Permutation {
    private static int count = 0;
    private static Set<String> result = new LinkedHashSet<>();

    public static void main(String[] args) {
        permutation(new String[]{"a", "b", "c", "d"});
        System.out.println("count"+count);
        System.out.println(result.size());
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static void permutation(String[] data) {
        int length = data.length;
        if (length == 0) {
            return;
        }
        help(data, 0);
    }

    public static void help(String[] data, int index) {
        if (index == data.length - 1) {
            result.add(toString(data));
            count++;
        }
        for (int i = index; i < data.length; i++) {
            swap(data, i, index);
            help(data, index+1);
            swap(data, i, index);
        }
    }

    public static void swap(String[] data, int i, int j) {
        String temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static String toString(String[] data){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : data){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
