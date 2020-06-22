package com.ic.learn.concurrent.useDelayQueue;

import com.ic.learn.concurrent.useDelayQueue.MyDelayedEvent;
import com.ic.learn.concurrent.useDelayQueue.MyDelayedService;
import com.ic.learn.concurrent.useDelayQueue.MyDelayedServiceImpl;
import com.ic.learn.concurrent.useDelayQueue.StudentTask;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestMyDelayedService {
    public static void main(String[] args) throws InterruptedException {
        MyDelayedService myDelayedService = new MyDelayedServiceImpl();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                for (int i = 0; i < 2; i++) {
                    myDelayedService.put(new MyDelayedEvent(new StudentTask(i), (new Date()).getTime() + 4000));
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }).start();
        myDelayedService.init();
}
}
