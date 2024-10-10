package com.myprojects.tries;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriesTest {

    Trie trie = new Trie();

    @Test
    void search() {
        trie = new Trie("apple", "banana");

        assertTrue(trie.search("apple"));
        assertTrue(trie.search("banana"));
        assertFalse(trie.search("banan"));
        assertFalse(trie.search("app"));

        System.out.println("Words in the trie:");
        System.out.println(trie.retrieveWords());
    }

    @Test
    void delete() {
        String word = "ciccio";
        String anotherWordWithCommonPrefix = "cicc";

        trie = new Trie(word, anotherWordWithCommonPrefix);
        assertTrue(trie.search(word));

        trie.delete(word);

        assertFalse(trie.search(word));
        assertTrue(trie.search(anotherWordWithCommonPrefix));

        System.out.println("Words in the trie:");
        System.out.println(trie.retrieveWords());
    }

    @Test
    void retrieveWords() {
        trie = new Trie("apple", "app", "banana", "bananas");

        assertEquals(trie.retrieveWords(), Arrays.asList("app", "apple", "banana", "bananas"));

        assertEquals(trie.retrieveWordsWithPrefix("ap"), Arrays.asList("app", "apple"));
    }
}
