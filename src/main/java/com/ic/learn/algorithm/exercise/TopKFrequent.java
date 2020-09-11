package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.*;

public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        Map<Integer, Num> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.get(i).up();
            } else {
                map.put(i,new Num(i,1));
            }
        }
        Queue<Num> queue = new PriorityQueue<>(new MyComparator());
        for (Num num : map.values()){
            queue.offer(num);
        }
        int[] res = new int[k];
        for (int i = 0;i<k;i++){
            res[i] = queue.poll().getVal();
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums = new int[]{1,1,1,2,2,3};
        System.out.println(Arrays.toString(topKFrequent(nums,2)));
    }
}

class Num {
    private int val;
    private int fre;

    public Num(int val, int fre) {
        this.val = val;
        this.fre = fre;
    }

    public void up() {
        this.fre++;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getFre() {
        return fre;
    }

    public void setFre(int fre) {
        this.fre = fre;
    }
}

class MyComparator implements Comparator<Num> {
    @Override
    public int compare(Num o1, Num o2) {
        return o2.getFre() - o1.getFre();
    }
}