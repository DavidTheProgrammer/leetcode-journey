package com.learning.me.strings;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/word-ladder/
public class WordLadder {

    public static void main(String[] args) {
        // "hot"
        // "dog"
        //     ["hot","dog","dot"]
        var start = "hot";
        var end = "dog";
        var words = new ArrayList<String>() {{
            add("hot");
            // add("dot");
            add("dog");
            add("dot");
            // add("log");
            // add("cog");
        }};

        System.out.println(ladderLength(start, end, words));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        var wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) {
            return 0;
        }

        wordSet.remove(beginWord);
        wordSet.remove(endWord);
        var q = new ArrayDeque<String>();
        q.offerLast(endWord);

        // BFS and return the level at which we find a word that differs by 1 for the start word.
        int level = 1;
        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var current = q.pollFirst();
                if (differsByOne(current, beginWord)) {
                    return level + 1;
                }

                var iter = wordSet.iterator();
                while (iter.hasNext()) {
                    var next = iter.next();
                    if (differsByOne(current, next)) {
                        q.offerLast(next);
                        iter.remove();
                    }
                }
            }

            level++;
        }


        return 0;
    }

    private static boolean differsByOne(String s1, String s2) {
        boolean first = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }

            if (first) {
                return false;
            } else {
                first = true;
            }
        }

        return true;
    }
}
