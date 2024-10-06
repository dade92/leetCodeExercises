package com.myprojects.tries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriesTest {

    Trie trie = new Trie();

    @Test
    void search() {
        trie.insert("apple");
        trie.insert("banana");

        assertTrue(trie.search("apple"));
        assertTrue(trie.search("banana"));
        assertFalse(trie.search("banan"));
        assertFalse(trie.search("app"));
    }
}
