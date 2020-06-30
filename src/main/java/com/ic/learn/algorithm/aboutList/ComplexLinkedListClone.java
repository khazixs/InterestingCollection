package com.ic.learn.algorithm.aboutList;

/*实现复杂链表复制*/
class ComplexNode {
    int value;
    ComplexNode next;
    ComplexNode sibling;

    public ComplexNode(int value) {
        this.value = value;
    }
}

public class ComplexLinkedListClone {
    private static ComplexNode cloneValue(ComplexNode head) {
        if (head == null) {
            return null;
        }
        ComplexNode temp = head;
        int length = 0;
        while (temp != null) {
            ComplexNode interNode = new ComplexNode(temp.value);
            interNode.next = temp.next;
            length++;
            temp.next = interNode;
            temp = interNode.next;
        }
        if (length == 1) {
            head.next = null;
            return head;
        }
        return cloneSibling(head);
    }

    private static ComplexNode cloneSibling(ComplexNode head) {
        ComplexNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.sibling != null) {
                temp.next.sibling = temp.sibling.next;
                temp = temp.next.next;
            } else {
                temp = temp.next.next;
            }
        }
        return getClonedLinkedList(head);
    }

    private static ComplexNode getClonedLinkedList(ComplexNode head) {
        ComplexNode newHead = head.next;
        ComplexNode node = head;
        ComplexNode temp = newHead;
        while (temp != null && temp.next != null) {
            node.next = node.next.next;
            temp.next = temp.next.next;
            node = node.next;
            temp = temp.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ComplexNode head = new ComplexNode(1);
        ComplexNode[] nodes = new ComplexNode[5];
        ComplexNode temp = head;
        for (int i = 0; i < 5; i++) {
            nodes[i] = new ComplexNode(i + 2);
            temp.next = nodes[i];
            temp = temp.next;
        }

        head.sibling = nodes[3];
        nodes[2].sibling = nodes[4];
        nodes[4].sibling = nodes[1];
        nodes[3].sibling = nodes[0];

        ComplexNode result = cloneValue(head);
        ComplexNode result2 = cloneValue(new ComplexNode(10101));
        while (result != null) {
            if (result.sibling != null) {
                System.out.println("当前节点是：" + result.value + " 其兄弟节点是： " + result.sibling.value);
            } else {
                System.out.println("当前节点是：" + result.value);
            }
            result = result.next;
        }
        System.out.println("=====================以下是原节点=====================");
        while (head != null) {
            if (head.sibling != null) {
                System.out.println("当前节点是：" + head.value + " 其兄弟节点是： " + head.sibling.value);
            } else {
                System.out.println("当前节点是：" + head.value);
            }
            head = head.next;
        }

    }

}
