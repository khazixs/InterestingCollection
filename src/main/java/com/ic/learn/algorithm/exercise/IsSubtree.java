package com.ic.learn.algorithm.exercise;

import java.util.regex.Pattern;

public class IsSubtree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        help(s,str1);
        help(t,str2);
        return Pattern.compile(str2.toString()).matcher(str1.toString()).find();
    }

    public void help(TreeNode root,StringBuilder str) {
        if (root==null){
            str.append("0");
            return;
        }
        str.append(root.val);
        help(root.left,str);
        help(root.right,str);
    }

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        add(s);
        System.out.println(s.toString());
    }
    public static void add(StringBuilder s){
        s.append(123);
    }
}
