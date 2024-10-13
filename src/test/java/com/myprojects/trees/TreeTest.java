package com.myprojects.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeTest {

    private Tree tree;

    @BeforeEach
    void setUp() {
        tree = new Tree(5, 3, 8, 9, 7);
    }

    @Test
    void inOrderTraversal() {
        assertEquals(
            Arrays.asList(3, 5, 7, 8, 9),
            tree.inOrderTraversal()
        );
    }

    @Test
    void preOrderTraversal() {
        assertEquals(
            Arrays.asList(5, 3, 8, 7, 9),
            tree.preOrderTraversal()
        );
    }

    @Test
    void postOrderTraversal() {
        assertEquals(
            Arrays.asList(3, 7, 9, 8, 5),
            tree.postOrderTraversal()
        );
    }


    @Test
    void breadthTraversal() {
        assertEquals(
            Arrays.asList(5, 3, 8, 7, 9),
            tree.breadthTraversal()
        );
    }

    @Test
    void insert() {
        tree.insert(4);

        assertEquals(
            Arrays.asList(3, 4, 5, 7, 8, 9),
            tree.inOrderTraversal()
        );
    }

    @Test
    void search() {
        int searched = 9;
        TreeNode found = tree.search(searched);

        assertEquals(
            searched,
            found.val
        );
    }
}