package com.ic.learn.algorithm.exercise;

import com.ic.userManagement.entity.User;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class MoveNum {
    public static void main(String[] args) {
        for (int i = 0;i<3;i++){
            new Thread(() -> {
                while (true){
                    User u = new User();
                    System.out.println("线程："+Thread.currentThread().getName()+" 输出："+Math.random()*10);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
