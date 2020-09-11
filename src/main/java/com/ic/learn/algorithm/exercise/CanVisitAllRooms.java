package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.*;

public class CanVisitAllRooms {
    private Queue<Integer> queue = new LinkedList<>();
    private boolean[] in;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms==null){
            return false;
        }
        in = new boolean[rooms.size()];
        List<Integer> list = rooms.get(0);
        for (int i : list){
            queue.offer(i);
        }
        while (!queue.isEmpty()){
            int key = queue.poll();
            if (in[key]){
                continue;
            }
            in[key] = true;
            List<Integer> keys = rooms.get(key);
            for (int i : keys){
                queue.offer(i);
            }
        }
        for (int i = 1;i<in.length;i++){
            if (!in[i]){
                return false;
            }
        }
        return true;
    }

    @Test
    public void test(){
        int[][] data = new int[][]{{1,3}, {3,0,1},{2},{0}};
        List<List<Integer>> rooms = new ArrayList<>();
        for (int[] row : data){
            List<Integer> list = new ArrayList<>();
            for (int d : row){
                list.add(d);
            }
            rooms.add(list);
        }
        System.out.println(canVisitAllRooms(rooms));
    }
}
