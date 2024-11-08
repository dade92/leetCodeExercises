package com.myprojects.trees;

import com.myprojects.lists.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTest {

    private BinaryTree<Integer> binaryTree;

    @BeforeEach
    void setUp() {
        binaryTree = new BinaryTree<>(5, 3, 8, 9, 7);
    }

    @Test
    void insert() {
        binaryTree.insert(4);

        assertEquals(
            new List<>(3, 4, 5, 7, 8, 9),
            binaryTree.inOrderTraversal()
        );
    }

    @Test
    void search() {
        int searched = 9;
        BinaryTreeNode<Integer> found = binaryTree.search(searched);

        assertEquals(
            searched,
            found.val
        );
    }

    @Test
    void inOrderTraversal() {
        assertEquals(
            new List<>(3, 5, 7, 8, 9),
            binaryTree.inOrderTraversal()
        );
    }

    @Test
    void preOrderTraversal() {
        assertEquals(
            new List<>(5, 3, 8, 7, 9),
            binaryTree.preOrderTraversal()
        );
    }

    @Test
    void postOrderTraversal() {
        assertEquals(
            new List<>(3, 7, 9, 8, 5),
            binaryTree.postOrderTraversal()
        );
    }

    @Test
    void breadthTraversal() {
        assertEquals(
            new List<>(5, 3, 8, 7, 9),
            binaryTree.breadthTraversal()
        );
    }
}