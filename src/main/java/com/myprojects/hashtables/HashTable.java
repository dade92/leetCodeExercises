package com.myprojects.hashtables;

import com.myprojects.lists.List;

public class HashTable<K, V> {

    private final double loadFactor;
    private int maxSize;
    private HashTableNode<K, V>[] buckets;
    private int size;

    public HashTable() {
        this(0.75, 11);
    }

    public HashTable(double loadFactor, int maxSize) {
        this.loadFactor = loadFactor;
        this.maxSize = maxSize;
        buckets = new HashTableNode[maxSize];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);

        if (buckets[index] == null) {
            buckets[index] = new HashTableNode<>(key, value);
        } else {
            HashTableNode<K, V> node = buckets[index];

            while (node != null) {
                if (key == node.key) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }

            HashTableNode<K, V> newNode = new HashTableNode<>(key, value);

            newNode.next = buckets[index];
            buckets[index] = newNode;
        }

        size++;

        if ((double) size / maxSize > loadFactor) {
            rehash();
        }
    }

    public V get(K key) {
        int index = getIndex(key);

        HashTableNode<K, V> node = buckets[index];
        while (node != null && node.key != key) {
            node = node.next;
        }
        if (node != null) {
            return node.value;
        } else {
            return null;
        }
    }

    public void remove(K key) {
        int index = getIndex(key);

        HashTableNode<K, V> node = buckets[index];

        while (node != null && node.key != key) {
            node = node.next;
        }
        //TODO for collisions
    }

    public V[] values() {
        List<V> values = new List<>();

        for (HashTableNode<K, V> node : buckets) {
            while (node != null) {
                values.addLast(node.value);
                node = node.next;
            }
        }

        return values.toArray();
    }

    public K[] keys() {
        List<K> keys = new List<>();

        for (HashTableNode<K, V> node : buckets) {
            while (node != null) {
                keys.addLast(node.key);
                node = node.next;
            }
        }

        return keys.toArray();
    }

    public int size() {
        return size;
    }

    private void rehash() {
        maxSize = maxSize * 2;
        HashTableNode<K, V>[] newBucket = new HashTableNode[maxSize];

        for (HashTableNode<K, V> element : buckets) {
            if (element != null) {
                int index = element.hashCode() % maxSize;
                newBucket[index] = element;
            }
        }

        buckets = newBucket;
    }

    private int getIndex(K key) {
        return (key.hashCode() & Integer.MAX_VALUE) % maxSize;
    }
}
