package com.ic.learn.algorithm.exercise;

public class MyLinkedList<E> {
    int length;
    LinkNode<E> first;
    LinkNode<E> last;

    public MyLinkedList() {

    }

    public MyLinkedList(E[] values) {
        for (E value : values) {
            add(value);
        }
    }

    public void add(E value) {
        LinkNode<E> anotherNode = new LinkNode<>(value);
        if (first == null) {
            first = anotherNode;
            last = first;
        } else {
            last.next = anotherNode;
            last = last.next;

        }
        length++;
    }

    public void consoleLog(){
        LinkNode<E> template = first;
        while (template.next!=null){
            System.out.print(template.value+"——>");
            template = template.next;
        }
        System.out.print(template.value);
    }
}
