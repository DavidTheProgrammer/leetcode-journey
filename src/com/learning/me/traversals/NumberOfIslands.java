package com.learning.me.traversals;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1374/
public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
            new char[]{'1', '0', '1', '1', '1'},
            new char[]{'1', '0', '1', '0', '1'},
            new char[]{'1', '1', '1', '0', '1'}
        };

        System.out.println(numIslands(grid));
    }

    // Class to hold coordinates to visit on the grid
    static class Coordinates {
        int i;
        int j;

        public Coordinates(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    // Create queue to check the values around the coordinate
                    Queue<Coordinates> queue = new ArrayDeque<>();
                    queue.add(new Coordinates(i, j));
                    while (!queue.isEmpty()) {
                        bfsQueueBuilder(queue, grid);
                    }
                }
            }
        }

        return count;
    }

    /**
     * Visit the node to the left, right, top and bottom to ensure we mark the continuous block as a single island;
     */
    private static void bfsQueueBuilder(Queue<Coordinates> queue, char[][] grid) {
        var coordinates = queue.poll();
        if (coordinates == null) {
            return;
        }

        int i = coordinates.i;
        int j = coordinates.j;

        // End traversal if it's water
        if (grid[i][j] == '0') {
            return;
        }

        // Mark the coordinate as visited
        grid[i][j] = '0';

        // Only add the left node for traversal if it's within bounds
        var leftIndex = j - 1;
        if (leftIndex >= 0) {
            queue.add(new Coordinates(i, leftIndex));
        }

        // Only add the right node for traversal if it's within bounds
        var rightIndex = j + 1;
        if (rightIndex < grid[i].length) {
            queue.add(new Coordinates(i, rightIndex));
        }

        // Only add the top node if the index is within bounds
        var topIndex = i - 1;
        if (topIndex >= 0) {
            queue.add(new Coordinates(topIndex, j));
        }

        // Only add the bottom node if the index is within bounds
        var belowIndex = i + 1;
        if (belowIndex < grid.length) {
            queue.add(new Coordinates(belowIndex, j));
        }
    }
}
