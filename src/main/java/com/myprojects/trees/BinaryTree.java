package com.myprojects.trees;


import com.myprojects.lists.List;

public class BinaryTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(T... elements) {
        for (T e : elements) {
            insert(e);
        }
    }

    public void insert(T val) {
        root = TreeUtils.insert(root, val);
    }

    public TreeNode<T> search(T val) {
        return TreeUtils.search(root, val);
    }

    public List<T> inOrderTraversal() {
        return TreeUtils.inOrderTraversal(root);
    }

    public List<T> preOrderTraversal() {
        return TreeUtils.preOrderTraversal(root);
    }

    public List<T> postOrderTraversal() {
        return TreeUtils.postOrderTraversal(root);
    }

    public List<T> breadthTraversal() {
        return TreeUtils.breadthTraversal(root);
    }

}
