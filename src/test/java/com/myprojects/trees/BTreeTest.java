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
        bTree = new BTree<>(2, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    }

    @Test
    void traverseDFS() {
        assertEquals(
            new List<>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14),
            bTree.traverseDFS()
        );
    }

    @Test
    void traverseBFS() {
        assertEquals(
            new List<>(4, 8, 2, 6, 10, 12, 1, 3, 5, 7, 9, 11, 13, 14),
            bTree.traverseBFS()
        );
    }

    @Test
    void insertsCorrectly() {
        bTree.insert(15);
        assertEquals(
            new List<>(4, 8, 2, 6, 10, 12, 1, 3, 5, 7, 9, 11, 13, 14, 15),
            bTree.traverseBFS()
        );

        bTree.insert(16);
        assertEquals(
            new List<>(4, 8, 2, 6, 10, 12, 14, 1, 3, 5, 7, 9, 11, 13, 15, 16),
            bTree.traverseBFS()
        );

        bTree.insert(17);
        assertEquals(
            new List<>(4, 8, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15, 16, 17),
            bTree.traverseBFS()
        );

        bTree.insert(18);
        assertEquals(
            new List<>(8, 4, 12, 2, 6, 10, 14, 16, 1, 3, 5, 7, 9, 11, 13, 15, 17, 18),
            bTree.traverseBFS()
        );

        bTree.insert(19);
        assertEquals(
            new List<>(8, 4, 12, 2, 6, 10, 14, 16, 1, 3, 5, 7, 9, 11, 13, 15, 17, 18, 19),
            bTree.traverseBFS()
        );

        bTree.insert(20);
        assertEquals(
            new List<>(8, 4, 12, 2, 6, 10, 14, 16, 18, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 20),
            bTree.traverseBFS()
        );
    }

    @Test
    void searchAnElement() {
        // TODO handle with the node reference is not easy here
        assertNotNull(bTree.search(8));
        assertNull(bTree.search(16));
    }

    @Test
    void removesCorrectly() {
        bTree.remove(6);
        assertEquals(
            new List<>(4, 10, 2, 8, 12, 1, 3, 5, 7, 9, 11, 13, 14),
            bTree.traverseBFS()
        );

        bTree.remove(14);
        assertEquals(
            new List<>(4, 2, 8, 10, 12, 1, 3, 5, 7, 9, 11, 13),
            bTree.traverseBFS()
        );

        bTree.remove(13);
        assertEquals(
            new List<>(4, 2, 8, 10, 1, 3, 5, 7, 9, 11, 12),
            bTree.traverseBFS()
        );

        bTree.remove(8);
        assertEquals(
            new List<>(4, 2, 7, 10, 1, 3, 5, 9, 11, 12),
            bTree.traverseBFS()
        );

        bTree.remove(2);
        assertEquals(
            new List<>(7, 4, 10, 1, 3, 5, 9, 11, 12),
            bTree.traverseBFS()
        );

        bTree.remove(10);
        assertEquals(
            new List<>(4, 7, 11, 1, 3, 5, 9, 12),
            bTree.traverseBFS()
        );

        bTree.remove(1);
        assertEquals(
            new List<>(4, 7, 11, 3, 5, 9, 12),
            bTree.traverseBFS()
        );

        bTree.remove(4);
        assertEquals(
            new List<>(7, 11, 3, 5, 9, 12),
            bTree.traverseBFS()
        );

        bTree.remove(9);
        assertEquals(
            new List<>(5, 11, 3, 7, 12),
            bTree.traverseBFS()
        );
    }
}