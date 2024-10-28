package com.myprojects.hashtables;

import com.myprojects.lists.List;
import org.apache.commons.lang3.tuple.Pair;

import java.util.StringJoiner;

public class HashTable<K, V> {

    private final double loadFactor;
    private int maxSize;
    private HashTableNode<K, V>[] buckets;
    private int size;

    public HashTable() {
        this(0.75, 11);
    }

    public HashTable(Pair<K, V>... pairs) {
        this();
        for (Pair<K, V> pair : pairs) {
            put(pair.getLeft(), pair.getRight());
        }
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

            if (insertInPosition(key, value, node, index)) return;
        }

        size++;

        if ((double) size / maxSize > loadFactor) {
            rehash();
        }
    }

    private boolean insertInPosition(K key, V value, HashTableNode<K, V> node, int index) {
        while (node != null) {
            if (key == node.key) {
                node.value = value;
                return true;
            }
            node = node.next;
        }

        HashTableNode<K, V> newNode = new HashTableNode<>(key, value);

        newNode.next = buckets[index];
        buckets[index] = newNode;
        return false;
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
        HashTableNode<K, V> current = buckets[index];
        HashTableNode<K, V> previous = null;

        while (current != null) {
            if (current.key == key) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                break;
            }
            previous = current;
            current = current.next;
        }
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
                int index = getIndex(element.key);
                //TODO must handle collisions!
                newBucket[index] = element;
            }
        }

        buckets = newBucket;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[", "]");

        for (HashTableNode<K, V> node : buckets) {
            if (node != null) {
                sj.add(node.toString());
            }
        }

        return sj.toString();
    }

    private int getIndex(K key) {
        return (key.hashCode() & Integer.MAX_VALUE) % maxSize;
    }
}
