package com.myprojects.tries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Trie {
    public static final int NUM_OF_SYMBOLS = 26;

    private final TrieNode root;
    private static final char OFFSET = 'a';

    public Trie() {
        root = new TrieNode();
    }

    public Trie(String... words) {
        root = new TrieNode();
        for (String word : words) {
            this.insert(word);
        }
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

    public List<String> retrieveWords() {
        List<String> output = new LinkedList<>();
        retrieveWordsStartingFrom(root, new StringBuilder(), output);
        return output;
    }

    public List<String> retrieveWordsWithPrefix(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - OFFSET;
            if (node.children[index] == null) {
                return Collections.emptyList();
            }
            node = node.children[index];
        }

        List<String> output = new LinkedList<>();
        retrieveWordsStartingFrom(node, new StringBuilder(word), output);
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

    private void retrieveWordsStartingFrom(TrieNode node, StringBuilder temp, List<String> output) {
        if (node.isEndOfWord) {
            output.add(temp.toString());
        }

        for (int i = 0; i < NUM_OF_SYMBOLS; i++) {
            if (node.children[i] != null) {
                temp.append((char) (OFFSET + i));
                retrieveWordsStartingFrom(node.children[i], temp, output);
                temp.deleteCharAt(temp.length() - 1);
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

