package com.myprojects.hashtables;

import java.util.Objects;

public class HashTableNode<K, V> {

    public K key;
    public V value;

    public HashTableNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTableNode<?, ?> that = (HashTableNode<?, ?>) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}