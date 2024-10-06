package com.myprojects.tries;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriesTest {

    Trie trie = new Trie();

    @Test
    void insertAndSearch() {
        trie.insert("apple");
        trie.insert("banana");

        assertTrue(trie.search("apple"));
        assertTrue(trie.search("banana"));
        assertFalse(trie.search("banan"));
        assertFalse(trie.search("app"));

        System.out.println("Words in the trie:");
        System.out.println(trie.printWords());
    }


    @Test
    void delete() {
        String word = "ciccio";
        String anotherWordWithCommonPrefix = "cicc";

        trie.insert(word);
        trie.insert(anotherWordWithCommonPrefix);
        assertTrue(trie.search(word));

        trie.delete(word);

        assertFalse(trie.search(word));
        assertTrue(trie.search(anotherWordWithCommonPrefix));

        System.out.println("Words in the trie:");
        System.out.println(trie.printWords());
    }

    @Test
    void printWords() {
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");
        trie.insert("bananas");

        assertEquals(trie.printWords(), Arrays.asList("app", "apple", "banana", "bananas"));

        assertEquals(trie.suggestFrom("ap"), Arrays.asList("app", "apple"));
    }
}
