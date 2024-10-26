package com.myprojects.trees;

import com.myprojects.lists.List;

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

    public static <T extends Comparable<T>> List<T> inOrderTraversal(TreeNode<T> root) {
        List<T> result = new List<>();
        if (root != null) {
            result.addAll(inOrderTraversal(root.left));
            result.addLast(root.val);
            result.addAll(inOrderTraversal(root.right));
        }
        return result;
    }

    public static <T extends Comparable<T>> List<T> preOrderTraversal(TreeNode<T> root) {
        List<T> result = new List<>();
        if (root != null) {
            result.addLast(root.val);
            result.addAll(preOrderTraversal(root.left));
            result.addAll(preOrderTraversal(root.right));
        }
        return result;
    }

    public static <T extends Comparable<T>> List<T> postOrderTraversal(TreeNode<T> root) {
        List<T> result = new List<>();
        if (root != null) {
            result.addAll(postOrderTraversal(root.left));
            result.addAll(postOrderTraversal(root.right));
            result.addLast(root.val);
        }
        return result;
    }

    public static <T extends Comparable<T>> List<T> breadthTraversal(TreeNode<T> root) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        List<T> output = new List<>();

        if (root == null) {
            return output;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> treeNode = queue.poll();
            output.addLast(treeNode.val);

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
