package com.learning.me.strings;

import java.util.*;

// https://leetcode.com/problems/word-ladder-ii/
public class WordLadderII {
    static class Node {
        Node parent;
        String word;

        public Node(Node parent, String word) {
            this.parent = parent;
            this.word = word;
        }
    }

    public static void main(String[] args) {
        var start = "hit";
        var end = "cog";
        var list = new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};

        System.out.println(findLadders(start, end, list));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        var result = new ArrayList<List<String>>();
        var wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord);
        if (!wordSet.contains(endWord)) {
            return result;
        }

        var q = new ArrayDeque<Node>();
        q.offerLast(new Node(null, endWord));
        wordSet.remove(endWord);

        var validNodes = new ArrayList<Node>();
        while (!q.isEmpty() && validNodes.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var current = q.pollFirst();
                if (differsByOne(current.word, beginWord)) {
                    validNodes.add(current);
                }

                for (String next : wordSet) {
                    var temp = current;
                    var isNotInPath = true;
                    while (temp != null) {
                        if (temp.word.equals(next)) {
                            isNotInPath = false;
                            break;
                        }

                        temp = temp.parent;
                    }

                    if (isNotInPath && differsByOne(current.word, next)) {
                        q.offerLast(new Node(current, next));
                    }
                }
            }
        }

        for (Node node : validNodes) {
            var list = new LinkedList<String>();
            list.offerFirst(beginWord);
            Node temp = node;
            while (temp != null) {
                list.offerLast(temp.word);
                temp = temp.parent;
            }

            result.add(list);
        }

        return result;
    }

    private static boolean differsByOne(String s1, String s2) {
        var flipped = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (flipped) {
                    return false;
                } else {
                    flipped = true;
                }
            }
        }

        return true;
    }
}
