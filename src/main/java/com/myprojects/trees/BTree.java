package com.myprojects.trees;

import com.myprojects.lists.List;
import com.myprojects.queues.Queue;

public class BTree<T extends Comparable<T>> {

    private BTreeNode<T> root;
    private final int minDegree;

    public BTree(int minDegree) {
        this.root = null;
        this.minDegree = minDegree;
    }

    public BTree(int minDegree, T... elements) {
        this.root = null;
        this.minDegree = minDegree;
        for (T element : elements) {
            this.insert(element);
        }
    }

    public void insert(T key) {
        if (root == null) {
            root = new BTreeNode<>(minDegree, true);
            root.keys[0] = key;
            root.numberOfKeys = 1;
        } else {
            if (root.numberOfKeys == 2 * minDegree - 1) {
                // New root
                BTreeNode<T> s = new BTreeNode<>(minDegree, false);
                // Make old root as child of new root
                s.children[0] = root;
                // Split the old root and move a key to the new root
                splitChild(s, 0, root);
                // New root has two children, decide where to insert the new key
                int i = 0;
                if (s.keys[0].compareTo(key) < 0)
                    i++;
                insertNonFull(s.children[i], key);
                root = s; // Update root
            } else {
                insertNonFull(root, key);
            }
        }
    }

    public List<T> traverseDFS() {
        List<T> output = new List<>();
        if (root != null) {
            traverseDFS(root, output);
        }
        return output;
    }

    public List<T> traverseBFS() {
        List<T> output = new List<>();

        if (root == null) {
            return output;
        }

        Queue<BTreeNode<T>> queue = new Queue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            BTreeNode<T> node = queue.dequeue();

            for (int i = 0; i < node.numberOfKeys; i++) {
                output.addLast(node.keys[i]);
            }

            if (!node.leaf) {
                for (int i = 0; i <= node.numberOfKeys; i++) {
                    queue.enqueue(node.children[i]);
                }
            }
        }
        return output;
    }

    public BTreeNode<T> search(T key) {
        return search(root, key);
    }

    // Helper function to search in a given node
    private BTreeNode<T> search(BTreeNode<T> node, T key) {
        int i = 0;

        // Find the first key greater than or equal to key
        while (i < node.numberOfKeys && key.compareTo(node.keys[i]) > 0) {
            i++;
        }

        // If the found key is equal to the key we're searching for, return the node
        if (i < node.numberOfKeys && node.keys[i] == key) {
            return node;
        }

        // If the key is not found and this is a leaf node, return null
        if (node.leaf) {
            return null;
        }

        // Otherwise, go to the appropriate child
        return search(node.children[i], key);
    }

    private void splitChild(BTreeNode<T> parent, int i, BTreeNode<T> fullChild) {
        // Create a new node to hold keys and children of fullChild
        BTreeNode<T> newChild = new BTreeNode<>(fullChild.minDegree, fullChild.leaf);
        newChild.numberOfKeys = minDegree - 1;

        // Move keys and children to the new node
        for (int j = 0; j < minDegree - 1; j++)
            newChild.keys[j] = fullChild.keys[j + minDegree];
        if (!fullChild.leaf) {
            for (int j = 0; j < minDegree; j++)
                newChild.children[j] = fullChild.children[j + minDegree];
        }
        fullChild.numberOfKeys = minDegree - 1;

        // Insert new child into the parent node
        for (int j = parent.numberOfKeys; j >= i + 1; j--)
            parent.children[j + 1] = parent.children[j];
        parent.children[i + 1] = newChild;

        // Move middle key up to the parent node
        for (int j = parent.numberOfKeys - 1; j >= i; j--)
            parent.keys[j + 1] = parent.keys[j];
        parent.keys[i] = fullChild.keys[minDegree - 1];
        parent.numberOfKeys += 1;
    }

    private void insertNonFull(BTreeNode<T> node, T key) {
        int i = node.numberOfKeys - 1;
        if (node.leaf) {
            // Find location to insert key and shift keys to make space
            while (i >= 0 && node.keys[i].compareTo(key) > 0) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.numberOfKeys += 1;
        } else {
            // Find the child which is going to have the new key
            while (i >= 0 && node.keys[i].compareTo(key) > 0)
                i--;
            i++;
            if (node.children[i].numberOfKeys == 2 * minDegree - 1) {
                splitChild(node, i, node.children[i]);
                if (node.keys[i].compareTo(key) < 0)
                    i++;
            }
            insertNonFull(node.children[i], key);
        }
    }

    private void traverseDFS(BTreeNode<T> node, List<T> output) {
        int i;
        for (i = 0; i < node.numberOfKeys; i++) {
            if (!node.leaf) {
                traverseDFS(node.children[i], output);
            }

            output.addLast(node.keys[i]);
        }
        if (!node.leaf) {
            traverseDFS(node.children[i], output);
        }
    }

}
