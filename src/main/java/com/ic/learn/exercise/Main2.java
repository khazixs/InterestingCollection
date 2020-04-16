package com.ic.learn.exercise;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/*
*
5 2 3 1 0 0
1 20 2 3
2 30 3 4 5
3 50 4
4 60
5 80

* */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        List<ArrayBlockingQueue<Integer>> list = new ArrayList<>();
        int[] funInfo = new int[101];
        while (scanner.hasNextLine()){
            String[] strings = scanner.nextLine().split(" ");
//            System.out.println(Arrays.toString(strings));
            if (strings.length>1){
                ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(100);
                queue.add(Integer.parseInt(strings[0]));
                funInfo[Integer.parseInt(strings[0])] = Integer.parseInt(strings[1]);
                for (int i = 2;i<strings.length;i++){
                    queue.add(Integer.parseInt(strings[i]));
                }
                list.add(queue);
            }else{
                System.out.println("NA");
            }
        }
        int maxCost = 0;
        for (ArrayBlockingQueue<Integer> integers : list) {
            int re = compute(integers, funInfo);
            if (re == -2) {
                System.out.println("R");
                return;
            }
            if (re > maxCost) {
                maxCost = re;
            }
        }
        System.out.println(maxCost);
    }

    private static int compute(ArrayBlockingQueue<Integer> queue,int[] costs) throws InterruptedException {
        int result = 0;
        boolean[] visited = new boolean[costs.length];
        while(!queue.isEmpty()){
            if (!visited[queue.peek()]) {
                visited[queue.peek()] = true;
                result += costs[queue.poll()];
            }else{
                return -2;
            }
        }
        return result;
    }
}
