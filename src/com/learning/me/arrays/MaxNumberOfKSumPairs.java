package com.learning.me.arrays;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/max-number-of-k-sum-pairs/
public class MaxNumberOfKSumPairs {
    public static void main(String[] args) {
        var arr = new int[]{4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4};
        maxOperations(arr, 2);
    }

    public static int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int n : nums) {
            if(n >= k) {
                continue;
            }

            numMap.merge(n, 1, (v, unused) -> v + 1);
        }

        int count = 0;
        Set<Integer> keySet = numMap.keySet();
        for(Iterator<Integer> iterator = keySet.iterator(); iterator.hasNext();) {
            Integer key = iterator.next();
            Integer match = k - key;
            if(!keySet.contains(match)) {
                iterator.remove();
                continue;
            }

            Integer keyCount = numMap.get(key);
            if(key.equals(match)) {
                count += keyCount / 2;
                iterator.remove();
                continue;
            }

            Integer matchCount = numMap.get(match);
            int maxOps = Math.min(keyCount, matchCount);
            count += maxOps;

            iterator.remove();
        }

        return count;
    }
}
