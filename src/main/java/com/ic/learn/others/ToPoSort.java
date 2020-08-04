package com.ic.learn.others;

import org.junit.Test;

import java.util.*;

public class ToPoSort {
    private boolean valid = true;
    private int[] visited;
    private List<List<Integer>> edges = new ArrayList<>();
    private Stack<Integer> result = new Stack<>();
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        for (int i = 0;i<numCourses;i++){
            edges.add(new ArrayList<>());
        }
        for (int[] info : prerequisites){
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0;i<numCourses&&valid;i++){
            if (visited[i]==0){
                dfs(i);
            }
        }
        if (valid){
            while (!result.isEmpty()){
                System.out.printf("%d ",result.pop());
            }

        }
        return valid;
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites){
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegrees = new int[numCourses];
        for (int i = 0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        for (int[] info : prerequisites){
            inDegrees[info[0]]++;
            adj.get(info[1]).add(info[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0;i<numCourses;i++){
            if (inDegrees[i] == 0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int v = queue.poll();
            System.out.printf("%d ",v);
            numCourses--;
            for (int k : adj.get(v)){
                if (--inDegrees[k]==0){
                    queue.add(k);
                }
            }
        }
        return numCourses==0;
    }


    public void dfs(int u){
        visited[u] = 1;
        for (int v : edges.get(u)){
            if (visited[v]==0){
                dfs(v);
                if (!valid){//递归过程中发现了闭环，那么下面的就没必要了所以直接返回
                    return;
                }
            }else if (visited[v] == 1){//发现闭环
                valid = false;
                return;
            }
        }
        visited[u] = 2;
        result.push(u);
    }

    @Test
    public void test(){
        int num = 4;
        int[][] p = new int[][]{{1,0},{2,1},{3,1}};
        System.out.println(canFinish(num,p));
        System.out.println(canFinish2(num,p));
    }
}
