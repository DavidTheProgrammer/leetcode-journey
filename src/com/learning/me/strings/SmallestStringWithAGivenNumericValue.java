package com.learning.me.strings;

import java.util.ArrayDeque;
import java.util.HashMap;

// https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
public class SmallestStringWithAGivenNumericValue {
    public static void main(String[] args) {
        // System.out.println(getSmallestString(3, 27));
        System.out.println(getSmallestString(5, 73));
    }

    public static String getSmallestString(int n, int k) {
        var values = new HashMap<Integer, String>();
        for (int i = 1; i <= 26; i++) {
            var letter = Character.toString(i + 96);
            values.put(i, letter);
        }

        var stack = new ArrayDeque<String>();
        for (int i = n; i > 0; i--) {
            var targetValue = k - (i - 1);
            var targetValueKey = Math.min(targetValue, 26);
            var actualValue = values.get(targetValueKey);
            stack.addFirst(actualValue);
            k = k - targetValueKey;
        }

        var sb = new StringBuilder("");
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }

        return sb.toString();
    }
}
