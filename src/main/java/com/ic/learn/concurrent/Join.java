package com.ic.learn.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread joinThread = new Thread(new JoinThread(), "joinThread");
        joinThread.start();
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                joinThread.join();
            }
            System.out.println(Thread.currentThread() + " is running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            TimeUnit.SECONDS.sleep(1);
        }
    }

    static class JoinThread implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " join @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }
}
