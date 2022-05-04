package com.learning.me.arrays;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ReverseToEqual {
    public static void main(String[] args) {
        var a = new int[]{1,2,3,4};
        var b = new int[]{1,4,3,2};
        System.out.println(areTheyEqual(a, b));
    }


    static boolean areTheyEqual(int[] array_a, int[] array_b) {
        // Write your code here
        HashMap<Integer, Integer> arrayACounts = new HashMap<>();
        for (int i : array_a) {
            arrayACounts.compute(i, (key, value) -> value == null ? 1 : ++value);
        }

        for (int i : array_b) {
            if (!arrayACounts.containsKey(i)) {
                return false;
            }

            int counts = arrayACounts.get(i);
            if (counts == 0) {
                return false;
            }

            arrayACounts.computeIfPresent(i, (key, value) -> --value);
        }

        // Check that all keys in the hashMap are 0;
        Collection<Integer> counts = arrayACounts.values();
        for (int count: counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}
