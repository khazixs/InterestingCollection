package com.ic.learn.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Phaser;

public class EventCenter {
    private Queue<Event> queue;
    private GameSystemCallback gameSystemCallback;
    Object wait;
    Phaser phaser = new Phaser();
    public void initRegisterCallback(GameSystemCallback gameSystemCallback){
        gameSystemCallback = new GameSystemCallback();
        queue = new LinkedList<>();
        Object wait = new Object();
    }

    public void notifyEventAndProcess(Event event) throws InterruptedException {
        while (queue.size()>=10000){
            wait.wait();
        }
        notifyAll();
        queue.offer(event);
        while (!queue.isEmpty()){
            gameSystemCallback.processEvent(queue.poll());
        }
    }
}

class GameSystemCallback{
    public void processEvent(Event event){

    }
}
class Event{
    public int type = 0;//event type
    public int data = 0;//event data
}

