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
        ArrayList<Integer> result = new ArrayList<>();
        if (root != null) {
            result.addAll(inOrderTraversal(root.left));
            result.add(root.val);
            result.addAll(inOrderTraversal(root.right));
        }
        return result;
    }

    public static ArrayList<Integer> preOrderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root != null) {
            result.add(root.val);
            result.addAll(preOrderTraversal(root.left));
            result.addAll(preOrderTraversal(root.right));
        }
        return result;
    }

    public static ArrayList<Integer> postOrderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root != null) {
            result.addAll(postOrderTraversal(root.left));
            result.addAll(postOrderTraversal(root.right));
            result.add(root.val);
        }
        return result;
    }

    public static TreeNode search(TreeNode root, int val) {
        if(root == null || root.val == val) {
            return root;
        } else {
            if(val < root.val) {
                return search(root.left, val);
            } else {
                return search(root.right, val);
            }
        }
    }

}
