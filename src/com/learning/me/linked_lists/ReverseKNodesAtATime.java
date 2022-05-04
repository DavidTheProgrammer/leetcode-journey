package com.learning.me.linked_lists;

// https://leetcode.com/problems/reverse-nodes-in-k-group/
public class ReverseKNodesAtATime {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {this.val = val;}

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        var _5 = new ListNode(5);
        var _4 = new ListNode(4, _5);
        var _3 = new ListNode(3, _4);
        var _2 = new ListNode(2, _3);
        var _1 = new ListNode(1, _2);

        reverseKGroup(_1, 2);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        int count = 1;
        ListNode end = head;
        while (count < k && end != null) {
            end = end.next;
            count++;
        }

        // Return the list unchanged
        if (count < k || end == null) {
            return head;
        }

        // Store the next value
        var nextNode = end.next;

        // Reverse the list from start to end;
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        while (prev != end) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head.next = reverseKGroup(nextNode, k);
        return end;
    }
}
