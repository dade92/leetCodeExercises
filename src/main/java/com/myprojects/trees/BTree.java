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
                BTreeNode<T> newRoot = new BTreeNode<>(minDegree, false);
                newRoot.children[0] = root;
                splitChild(newRoot, 0, root);
                int i = 0;
                if (newRoot.keys[0].compareTo(key) < 0)
                    i++;
                insertNonFull(newRoot.children[i], key);
                root = newRoot;
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

    public void remove(T key) {
        if (root == null) {
            return;
        }

        remove(root, key);

        // If the root has 0 keys, make its first child the new root (if it has any)
        if (root.numberOfKeys == 0) {
            if (root.leaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
    }

    private void remove(BTreeNode<T> node, T key) {
        int idx = findKeyGreaterOrEqualTo(node, key);

        // Case 1: The key is in this node
        if (idx < node.numberOfKeys && node.keys[idx].compareTo(key) == 0) {
            if (node.leaf) {
                removeFromLeaf(node, idx);
            } else {
                removeFromNonLeaf(node, idx);
            }
        } else {
            // Case 3: The key is not in this node, must be in a child subtree
            if (node.leaf) {
                return;
            }

            // Check if the key is in the last child
            boolean flag = (idx == node.numberOfKeys);

            // Ensure the child has at least degree keys
            if (node.children[idx].numberOfKeys < minDegree) {
                fill(node, idx);
            }

            // Recurse to the appropriate child, which may have merged
            if (flag && idx > node.numberOfKeys) {
                remove(node.children[idx - 1], key);
            } else {
                remove(node.children[idx], key);
            }
        }
    }

    // Find the index of the first key greater than or equal to key
    private int findKeyGreaterOrEqualTo(BTreeNode<T> node, T key) {
        int idx = 0;
        while (idx < node.numberOfKeys && node.keys[idx].compareTo(key) < 0) {
            idx++;
        }
        return idx;
    }

    private void removeFromLeaf(BTreeNode node, int idx) {
        for (int i = idx + 1; i < node.numberOfKeys; ++i) {
            node.keys[i - 1] = node.keys[i];
        }
        node.numberOfKeys--;
    }

    private void removeFromNonLeaf(BTreeNode<T> node, int idx) {
        T key = node.keys[idx];

        // Case 2a: The child before the key has at least degree keys
        if (node.children[idx].numberOfKeys >= minDegree) {
            T pred = getPred(node, idx);
            node.keys[idx] = pred;
            remove(node.children[idx], pred);
        }
        // Case 2b: The child after the key has at least minDegree keys
        else if (node.children[idx + 1].numberOfKeys >= minDegree) {
            T succ = getSucc(node, idx);
            node.keys[idx] = succ;
            remove(node.children[idx + 1], succ);
        }
        // Case 2c: Both children have fewer than minDegree keys
        else {
            merge(node, idx);
            remove(node.children[idx], key);
        }
    }

    private T getPred(BTreeNode<T> node, int idx) {
        BTreeNode<T> cur = node.children[idx];
        while (!cur.leaf) {
            cur = cur.children[cur.numberOfKeys];
        }
        return cur.keys[cur.numberOfKeys - 1];
    }

    private T getSucc(BTreeNode<T> node, int idx) {
        BTreeNode<T> cur = node.children[idx + 1];
        while (!cur.leaf) {
            cur = cur.children[0];
        }
        return cur.keys[0];
    }

    private void fill(BTreeNode<T> node, int idx) {
        if (idx != 0 && node.children[idx - 1].numberOfKeys >= minDegree) {
            borrowFromPrev(node, idx);
        } else if (idx != node.numberOfKeys && node.children[idx + 1].numberOfKeys >= minDegree) {
            borrowFromNext(node, idx);
        } else {
            if (idx != node.numberOfKeys) {
                merge(node, idx);
            } else {
                merge(node, idx - 1);
            }
        }
    }

    private void borrowFromPrev(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children[idx];
        BTreeNode<T> sibling = node.children[idx - 1];

        for (int i = child.numberOfKeys - 1; i >= 0; --i) {
            child.keys[i + 1] = child.keys[i];
        }

        if (!child.leaf) {
            for (int i = child.numberOfKeys; i >= 0; --i) {
                child.children[i + 1] = child.children[i];
            }
        }

        child.keys[0] = node.keys[idx - 1];
        if (!node.leaf) {
            child.children[0] = sibling.children[sibling.numberOfKeys];
        }

        node.keys[idx - 1] = sibling.keys[sibling.numberOfKeys - 1];
        child.numberOfKeys += 1;
        sibling.numberOfKeys -= 1;
    }

    private void borrowFromNext(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children[idx];
        BTreeNode<T> sibling = node.children[idx + 1];

        child.keys[child.numberOfKeys] = node.keys[idx];
        if (!child.leaf) {
            child.children[child.numberOfKeys + 1] = sibling.children[0];
        }

        node.keys[idx] = sibling.keys[0];
        for (int i = 1; i < sibling.numberOfKeys; ++i) {
            sibling.keys[i - 1] = sibling.keys[i];
        }

        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.numberOfKeys; ++i) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }

        child.numberOfKeys += 1;
        sibling.numberOfKeys -= 1;
    }

    private void merge(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children[idx];
        BTreeNode<T> sibling = node.children[idx + 1];

        child.keys[minDegree - 1] = node.keys[idx];
        for (int i = 0; i < sibling.numberOfKeys; ++i) {
            child.keys[i + minDegree] = sibling.keys[i];
        }

        if (!child.leaf) {
            for (int i = 0; i <= sibling.numberOfKeys; ++i) {
                child.children[i + minDegree] = sibling.children[i];
            }
        }

        for (int i = idx + 1; i < node.numberOfKeys; ++i) {
            node.keys[i - 1] = node.keys[i];
        }
        for (int i = idx + 2; i <= node.numberOfKeys; ++i) {
            node.children[i - 1] = node.children[i];
        }

        child.numberOfKeys += sibling.numberOfKeys + 1;
        node.numberOfKeys--;
    }

    private BTreeNode<T> search(BTreeNode<T> node, T key) {
        int i = 0;

        while (i < node.numberOfKeys && key.compareTo(node.keys[i]) > 0) {
            i++;
        }
        if (i < node.numberOfKeys && node.keys[i] == key) {
            return node;
        }
        if (node.leaf) {
            return null;
        }

        return search(node.children[i], key);
    }

    private void splitChild(BTreeNode<T> parent, int i, BTreeNode<T> fullChild) {
        BTreeNode<T> newChild = new BTreeNode<>(fullChild.minDegree, fullChild.leaf);
        newChild.numberOfKeys = minDegree - 1;

        for (int j = 0; j < minDegree - 1; j++)
            newChild.keys[j] = fullChild.keys[j + minDegree];
        if (!fullChild.leaf) {
            for (int j = 0; j < minDegree; j++)
                newChild.children[j] = fullChild.children[j + minDegree];
        }
        fullChild.numberOfKeys = minDegree - 1;

        for (int j = parent.numberOfKeys; j >= i + 1; j--)
            parent.children[j + 1] = parent.children[j];
        parent.children[i + 1] = newChild;

        for (int j = parent.numberOfKeys - 1; j >= i; j--)
            parent.keys[j + 1] = parent.keys[j];
        parent.keys[i] = fullChild.keys[minDegree - 1];
        parent.numberOfKeys += 1;
    }

    private void insertNonFull(BTreeNode<T> node, T key) {
        int i = node.numberOfKeys - 1;
        if (node.leaf) {
            while (i >= 0 && node.keys[i].compareTo(key) > 0) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.numberOfKeys += 1;
        } else {
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
