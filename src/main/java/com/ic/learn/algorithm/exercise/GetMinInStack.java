package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.Stack;

/*实现一个栈，使得min pop put 函数的时间复杂度都是O（1）*/
public class GetMinInStack {
    public static Stack<Integer> realStack = new Stack<>();
    public static Stack<Integer> assistantStack = new Stack<>();

    public void putData(Integer data){
        realStack.push(data);
        if (assistantStack.empty()){
            assistantStack.push(data);
        }else {
            assistantStack.push(Math.min(data,assistantStack.peek()));
        }
    }
    public Integer minData(){
        if (assistantStack.empty()){
            return -1;
        }
        return assistantStack.peek();
    }

    public Integer popData(){
        if (realStack.empty()){
            return -1;
        }
        assistantStack.pop();
        return realStack.pop();
    }
}
