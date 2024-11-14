package com.myprojects.bloomfilter;

import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter<T> {
    private final BitSet bitSet;
    private final int size;
    private final int hashCount;
    private final Function<T, Integer> hashFunction1;
    private final Function<T, Integer> hashFunction2;

    public BloomFilter(
        int size,
        int hashCount,
        Function<T, Integer> hashFunction1,
        Function<T, Integer> hashFunction2
    ) {
        this.size = size;
        this.hashCount = hashCount;
        this.hashFunction1 = hashFunction1;
        this.hashFunction2 = hashFunction2;
        this.bitSet = new BitSet(size);
    }

    public void add(T element) {
        for (int i = 0; i < hashCount; i++) {
            int combinedHash = computeHash(element, i);
            bitSet.set(Math.abs(combinedHash % size));
        }
    }

    public boolean mightContain(T element) {
        for (int i = 0; i < hashCount; i++) {
            int combinedHash = computeHash(element, i);
            if (!bitSet.get(Math.abs(combinedHash % size))) {
                return false;
            }
        }
        return true;
    }

    private int computeHash(T element, int i) {
        return hashFunction1.apply(element) + i * hashFunction2.apply(element);
    }
}

