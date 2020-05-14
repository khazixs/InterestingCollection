package com.ic.learn.algorithm.exercise;

import sun.reflect.generics.tree.Tree;

public class MergeTwoLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = null;
        ListNode temp = null;
        while (true) {
            if (l1 != null && l2 != null) {
                if (res == null) {
                    if (l1.val <= l2.val) {
                        res = l1;
                        temp = res;
                        l1 = l1.next;
                    } else {
                        res = l2;
                        temp = res;
                        l2 = l2.next;
                    }
                } else {
                    if (l1.val <= l2.val) {
                        temp.next = l1;
                        l1 = l1.next;
                    } else {
                        temp.next = l2;
                        l2 = l2.next;
                    }
                    temp = temp.next;
                }
            }
            if (l1 == null) {
                temp.next = l2;
                return res;
            } else if (l2 == null) {
                temp.next = l1;
                return res;
            }
        }
    }

    public static void main(String[] args) {
        /*1->2->4, 1->3->4*/
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode res = mergeTwoLists(l1, l2);
        while (res != null) {
            System.out.print(res.val + " —> ");
            res = res.next;
        }
    }
}
