package com.ic.learn.concurrent.useDelayQueue;

import com.ic.learn.concurrent.useDelayQueue.MyDelayedEvent;

public interface MyDelayedService {
    /*插入任务调度*/
    void put(MyDelayedEvent delayedEvent);
    /*移除任务调度*/
    boolean remove(MyDelayedEvent delayedEvent);
    /*初始化该任务调度*/
    void init();
}
