package com.learning.me.traversals.bst_basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/930/
public class PostOrderTraversal {
    public static void main(String[] args) {
        var _3 = new TreeNode(3);
        var _2 = new TreeNode(2, _3, null);
        var _1 = new TreeNode(1, null, _2);

        System.out.println(postorderTraversal(_1));
        System.out.println(iterative(_1));
    }

    // Recursive
    private static List<Integer> postorderTraversal(TreeNode root) {
        var result = new ArrayList<Integer>();

        recursive(result, root);
        return result;
    }

    private static void recursive(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }

        recursive(result, node.left);
        recursive(result, node.right);
        result.add(node.val);
    }

    private static List<Integer> iterative(TreeNode root) {
        var result = new ArrayList<Integer>();
        var stack = new ArrayDeque<TreeNode>();

        var temp = root;
        while (true) {
            if (temp != null) {
                stack.addFirst(temp);
                temp = temp.left;
            } else {
                if (stack.isEmpty()) {
                    return result;
                }

                // This means LS is processed and now right should be processed
                if (stack.peekFirst().right == null) {
                    temp = stack.pollFirst();
                    result.add(temp.val);

                    // While the RS is being processed and there are no left nodes
                    // Keep popping off the stack and adding to result set.
                    // This condition checks if the value that's just been added to the result set
                    // is the right child of the value currently at the top of the stack,
                    // if so, pop it as we assume the LS has already been processed
                    while (stack.peekFirst() != null && temp.equals(stack.peekFirst().right)) {
                        temp = stack.pollFirst();
                        result.add(temp.val);
                    }
                }

                if (stack.isEmpty()) {
                    return result;
                }

                temp = stack.peekFirst().right;
            }
        }
    }
}
