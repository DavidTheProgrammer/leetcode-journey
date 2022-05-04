package com.learning.me.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {
    static class ArrComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    }

    public int[][] merge(int[][] intervals) {
        // Sort by starting values
        Arrays.sort(intervals, new ArrComparator());

        // Create final array for results
        var resultList = new ArrayList<List<Integer>>();

        // Create start and end values for each array we'll be creating;
        var start = intervals[0][0];
        var end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end >= intervals[i][0]) {
                if (end <= intervals[i][1]) {
                    end = intervals[i][1];
                }
                continue;
            }

            var arr = new ArrayList<Integer>(2);
            arr.add(start);
            arr.add(end);
            resultList.add(arr);

            start = intervals[i][0];
            end = intervals[i][1];
        }

        // Create the final array and add it to result list
        var lastArr = new ArrayList<Integer>(2);
        lastArr.add(start);
        lastArr.add(end);
        resultList.add(lastArr);

        // Convert list back to arr
        var result = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            var arr = resultList.get(i);
            result[i] = new int[]{arr.get(0), arr.get(1)};
        }

        return result;
    }
}
