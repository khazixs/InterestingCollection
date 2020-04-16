package com.ic.learn.algorithm.aboutTree;

import java.util.Objects;

/*将二叉排序树序列化和反序列化，空的位置置为"$"*/
public class SerializeBinaryTree {
    public static String serialize(BinaryTreeNode root){
        StringBuilder sb = new StringBuilder();
        if (root == null){
            sb.append("$,");
            return sb.toString();
        }
        sb.append(root.value).append(",");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }
    public static int index = -1;
    public static BinaryTreeNode deserialize(String str){
        index++;
        int len  = str.length();
        String[] strings = str.split(",");
        BinaryTreeNode node = null;
        if (index >= len){
            return null;
        }
        if (!strings[index].equals("$")){
            node = new BinaryTreeNode(Integer.parseInt(strings[index]));
            node.left = deserialize(str);
            node.right = deserialize(str);
        }
        return node;
    }

    public static void main(String[] args) {
        int[] numbers = {8, 1, 2, 5, 4, 7, 6, 9, 15, 14, 12, 13, 11, 10, 3};
        BinaryTree binaryTree1 = new BinaryTree();
        binaryTree1.init(numbers);
        System.out.println(binaryTree1.root.value);
        System.out.println(serialize(binaryTree1.root));
        System.out.println(Objects.requireNonNull(deserialize(serialize(binaryTree1.root))).value);


    }
}
