package com.myprojects.tries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Trie {
    private final TrieNode root;
    private static final int NUM_OF_SYMBOLS = 26;
    private static final char OFFSET = 'a';

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - OFFSET;
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - OFFSET;
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    public List<String> printWords() {
        List<String> output = new LinkedList<>();
        innerPrintWords(root, new StringBuilder(), output, "");
        return output;
    }

    public List<String> printWordsStartingFrom(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()) {
            int index = c - OFFSET;
            if (node.children[index] == null) {
                return Collections.emptyList();
            }
            node = node.children[index];
        }

        List<String> output = new LinkedList<>();
        innerPrintWords(node, new StringBuilder(), output, word);
        return output;
    }

    private boolean delete(TrieNode current, String word, int depth) {
        if (depth == word.length()) {
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            return hasNoChildren(current);
        }

        char ch = word.charAt(depth);
        int index = ch - OFFSET;
        TrieNode node = current.children[index];

        if (node == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(node, word, depth + 1);

        if (shouldDeleteCurrentNode) {
            current.children[index] = null;
            return hasNoChildren(current) && !current.isEndOfWord;
        }

        return false;
    }

    private void innerPrintWords(TrieNode node, StringBuilder builder, List<String> output, String prefix) {
        if (node.isEndOfWord) {
            output.add(prefix + builder.toString());
        }

        for (int i = 0; i < NUM_OF_SYMBOLS; i++) {
            if (node.children[i] != null) {
                builder.append((char) ('a' + i));
                innerPrintWords(node.children[i], builder, output, prefix);
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }

    private boolean hasNoChildren(TrieNode node) {
        for (int i = 0; i < NUM_OF_SYMBOLS; i++) {
            if (node.children[i] != null) {
                return false;
            }
        }
        return true;
    }
}

