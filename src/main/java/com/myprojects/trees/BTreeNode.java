package com.myprojects.trees;

class BTreeNode<T extends Comparable<T>> {
    T[] keys;
    BTreeNode<T>[] children;
    int minDegree;
    int numberOfKeys;
    boolean leaf;

    public BTreeNode(int minDegree, boolean leaf) {
        this.minDegree = minDegree;
        this.leaf = leaf;
        this.numberOfKeys = 0;
        this.children = new BTreeNode[2 * minDegree];
        this.keys = (T[]) new Comparable[2 * minDegree - 1];
    }
}

