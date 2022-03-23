package com.learning.me.greedy;

// https://leetcode.com/problems/broken-calculator/
public class BrokenCalculator {
    public static void main(String[] args) {
        System.out.println(brokenCalc(2,3));
        System.out.println(brokenCalc(5,8));
        System.out.println(brokenCalc(3,10));
        System.out.println(brokenCalc(1,1000000000));
    }

    // 2, 10
    private static int brokenCalc(int startValue, int target) {
        int stepCount = 0;
        while (target > startValue) {
            if (target % 2 == 0) {
                target /= 2;
            } else {
                target++;
            }

            stepCount++;
        }


        // We get the difference because that's how many additions it'll take to get to the start value
        return stepCount + (startValue - target);
    }
}
