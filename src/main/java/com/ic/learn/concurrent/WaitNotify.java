package com.ic.learn.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new WaitThread(),"waitThread");
        Thread notifyThread = new Thread(new NotifyThread(),"notifyThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        notifyThread.start();
    }

    static class WaitThread implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    System.out.println(Thread.currentThread() + " flag is true. wait@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false. wait@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class NotifyThread implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock notify@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                   TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock){
                System.out.println(Thread.currentThread() + "hold lock again. sleep@ "+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}