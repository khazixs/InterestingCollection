package com.ic.learn.algorithm.exercise;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(6);

        ListNode l2 = new ListNode(0);
        ListNode res = addTwoNumbers(l1,l2);
        while(res!=null){
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = new ListNode(l1.val + l2.val);
        ListNode temp = res;
        boolean flag1 = true;
        boolean flag2 = true;
        while (true) {
            if (l1!=null){
                if (l1.next == null) {
                    flag1 = false;
                }
            }
            if (l2!=null){
                if (l2.next == null) {
                    flag2 = false;
                }
            }
            if (!flag1 && !flag2) {
                break;
            }
            if (flag1 && flag2) {
                temp.next = new ListNode(l1.next.val + l2.next.val);
            } else if (!flag2) {
                temp.next = new ListNode(l1.next.val);
            } else {
                temp.next = new ListNode(l2.next.val);
            }
            temp = temp.next;
            if (l1!=null){
                l1 = l1.next;
            }
            if (l2!=null){
                l2 = l2.next;
            }

        }
        temp = res;
        boolean up = false;
        while (temp!= null) {
            if (up) {
                temp.val = temp.val + 1;
            }
            if (temp.val < 10) {
                up = false;
            } else {
                temp.val = temp.val - 10;
                up = true;
            }
            if (temp.next==null&&up){
                temp.next = new ListNode(1);
                break;
            }
            temp = temp.next;
        }
        return res;
    }
}
