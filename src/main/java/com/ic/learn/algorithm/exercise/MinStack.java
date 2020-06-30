package com.ic.learn.algorithm.exercise;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
        if (!stack2.isEmpty()) {
            int temp = stack2.peek();
            stack2.push(Math.min(temp, x));
        } else {
            stack2.push(x);
        }
    }

    public void pop() {
        if (stack1.isEmpty() || stack2.isEmpty()) {
            return;
        }
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        if (stack1.isEmpty() || stack2.isEmpty()) {
            return -1;
        } else {
            return stack1.peek();
        }
    }

    public int getMin() {
        if (stack1.isEmpty() || stack2.isEmpty()) {
            return -1;
        } else {
            return stack2.peek();
        }
    }
}
