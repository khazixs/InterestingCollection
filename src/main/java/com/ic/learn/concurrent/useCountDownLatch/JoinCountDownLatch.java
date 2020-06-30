package com.ic.learn.concurrent.useCountDownLatch;

public class JoinCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(()->{});
        Thread parser2 = new Thread(()->{
            System.out.println("parser2 finish");
        });
        parser1.start();
        parser2.start();
        /*join,是让当前执行线程等待join线程执行结束。原理是不停检查join线程是否存活，如果存活就让当前线程一直等待下去*/
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }
}
