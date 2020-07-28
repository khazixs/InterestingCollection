package com.ic.learn.others;

import java.util.LinkedList;
import java.util.concurrent.LinkedTransferQueue;

public class TestLinkedTransferQueue {
    public static void main(String[] args) {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();
        for (int i = 0;i<10000;i++){
            queue.offer(1);
        }
        int a = queue.drainTo(new LinkedList<>());
        System.out.println(a);
    }
}
