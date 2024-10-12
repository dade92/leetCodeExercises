package com.myprojects.tries;

import static com.myprojects.tries.Trie.NUM_OF_SYMBOLS;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[NUM_OF_SYMBOLS];
        isEndOfWord = false;
    }
}

