package com.myprojects.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public final class TreeUtils {

    public static <T extends Comparable<T>> TreeNode<T> insert(TreeNode<T> root, T val) {
        if (root == null) {
            root = new TreeNode<>(val);
        } else {
            if (val.compareTo(root.val) < 0) {
                root.left = insert(root.left, val);
            } else {
                root.right = insert(root.right, val);
            }
        }
        return root;
    }

    public static <T extends Comparable<T>> TreeNode<T> search(TreeNode<T> root, T val) {
        if (root == null || root.val == val) {
            return root;
        } else {
            if (val.compareTo(root.val) < 0) {
                return search(root.left, val);
            } else {
                return search(root.right, val);
            }
        }
    }

    public static <T extends Comparable<T>> TreeNode<T> invertTree(TreeNode<T> root) {
        if (root != null) {
            TreeNode<T> temp = invertTree(root.left);
            root.left = invertTree(root.right);
            root.right = temp;
        }
        return root;
    }

    public static <T extends Comparable<T>> ArrayList<T> inOrderTraversal(TreeNode<T> root) {
        ArrayList<T> result = new ArrayList<>();
        if (root != null) {
            result.addAll(inOrderTraversal(root.left));
            result.add(root.val);
            result.addAll(inOrderTraversal(root.right));
        }
        return result;
    }

    public static <T extends Comparable<T>> ArrayList<T> preOrderTraversal(TreeNode<T> root) {
        ArrayList<T> result = new ArrayList<>();
        if (root != null) {
            result.add(root.val);
            result.addAll(preOrderTraversal(root.left));
            result.addAll(preOrderTraversal(root.right));
        }
        return result;
    }

    public static <T extends Comparable<T>> ArrayList<T> postOrderTraversal(TreeNode<T> root) {
        ArrayList<T> result = new ArrayList<>();
        if (root != null) {
            result.addAll(postOrderTraversal(root.left));
            result.addAll(postOrderTraversal(root.right));
            result.add(root.val);
        }
        return result;
    }

    public static <T extends Comparable<T>> ArrayList<T> breadthTraversal(TreeNode<T> root) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        ArrayList<T> output = new ArrayList<>();

        if (root == null) {
            return output;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> treeNode = queue.poll();
            output.add(treeNode.val);

            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }

        }

        return output;
    }

}
