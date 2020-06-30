package com.ic.learn.map;

import com.ic.userManagement.entity.User;

import java.util.TreeMap;

public class UseTreeMap {
    public static void main(String[] args) {
        TreeMap<User,String> treeMap = new TreeMap<>();
        treeMap.put(new User(),"user");
        System.out.println();
    }
    class Test implements Comparable{
        int i;
        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
}
