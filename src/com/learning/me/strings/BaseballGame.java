package com.learning.me.strings;

import java.util.ArrayDeque;

public class BaseballGame {
    public static void main(String[] args) {
        var input = new String[]{"5", "2", "C", "D", "+"};
        calPoints(input);
    }

    public static int approach2(String[] ops) {
        var stack = new ArrayDeque<Integer>(ops.length);
        for (String op : ops) {
            switch (op) {
                case "+" -> {
                    var p = stack.pollFirst();
                    var pp = stack.peekFirst();
                    stack.addFirst(p);
                    stack.addFirst(p + pp);
                }
                case "D" -> stack.addFirst(2 * stack.peekFirst());
                case "C" -> stack.pollFirst();
                default -> stack.addFirst(Integer.valueOf(op));
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pollFirst();
        }

        return sum;
    }


    public static int calPoints(String[] ops) {
        var results = new int[ops.length];
        var resultPointer = 0;

        for (String op : ops) {
            int newValue;
            switch (op) {
                case "+" -> {
                    newValue = results[resultPointer - 1] + results[resultPointer - 2];
                    results[resultPointer++] = newValue;
                }
                case "D" -> {
                    newValue = 2 * results[resultPointer - 1];
                    results[resultPointer++] = newValue;
                }
                case "C" -> results[--resultPointer] = 0;
                default -> results[resultPointer++] = Integer.parseInt(op);
            }
        }

        var sum = 0;
        for (int i : results) {
            sum += i;
        }

        return sum;
    }
}
