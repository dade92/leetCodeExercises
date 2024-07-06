package com.myprojects.trees;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeTest {


    @Test
    void inOrderTraversal() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(9);
        root.right.right = new TreeNode(11);

        assertEquals(Utils.inOrderTraversal(root), Arrays.asList(9, 3, 5, 8, 11));
    }

    @Test
    void preOrderTraversal() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(9);
        root.right.right = new TreeNode(11);

        assertEquals(Utils.preOrderTraversal(root), Arrays.asList(5, 3, 9, 8, 11));
    }

    @Test
    void insert() {
        TreeNode root = null;

        root = Utils.insert(root, 5);
        root = Utils.insert(root, 3);
        root = Utils.insert(root, 1);

    }

    private void checkTreeStatus(TreeNode root, int[] expectedInOrderStatus) {

    }
}
