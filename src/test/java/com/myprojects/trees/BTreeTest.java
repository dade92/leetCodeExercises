package com.myprojects.trees;

import com.myprojects.lists.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BTreeTest {

    private BTree<Integer> bTree;

    @BeforeEach
    void setUp() {
        bTree = new BTree<>(2, 5, 10, 8, 4);
    }

    @Test
    void traverseDFS() {
        assertEquals(
            new List<>(4, 5, 8, 10),
            bTree.traverseDFS()
        );
    }

    @Test
    void traverseBFS() {
        assertEquals(
            new List<>(8, 4, 5, 10),
            bTree.traverseBFS()
        );
    }

    @Test
    void insertsCorrectly() {
        bTree.insert(11);

        assertEquals(
            new List<>(4, 5, 8, 10, 11),
            bTree.traverseDFS()
        );
    }

    @Test
    void searchAnElement() {
        assertNotNull(bTree.search(8));
        assertNull(bTree.search(7));
    }
}