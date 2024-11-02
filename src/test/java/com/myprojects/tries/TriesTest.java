package com.myprojects.tries;

import com.myprojects.lists.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriesTest {

    Trie trie = new Trie("apple", "app", "banana", "bananas", "pear");

    @Test
    void initCorrectly() {
        assertEquals(trie.retrieveWords(), new List<>("app", "apple", "banana", "bananas", "pear"));
    }

    @Test
    void search() {
        assertTrue(trie.search("apple"));
        assertTrue(trie.search("banana"));
        assertFalse(trie.search("banan"));
        assertFalse(trie.search("apps"));
        assertFalse(trie.search("orange"));
    }

    @Test
    void retrieveWordsFromPrefix() {
        assertEquals(trie.retrieveWordsWithPrefix("ap"), new List<>("app", "apple"));
    }

    @Test
    void delete() {
        assertTrue(trie.search("pear"));

        trie.delete("pear");

        assertFalse(trie.search("pear"));
    }
}
