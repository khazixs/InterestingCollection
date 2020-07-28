package com.ic.learn.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestArrayListClear {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);
        System.out.println(Arrays.toString(list.toArray()));
        list.clear();
        System.out.println(Arrays.toString(list.toArray()));
        list.add(321);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
