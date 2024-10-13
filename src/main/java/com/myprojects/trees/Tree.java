package com.myprojects.trees;

import java.util.List;

public class Tree {

    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public Tree(int... elements) {
        for (int e : elements) {
            insert(e);
        }
    }

    public void insert(int val) {
        root = TreeUtils.insert(root, val);
    }

    public TreeNode search(int val) {
        return TreeUtils.search(root, val);
    }

    public List<Integer> inOrderTraversal() {
        return TreeUtils.inOrderTraversal(root);
    }

    public List<Integer> preOrderTraversal() {
        return TreeUtils.preOrderTraversal(root);
    }

    public List<Integer> postOrderTraversal() {
        return TreeUtils.postOrderTraversal(root);
    }

    public List<Integer> breadthTraversal() {
        return TreeUtils.breadthTraversal(root);
    }

}
