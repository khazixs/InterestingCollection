package com.ic.learn.concurrent.useCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {
    static class A implements Runnable{
        @Override
        public void run() {
            System.out.println(3);
        }
    }
    static CyclicBarrier c = new CyclicBarrier(2,new A());//在线程抵达屏障时优先执行线程A，其他两个线程的执行顺序不定
    public static void main(String[] args) {
        new Thread(()->{
            try {
                c.await();
            }catch (BrokenBarrierException | InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();

        try {
            c.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(2);
    }
}
