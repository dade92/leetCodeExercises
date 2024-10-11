package com.myprojects.tries;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode(int numOfSymbols) {
        children = new TrieNode[numOfSymbols];
        isEndOfWord = false;
    }
}

