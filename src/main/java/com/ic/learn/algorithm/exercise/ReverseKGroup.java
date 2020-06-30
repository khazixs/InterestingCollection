package com.ic.learn.algorithm.exercise;

import java.util.Stack;

public class ReverseKGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        boolean getResultHead = false;
        ListNode oldLast = new ListNode(-1);
        do {
            ListNode last = first;
            for (int i = 0; i < k - 1; i++) {
                if (first.next != null) {
                    first = first.next;
                } else {
                    return head;
                }
            }
            if (!getResultHead) {
                head = first;
                getResultHead = true;
            }
            ListNode exchange1 = last;
            ListNode exchange2 = last.next;
            ListNode temp;
            for (int i = 0; i < k - 1; i++) {
                temp = exchange2.next;
                exchange2.next = exchange1;
                last.next = temp;
                exchange1 = exchange2;
                exchange2 = last.next;
            }
            first = exchange2;
            oldLast.next = exchange1;
            oldLast = last;
        } while (first != null);
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        ListNode res = reverseKGroup(head, 2);
        while (res != null) {
            System.out.print(res.val);
            System.out.print(" --》 ");
            res = res.next;
        }
    }
}
