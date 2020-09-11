package com.ic.learn.others;

import com.ic.userManagement.entity.User;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Circle {
    public static void main(String[] args) throws InterruptedException {
        Queue<User> queue = new LinkedList<>();
        for (int i = 0;i<100000;i++){
            User u = new User();
            if(i%3==0){
                queue.poll();
            }else{
                queue.offer(u);
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
