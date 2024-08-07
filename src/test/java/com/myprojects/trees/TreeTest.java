package com.myprojects.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TreeTest {

    TreeNode root;

    @BeforeEach
    void setUp() {
        root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
    }

    @Test
    void inOrderTraversal() {
        assertEquals(Arrays.asList(9, 3, 5, 8, 11), Utils.inOrderTraversal(root));
    }

    @Test
    void preOrderTraversal() {
        assertEquals(Arrays.asList(5, 3, 9, 8, 11), Utils.preOrderTraversal(root));
    }

    @Test
    void postOrderTraversal() {
        assertEquals(Arrays.asList(9, 3, 11, 8, 5), Utils.postOrderTraversal(root));
    }

    @Test
    void breadthTraversal() {
        assertEquals(Arrays.asList(5, 3, 8, 9, 11), Utils.breadthTraversal(root));
    }

    @Test
    void search() {
        TreeNode toBeSearched = new TreeNode(8);

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = toBeSearched;
        root.left.left = new TreeNode(9);
        root.right.right = new TreeNode(11);

        assertEquals(toBeSearched, Utils.search(root, 8));
        assertNull(Utils.search(root, 66));
    }

    @Test
    void insert() {
        TreeNode root = null;

        root = Utils.insert(root, 5);
        root = Utils.insert(root, 3);
        root = Utils.insert(root, 7);

        checkTreeInBreadth(root, Arrays.asList(5, 3, 7));
    }

    @Test
    void invertTree() {
        root = Utils.invertTree(root);

        checkTreeInBreadth(root, Arrays.asList(5, 8, 3, 11, 9));
    }

    private void checkTreeInBreadth(TreeNode root, List<Integer> expected) {
        assertEquals(expected, Utils.breadthTraversal(root));
    }
}
