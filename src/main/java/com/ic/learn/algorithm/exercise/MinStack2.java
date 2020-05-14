package com.ic.learn.algorithm.exercise;

public class MinStack2 {
    /*使用链表重塑栈*/
    private static class Node {
        int val;
        int min;
        Node last;

        public Node(int val, int min, Node last) {
            this.val = val;
            this.min = min;
            this.last = last;
        }
    }

    private Node head;

    public void push(int x){
        if (head==null){
            head = new Node(x, x, null);
        }else{
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop(){
        if (head==null){
            return;
        }
        head = head.last;
    }

    public int top(){
        return head.val;
    }

    public int getMin(){
        return head.min;
    }

    public static void main(String[] args) {
        MinStack2 m = new MinStack2();
        m.push(3);
        m.push(4);
        m.push(2);
        m.push(1);
        System.out.println(m.top());
        m.pop();
        m.pop();
        System.out.println(m.getMin());
    }
}
