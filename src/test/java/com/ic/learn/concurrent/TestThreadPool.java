package com.ic.learn.concurrent;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestThreadPool {
    private final int MAX_URLS = 10;
    static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1000);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ThreadPoolConfig.class);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) context.getBean("threadPoolTaskExecutor");
        threadPoolTaskExecutor.execute(new Producer(200));
        Future<String> future = threadPoolTaskExecutor.submit(new Consumer2());
        System.out.println(future.get());
        new Thread(() -> System.out.println("1123132")).start();
//        while (true) {
//            threadPoolTaskExecutor.execute(new Consumer());
//        }

    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("当前线程是： " + Thread.currentThread().getName() + " 线程状态是： " + Thread.currentThread().getState() + " 正在消费： " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer2 implements Callable {
        @Override
        public String call() throws Exception {
            return "hello world";
        }
    }

    static class Producer implements Runnable {
        private int n;

        public Producer(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                Random random = new Random();
                for (int j = 0; j < 10; j++) {
                    switch (random.nextInt(3)) {
                        /*ASCII编码共128个字符*/
                        /*65-90是大写字母*/
                        case 1:
                            stringBuilder.append((char) (random.nextInt(26) + 65));
                            /*97-122是小写字母*/
                        case 2:
                            stringBuilder.append((char) (random.nextInt(26) + 97));
                            /*48-57是数字*/
                        case 3:
                            stringBuilder.append((char) (random.nextInt(10) + 48));
                    }
                }
                try {
                    queue.put(stringBuilder.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testExchange() {
        for (int i = 0; i < 256; i++) {
            System.out.println(i + "  " + (char) i);
        }
    }
}
