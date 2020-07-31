package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.concurrent.Phaser;

public class RunningMen {
    @Test
    public void test() throws InterruptedException {
        Phaser phaser = new Phaser(4);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
                try {
                    threadLocal.set(0);
                    long time = new Double(Math.random() * 3000).longValue();
                    long sum = time;
                    Thread.sleep(time);
                    if (phaser.getPhase()==threadLocal.get()){
                        phaser.arrive();
                        System.out.println(Thread.currentThread().getName() + "到达第一条线,跑了"+sum+"毫秒");
                    }
                    phaser.awaitAdvance(0);
                    threadLocal.set(1);
                    time = new Double(Math.random() * 3000).longValue();
                    sum+=time;
                    Thread.sleep(time);
                    if (phaser.getPhase()==threadLocal.get()){
                        phaser.arrive();
                        System.out.println(Thread.currentThread().getName() + "到达第二条线,跑了"+sum+"毫秒");
                    }
                    phaser.awaitAdvance(1);
                    threadLocal.set(2);
                    time = new Double(Math.random() * 3000).longValue();
                    sum+=time;
                    Thread.sleep(time);
                    if (phaser.getPhase()==threadLocal.get()){
                        phaser.arrive();
                        System.out.println(Thread.currentThread().getName() + "到达终点,跑了"+sum+"毫秒");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        phaser.awaitAdvance(0);
        Thread.sleep(10);
        System.out.println("第一阶段结束，此时总数为"+phaser.getRegisteredParties());
        phaser.arriveAndDeregister();
        phaser.awaitAdvance(1);
        Thread.sleep(10);
        System.out.println("第二阶段结束，此时总数为"+phaser.getRegisteredParties());
        phaser.arriveAndDeregister();
        phaser.awaitAdvance(2);
        Thread.sleep(10);
        System.out.println("比赛结束，此时总数为"+phaser.getRegisteredParties());
    }

    @Test
    public void test2() {
        Phaser phaser = new Phaser(2);
        System.out.println(phaser.getRegisteredParties());
        phaser.arriveAndDeregister();
        System.out.println(phaser.getRegisteredParties());

    }
}
