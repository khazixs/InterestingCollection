package com.ic.learn.algorithm.exercise;

import java.util.Arrays;

public class BuildTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder).val);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MAX_VALUE);
    }

    static int pre = 0;
    static int in = 0;

    public static TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre == preorder.length) return null;
        if (stop == inorder[in]) {
            in++;
            return null;
        }
        int value = preorder[pre++];
        TreeNode node = new TreeNode(value);
        node.left = build(preorder, inorder, value);
        node.right = build(preorder, inorder, stop);
        return node;
    }


}
