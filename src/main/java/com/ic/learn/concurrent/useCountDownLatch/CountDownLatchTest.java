package com.ic.learn.concurrent.useCountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    static CountDownLatch c  = new CountDownLatch(2);//想等待几个点完成就传入多少

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println(1);
            c.countDown();
            System.out.println(2);
            c.countDown();
        }).start();
        c.await();//如果计数器不为零，会阻塞当前线程
        System.out.println(3);
    }
}
