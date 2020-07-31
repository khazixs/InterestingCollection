package com.ic.learn.algorithm.exercise;
/*判断链表中是否存在环*/
public class IsCircularLinkedList {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        LinkNode<Integer> template = new LinkNode<>(-1);
        for (int i = 0; i < values.length; i++) {
            myLinkedList.add(values[i]);
            if (i == myLinkedList.length / 2) {
                template = myLinkedList.last;
            }
        }
        myLinkedList.last.next = template;
        System.out.println(isCircularLinkedList(myLinkedList));
    }
    /*用追逐法判断是否链表中是否存在环*/
    private static boolean isCircularLinkedList(MyLinkedList<Integer> linkedList) {
        if (linkedList == null) {
            return false;
        }
        if (linkedList.length == 0 || linkedList.length == 1) {
            return false;
        }
        LinkNode<Integer> one = linkedList.first.next;
        LinkNode<Integer> second = linkedList.first;
        while (true) {
            if (one.next == second || one == second) {
                return true;
            }
            if (one.next != null) {
                if (one.next.next != null) {
                    one = one.next.next;
                } else {
                    return false;
                }
            } else {
                return false;
            }
            second = second.next;
        }

    }
}
