package com.ic.learn.algorithm.aboutTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*寻找二叉树中相连节点的和为某一值的这些节点组成的路径*/
public class FindSumPathInTree {
    public static void findPath(BinaryTree binaryTree, int sum) {
        if (binaryTree == null || binaryTree.root == null || sum <= 0) {
            throw new NullPointerException("输入参数不正确");
        }


        for (BinaryTreeNode binaryTreeNode : binaryTree.nodes) {
            List<Integer> list = new ArrayList<>();
            list = isPath(binaryTreeNode, sum, list);
            if (getSum(list) == sum && list.size() > 1) {
                System.out.println(Arrays.toString(list.toArray()));
            }
        }
    }

    private static int getSum(List<Integer> list) {
        int sum = 0;
        if (list != null) {
            for (int a : list) {
                sum += a;
            }
            return sum;
        }
        return -1;
    }

    public static List<Integer> isPath(BinaryTreeNode node, int sum, List<Integer> list) {
        if (node.value == sum) {
            list.add(node.value);
            return list;
        }
        if (node.value < sum) {
            list.add(node.value);
            if (node.left != null) {
                return isPath(node.left, sum - node.value, list);
            }
            if (node.right != null) {
                return isPath(node.right, sum - node.value, list);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        int[] numbers1 = {8, 1, 2, 5, 4, 7, 6, 9, 15, 14, 12, 13, 11, 10, 3};
//        int[] numbers2 = {};
        int[] numbers3 = {1};

        BinaryTree binaryTree1 = new BinaryTree();
        binaryTree1.init(numbers1);
//        BinaryTree binaryTree2 = new BinaryTree();
//        binaryTree2.init(numbers2);
        BinaryTree binaryTree3 = new BinaryTree();
        binaryTree3.init(numbers3);

        findPath(binaryTree1, 33);
        System.out.println("=========================");
//        findPath(binaryTree2, 16);
        findPath(binaryTree3, 16);

    }
}
