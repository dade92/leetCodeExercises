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
        int index = (key.hashCode() & Integer.MAX_VALUE) % maxSize;

        buckets[index] = new HashTableNode<>(key, value);
        size++;

        if ((double) size / maxSize > loadFactor) {
            rehash();
        }
    }

    public V get(K key) {
        int index = (key.hashCode() & Integer.MAX_VALUE) % maxSize;

        HashTableNode<K, V> bucket = buckets[index];
        if(bucket != null) {
            return bucket.value;
        } else {
            return null;
        }
    }

    public void remove(K key) {
        int index = (key.hashCode() & Integer.MAX_VALUE) % maxSize;

        buckets[index] = null;
    }

    public V[] values() {
        List<V> values = new List<>();

        for (HashTableNode<K, V> node : buckets) {
            if (node != null) {
                values.addLast(node.value);
            }
        }

        return values.toArray();
    }

    public int size() {
        return size;
    }

    public K[] keys() {
        List<K> keys = new List<>();

        for (HashTableNode<K, V> node : buckets) {
            if (node != null) {

                keys.addLast(node.key);
            }
        }

        return keys.toArray();
    }

    private void rehash() {
        maxSize = maxSize * 2;
        HashTableNode<K, V>[] newBucket = new HashTableNode[maxSize];

        for (HashTableNode<K, V> element : buckets) {
            int index = element.hashCode() % maxSize;

            newBucket[index] = element;
        }

        buckets = newBucket;
    }
}
