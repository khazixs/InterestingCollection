package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generate {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(Collections.singletonList(1)));
        for (int i = 1;i<numRows;i++){
            List<Integer> row = new ArrayList<>();
            List<Integer> last = res.get(i-1);
            for (int j = 0;j<i+1;j++){
                int left = j-1<0?0:last.get(j-1);
                int right = j>=last.size()?0:last.get(j);
                int cur = left+right;
                row.add(cur);
            }
            res.add(row);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = generate(5);
        for (int i = 0;i<res.size();i++){
            System.out.println(Arrays.toString(res.get(i).toArray()));
        }
    }
}
