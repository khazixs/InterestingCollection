package com.ic.learn.algorithm.exercise;

import java.util.*;

/*现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组
*/
public class FindOrder {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        // HashSet 作为邻接矩阵
        HashSet<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
        }

        int[] mark = new int[numCourses]; // 标记数组(-1为已经访问过，1为正在访问，0为未访问)

        Stack<Integer> stack = new Stack<>(); // 结果栈
        for (int i = 0; i < numCourses; i++) {
            if (isCycle(graph, mark, i, stack)) return new int[0];
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    private static boolean isCycle(HashSet<Integer>[] graph, int[] mark, int i, Stack<Integer> stack) {
        if (mark[i] == -1) return false;//该节点已经被访问过
        if (mark[i] == 1) return true;//该节点正在被访问，说明是个圈

        mark[i] = 1;
        //遍历当前节点的所有可达节点
        for (int neighbor : graph[i]) {
            if (isCycle(graph, mark, neighbor, stack)) return true;
        }
        mark[i] = -1;
        stack.push(i);//将没有可达节点或者可达节点都已经访问过的节点，先添加到stack中，后学习，没有可达节点，说明该节点课程不是其他课程的依赖，但可能自身有依赖所以后学习
        return false;
    }

    public static void main(String[] args) {
        int numCourses = 8;
        int[][] prerequisites = new int[][]{{4, 6}, {0, 6}, {2, 0}, {3, 2}, {1, 3}, {3, 7}, {7, 5}, {2, 5}};
        int[] res = findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(res));
    }
}
