package com.learning.me.arrays;

import java.util.*;

// https://leetcode.com/problems/top-k-frequent-elements/
public class TopKFrequentElements {
    public static void main(String[] args) {
        var input = new int[]{5, 3, 1, 1, 1, 3, 5, 73, 1};
        var k = 3;
        topKFrequent(input, k);

    }

    public static int[] topKFrequent(int[] nums, int k) {
        var frequency = new HashMap<Integer, Integer>();
        for (int i : nums) {
            frequency.put(i, frequency.getOrDefault(i, 0) + 1);
        }

        HashMap<Integer, List<Integer>> frequencyLists = new HashMap<>();
        PriorityQueue<Integer> frequencyHeap = new PriorityQueue<>();
        for (var entry : frequency.entrySet()) {
            // If the heap is full and the value of count is less than the min item on the heap, skip
            var num = entry.getKey();
            var count = entry.getValue();
            if (frequencyHeap.size() == k && count < frequencyHeap.peek()) {
                continue;
            }

            // If after adding the element the heap size is greater than k, remove the list of nums that occurs count times
            frequencyHeap.add(count);
            if (frequencyHeap.size() > k) {
                frequencyLists.remove(frequencyHeap.poll());
            }

            // Add the current integer to the counts list
            frequencyLists.compute(count, (_count, _countList) -> {
                _countList = _countList == null ? new ArrayList<>() : _countList;
                _countList.add(num);
                return _countList;
            });
        }

        var duplicates = new HashSet<>();
        var resultList = new ArrayList<Integer>(frequencyHeap.size());
        while (resultList.size() < k) {
            var count = frequencyHeap.poll();
            if (duplicates.contains(count)) {
                continue;
            }

            duplicates.add(count);
            var numList = frequencyLists.get(count);
            for (int num : numList) {
                resultList.add(num);
                if (resultList.size() == k) {
                    break;
                }
            }
        }

        var result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}
