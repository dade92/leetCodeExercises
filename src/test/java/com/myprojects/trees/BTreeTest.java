package com.myprojects.trees;

import com.myprojects.lists.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Integer searched = 8;
        BTreeNode<Integer> expected = new BTreeNode<>(3, true);
        expected.keys[0] = searched;

        assertEquals(
            expected,
            bTree.search(searched)
        );
    }
}