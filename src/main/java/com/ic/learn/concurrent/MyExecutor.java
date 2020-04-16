package com.ic.learn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor extends Thread {
    private int index;
    public MyExecutor(int index){
        this.index = index;
    }

    @Override
    public void run() {
        try {
            System.out.println("["+index+"] is starting ...");
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println("["+index+"] end ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
