package com.ic.learn.algorithm.exercise;

import java.util.Stack;

public class QueueBaseOnStack {
    private final Stack<Integer> stackIn = new Stack<>();
    private final Stack<Integer> stackOut = new Stack<>();

    public void push(Integer data){
        stackIn.push(data);
    }

    public Integer poll(){
        if (stackOut.isEmpty()){
            if (stackIn.isEmpty()){
                return -1;
            }else{
                while (!stackIn.isEmpty()){
                    Integer t = stackIn.pop();
                    stackOut.push(t);
                }
                return stackOut.pop();
            }
        }else{
            return stackOut.pop();
        }
    }


    public static void main(String[] args) {
        QueueBaseOnStack queueBaseOnStack = new QueueBaseOnStack();
        queueBaseOnStack.push(1);
        queueBaseOnStack.push(2);
        queueBaseOnStack.push(3);
        queueBaseOnStack.push(4);
        System.out.println(queueBaseOnStack.poll());
        System.out.println(queueBaseOnStack.poll());
        System.out.println(queueBaseOnStack.poll());

    }
}
