package com.myprojects.trees;

import com.myprojects.lists.List;
import com.myprojects.queues.Queue;

public final class TreeUtils {

    public static <T extends Comparable<T>> BinaryTreeNode<T> insert(BinaryTreeNode<T> root, T val) {
        if (root == null) {
            root = new BinaryTreeNode<>(val);
        } else {
            if (val.compareTo(root.val) < 0) {
                root.left = insert(root.left, val);
            } else {
                root.right = insert(root.right, val);
            }
        }
        return root;
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> binarySearch(BinaryTreeNode<T> root, T val) {
        if (root == null || root.val == val) {
            return root;
        } else {
            if (val.compareTo(root.val) < 0) {
                return binarySearch(root.left, val);
            } else {
                return binarySearch(root.right, val);
            }
        }
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> invertTree(BinaryTreeNode<T> root) {
        if (root != null) {
            BinaryTreeNode<T> temp = invertTree(root.left);
            root.left = invertTree(root.right);
            root.right = temp;
        }
        return root;
    }

    public static <T extends Comparable<T>> List<T> inOrderTraversal(BinaryTreeNode<T> root) {
        List<T> result = new List<>();
        if (root != null) {
            result.addAll(inOrderTraversal(root.left));
            result.addLast(root.val);
            result.addAll(inOrderTraversal(root.right));
        }
        return result;
    }

    public static <T extends Comparable<T>> List<T> preOrderTraversal(BinaryTreeNode<T> root) {
        List<T> result = new List<>();
        if (root != null) {
            result.addLast(root.val);
            result.addAll(preOrderTraversal(root.left));
            result.addAll(preOrderTraversal(root.right));
        }
        return result;
    }

    public static <T extends Comparable<T>> List<T> postOrderTraversal(BinaryTreeNode<T> root) {
        List<T> result = new List<>();
        if (root != null) {
            result.addAll(postOrderTraversal(root.left));
            result.addAll(postOrderTraversal(root.right));
            result.addLast(root.val);
        }
        return result;
    }

    public static <T extends Comparable<T>> List<T> breadthTraversal(BinaryTreeNode<T> root) {
        Queue<BinaryTreeNode<T>> queue = new Queue<>();
        List<T> output = new List<>();

        if (root == null) {
            return output;
        }

        queue.enqueue(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> binaryTreeNode = queue.dequeue();
            output.addLast(binaryTreeNode.val);

            if (binaryTreeNode.left != null) {
                queue.enqueue(binaryTreeNode.left);
            }
            if (binaryTreeNode.right != null) {
                queue.enqueue(binaryTreeNode.right);
            }
        }

        return output;
    }

}
