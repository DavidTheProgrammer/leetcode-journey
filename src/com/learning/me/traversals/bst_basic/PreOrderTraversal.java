package com.learning.me.traversals.bst_basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/928/
public class PreOrderTraversal {
    public static void main(String[] args) {
        var _3 = new TreeNode(3);
        var _2 = new TreeNode(2, _3, null);
        var _1 = new TreeNode(1, null, _2);

        System.out.println(preorderTraversal(_1));
        System.out.println(iterative(_1));
    }

    // Recursive
    private static List<Integer> preorderTraversal(TreeNode root) {
        var result = new ArrayList<Integer>();

        recursive(result, root);

        return result;
    }

    private static void recursive(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }

        result.add(node.val);
        recursive(result, node.left);
        recursive(result, node.right);
    }

    private static List<Integer> iterative(TreeNode root) {
        var result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        var stack = new ArrayDeque<TreeNode>();
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            var temp = stack.pollFirst();
            result.add(temp.val);
            if (temp.right != null) {
                stack.addFirst(temp.right);
            }
            if (temp.left != null) {
                stack.addFirst(temp.left);
            }
        }

        return result;
    }
}
