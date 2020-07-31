package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    BinaryTreeNode root;
    List<BinaryTreeNode> nodes = new ArrayList<>();

    public boolean init(int[] numbers){
        if (numbers == null){
            return false;
        }
        if (numbers.length == 0){
            return false;
        }
        for (int value : numbers){
            insertNewNode(value);
        }
        return true;
    }
    public void insertNewNode(int newValue) {
        BinaryTreeNode newNode = new BinaryTreeNode(newValue);
        nodes.add(newNode);
        if (this.root == null) {
            this.root = newNode;
            return;
        }
        BinaryTreeNode temp = root;
        while (true) {
            if (temp.value >= newValue) {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    temp.left = newNode;
                    return;
                }

            } else {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    temp.right = newNode;
                    return;
                }
            }
        }

    }

    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(BinaryTreeNode nowNode) {
        if (nowNode != null) {
            inOrder(nowNode.left);
            System.out.println(nowNode.value);
            inOrder(nowNode.right);
        }
    }

}
