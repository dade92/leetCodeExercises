package com.myprojects.trees;

import com.myprojects.lists.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BinaryTreeUtilsTest {

    BinaryTreeNode<Integer> root;

    @BeforeEach
    void setUp() {
        root = new BinaryTreeNode<>(5);
        root.left = new BinaryTreeNode<>(3);
        root.right = new BinaryTreeNode<>(8);
        root.left.left = new BinaryTreeNode<>(9);
        root.right.right = new BinaryTreeNode<>(11);
    }

    @Test
    void inOrderTraversal() {
        assertEquals(new List<>(9, 3, 5, 8, 11), TreeUtils.inOrderTraversal(root));
    }

    @Test
    void preOrderTraversal() {
        assertEquals(new List<>(5, 3, 9, 8, 11), TreeUtils.preOrderTraversal(root));
    }

    @Test
    void postOrderTraversal() {
        assertEquals(new List<>(9, 3, 11, 8, 5), TreeUtils.postOrderTraversal(root));
    }

    @Test
    void breadthTraversal() {
        assertEquals(new List<>(5, 3, 8, 9, 11), TreeUtils.breadthTraversal(root));
    }

    @Test
    void binarySearch() {
        BinaryTreeNode<Integer> toBeSearched = new BinaryTreeNode<>(8);

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5);
        root.left = new BinaryTreeNode<>(3);
        root.right = toBeSearched;
        root.left.left = new BinaryTreeNode<>(9);
        root.right.right = new BinaryTreeNode<>(11);

        assertEquals(toBeSearched, TreeUtils.binarySearch(root, 8));
        assertNull(TreeUtils.binarySearch(root, 66));
    }

    @Test
    void insert() {
        BinaryTreeNode<Integer> root = null;

        root = TreeUtils.insert(root, 5);
        root = TreeUtils.insert(root, 3);
        root = TreeUtils.insert(root, 7);

        checkTreeInBreadth(root, new List<>(5, 3, 7));
    }

    @Test
    void invertTree() {
        root = TreeUtils.invertTree(root);

        checkTreeInBreadth(root, new List<>(5, 8, 3, 11, 9));
    }

    private void checkTreeInBreadth(BinaryTreeNode<Integer> root, List<Integer> expected) {
        assertEquals(expected, TreeUtils.breadthTraversal(root));
    }
}
