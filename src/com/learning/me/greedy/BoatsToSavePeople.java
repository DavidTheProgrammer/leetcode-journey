package com.learning.me.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

// https://leetcode.com/problems/boats-to-save-people/
public class BoatsToSavePeople {
    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{8,2,3,6,2,6}, 8));
    }

    public static int numRescueBoats(int[] people, int limit) {
        int numberOfBoats = 0;
        Arrays.sort(people);

        int i = 0;
        int j = people.length - 1;

        while (i <= j) {
            numberOfBoats++;
            if (people[j] + people[j] <= limit) {
                i++;
            }

            j--;
        }

        return  numberOfBoats;
    }
}
