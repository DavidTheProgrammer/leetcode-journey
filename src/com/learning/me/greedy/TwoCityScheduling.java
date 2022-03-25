package com.learning.me.greedy;

import java.util.*;

// https://leetcode.com/problems/two-city-scheduling/
public class TwoCityScheduling {
    public static void main(String[] args) {

        // var costs = new int[][]{
        //     new int[]{259,770},
        //     new int[]{448,54},
        //     new int[]{926,667},
        //     new int[]{184,139},
        //     new int[]{840,118},
        //     new int[]{577,469},
        // };

        var costs = new int[][]{
            new int[]{10,20},
            new int[]{30,200},
            new int[]{400,50},
            new int[]{30,20}
        };

        System.out.println(twoCitySchedCost(costs));
    }


    public static int twoCitySchedCost(int[][] costs) {
        int numberOfSelections = costs.length / 2;

        // Sort by savings between A and B;
        var cityAComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                var p1Savings = p1[0] - p1[1];
                var p2Savings = p2[0] - p2[1];
                return p1Savings - p2Savings;
            }
        };

        Arrays.sort(costs, cityAComparator);

        int total = 0;
        int left = 0;
        int right = costs.length - 1;
        while (left < right) {
            total += costs[left++][0];
            total += costs[right--][1];
        }

        return total;
    }
}
