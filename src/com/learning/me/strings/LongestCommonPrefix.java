package com.learning.me.strings;

// https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        var shortestWord = Integer.MAX_VALUE;
        for (String str : strs) {
            shortestWord = Math.min(shortestWord, str.length());
        }

        int commonLetters = 0;
        for (int i = 0; i < shortestWord; i++) {
            var allMatch = true;
            char letterToCompare = strs[0].charAt(i);

            for(int j = 1; j < strs.length; j++) {
                if(strs[j].charAt(i) != letterToCompare) {
                    allMatch = false;
                    break;
                }

            }

            if(allMatch) {
                commonLetters++;
            } else {
                break;
            }
        }

        return commonLetters == 0 ? "" : strs[0].substring(0, commonLetters);
    }
}
