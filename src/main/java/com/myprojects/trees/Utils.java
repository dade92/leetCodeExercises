package com.myprojects.trees;

import java.util.ArrayList;

public final class Utils {

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else {
            if (val < root.val) {
                root.left = insert(root.left, val);
            } else {
                root.right = insert(root.right, val);
            }
        }
        return root;
    }

    public static ArrayList<Integer> inOrderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        } else {
            ArrayList<Integer> traversalOutcome = inOrderTraversal(root.left);
            traversalOutcome.add(root.val);
            traversalOutcome.addAll(inOrderTraversal(root.right));
            return traversalOutcome;
        }
    }

    public static ArrayList<Integer> preOrderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        } else {
            result.add(root.val);
            result.addAll(preOrderTraversal(root.left));
            result.addAll(preOrderTraversal(root.right));
            return result;
        }
    }

}
