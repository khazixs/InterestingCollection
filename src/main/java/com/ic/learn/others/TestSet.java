package com.ic.learn.others;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestSet {
    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("123123");
        stringSet.add("12445");
        for (String string : stringSet){
            System.out.println(string);
        }
        Iterator iterator = stringSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
