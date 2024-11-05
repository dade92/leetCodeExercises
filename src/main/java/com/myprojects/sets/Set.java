package com.myprojects.sets;

import com.myprojects.hashtables.HashTable;

import java.util.Objects;
import java.util.StringJoiner;

public class Set<V> {

    private final HashTable<V, Boolean> hashTable;

    public Set() {
        hashTable = new HashTable<>();
    }

    public Set(V... elements) {
        this();
        for (V element : elements) {
            hashTable.put(element, true);
        }
    }

    public void add(V value) {
        hashTable.put(value, true);
    }

    public void addAll(Set<V> elements) {
        for (V value : elements.toArray()) {
            hashTable.put(value, true);
        }
    }

    public void remove(V value) {
        hashTable.remove(value);
    }

    public boolean contains(V value) {
        return hashTable.get(value) != null;
    }

    public boolean isEmpty() {
        return hashTable.size() == 0;
    }

    public int size() {
        return hashTable.size();
    }

    public V[] toArray() {
        return hashTable.keys();
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[ ", " ]");

        for (V value : hashTable.keys()) {
            sj.add(value.toString());
        }

        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<?> set = (Set<?>) o;
        return Objects.equals(hashTable, set.hashTable);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hashTable);
    }
}
