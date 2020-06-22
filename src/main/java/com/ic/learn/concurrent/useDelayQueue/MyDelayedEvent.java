package com.ic.learn.concurrent.useDelayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayedEvent implements Delayed {
    /*要执行的任务*/
    private Task task;
    /*延时结束时间*/
    private Long endTime;

    public MyDelayedEvent(Task task, Long endTime) {
        this.task = task;
        this.endTime = endTime;
    }
    /*获取剩余的时间，不为正取出*/
    @Override
    public long getDelay(TimeUnit unit) {
        /*TimeUnit工具类的convert方法支持将unix时间转成任意的单位
        TimeUnit.HOURS.convert(3600, TimeUnit.MINUTES)就是将3600分钟转小时，默认是毫秒单位*/
        return unit.convert(endTime, TimeUnit.NANOSECONDS) - unit.convert(System.currentTimeMillis(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this == o) {
            return 1;
        }
        if (o == null) {
            return -1;
        }
        long diff = this.getDelay(TimeUnit.NANOSECONDS) - getDelay(TimeUnit.NANOSECONDS);
        return diff < 0 ? -1 : (diff == 0 ? 0 : 1);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getEndTime(){
        return endTime;
    }

    public void setEndTime(Long endTime){
        this.endTime = endTime;
    }
}
