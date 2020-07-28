package com.ic.learn.algorithm.exercise;

/*合并两个递增的链表，并且保证合并之后的链表依旧递增*/
public class CombineLinkedList {
    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList1 = new MyLinkedList<>(new Integer[]{1, 3, 4, 7, 8});
        MyLinkedList<Integer> linkedList2 = new MyLinkedList<>(new Integer[]{2, 5, 6, 9, 10});
        combineLinkedList(linkedList1,linkedList2).consoleLog();
    }

    private static MyLinkedList<Integer> combineLinkedList(MyLinkedList<Integer> linkedList1, MyLinkedList<Integer> linkedList2) {
        MyLinkedList<Integer> result = new MyLinkedList<>();
        int flag1, flag2;
        if (linkedList1 == null || linkedList1.length == 0) {
            flag1 = 0;
        } else {
            flag1 = 1;
        }
        if (linkedList2 == null || linkedList2.length == 0) {
            flag2 = 0;
        } else {
            flag2 = 1;
        }
        if (flag1 == 0 && flag2 == 0) {
            return result;
        } else if (flag1 == 0) {
            return linkedList2;
        } else if (flag2 == 0) {
            return linkedList1;
        } else {
            LinkNode<Integer> index1 = linkedList1.first;
            LinkNode<Integer> index2 = linkedList2.first;
            while (index1 != null || index2 != null) {
                if (index1 == null) {
                    result.add(index2.value);
                    index2 = index2.next;
                }
                if (index2 == null && index1 != null) {
                    result.add(index1.value);
                    index1 = index1.next;
                }
                if (index1 != null && index2 != null) {
                    if (index1.value <= index2.value) {
                        result.add(index1.value);
                        index1 = index1.next;
                    } else {
                        result.add(index2.value);
                        index2 = index2.next;
                    }
                }
            }
            return result;
        }
    }
}
