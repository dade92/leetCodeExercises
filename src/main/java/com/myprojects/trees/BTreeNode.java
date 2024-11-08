package com.myprojects.trees;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTreeNode<?> bTreeNode = (BTreeNode<?>) o;
        return minDegree == bTreeNode.minDegree && numberOfKeys == bTreeNode.numberOfKeys && leaf == bTreeNode.leaf && Objects.deepEquals(keys, bTreeNode.keys) && Objects.deepEquals(children, bTreeNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(keys), Arrays.hashCode(children), minDegree, numberOfKeys, leaf);
    }

    @Override
    public String toString() {
        return "BTreeNode{" +
            "keys=" + Arrays.toString(keys) +
            ", children=" + Arrays.toString(children) +
            ", minDegree=" + minDegree +
            ", numberOfKeys=" + numberOfKeys +
            ", leaf=" + leaf +
            '}';
    }
}

