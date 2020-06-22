package com.ic.learn.concurrent;

import java.util.concurrent.TimeUnit;

public class TestBoundedQueue {
    static class rmThread implements Runnable {
        BoundedQueue<Integer> boundedQueue;
        Integer time;

        public rmThread(BoundedQueue<Integer> boundedQueue,int time) {
            this.boundedQueue = boundedQueue;
            this.time = time;
        }

        @Override
        public void run() {
            try {
                for ( ; ; ) {
                    Integer x = boundedQueue.remove();
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class addThread implements Runnable {
        BoundedQueue<Integer> boundedQueue;

        public addThread(BoundedQueue<Integer> boundedQueue) {
            this.boundedQueue = boundedQueue;
        }

        @Override
        public void run() {
            int count = 0;
            try {
                for ( ; ; ) {
                    boundedQueue.add(count);
                    count++;
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedQueue<Integer> boundedQueue = new BoundedQueue<>(5);
        Thread add = new Thread(new addThread(boundedQueue), "addThread");
        Thread rm = new Thread(new rmThread(boundedQueue,2), "rmThread1");
        Thread rm2 = new Thread(new rmThread(boundedQueue,2), "rmThread2");
        add.start();
        rm.start();
        rm2.start();

    }
}
