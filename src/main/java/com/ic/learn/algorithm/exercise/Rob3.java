package com.ic.learn.algorithm.exercise;


import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Rob3 {
    public int rob(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }
/*我们换一种办法来定义此问题

每个节点可选择偷或者不偷两种状态，根据题目意思，相连节点不能一起偷

当前节点选择偷时，那么两个孩子节点就不能选择偷了
当前节点选择不偷时，两个孩子节点只需要拿最多的钱出来就行(两个孩子节点偷不偷没关系)
我们使用一个大小为 2 的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷

任何一个节点能偷到的最大钱的状态可以定义为
当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
*/
    @Test
    public void test() {
        int[] data = new int[]{3, 2, 3, -1, 3, -1, 1};
        TreeNode root = new TreeNode(data[0]);
        TreeNode cur = root;
        Queue<TreeNode> queue = new LinkedList<>();
        int n = 2;
        int i = 1;
        while (i < data.length) {
            if (data[i] == -1) {
                n--;
                i++;
            }
            if (n != 0) {
                assert cur != null;
                if (i % 2 == 1) {
                    cur.left = new TreeNode(data[i]);
                    queue.add(cur.left);
                } else {
                    cur.right = new TreeNode(data[i]);
                    queue.add(cur.right);
                }
                n--;
            }
            if (n == 0) {
                cur = queue.poll();
                n = 2;
            }
            i++;
        }
        System.out.println(rob(root));
    }
}
