package com.learning.me.traversals.bst_basic;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/929/
public class InOrderTraversal {
    public static void main(String[] args) {
        var _3 = new TreeNode(3);
        var _2 = new TreeNode(2, _3, null);
        var _1 = new TreeNode(1, null, _2);

        System.out.println(inorderTraversal(_1));
        System.out.println(iterative(_1));
    }

    // Recursive
    private static List<Integer> inorderTraversal(TreeNode root) {
        var result = new ArrayList<Integer>();

        recursive(result, root);
        return result;
    }

    private static void recursive(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }

        recursive(result, node.left);
        result.add(node.val);
        recursive(result, node.right);
    }

    private static List<Integer> iterative(TreeNode root) {
        var result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        var stack = new ArrayDeque<TreeNode>();
        var temp = root;
        while (true) {
            while (temp != null) {
                stack.addFirst(temp);
                temp = temp.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            temp = stack.pollFirst();
            result.add(temp.val);
            temp = temp.right;
        }

        return result;
    }
}
