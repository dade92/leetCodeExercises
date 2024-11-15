package com.myprojects.bitsets;

import com.myprojects.lists.List;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class BitSet implements Iterable<Integer> {
    private final int[] bitArray;
    private final int size;

    public BitSet(int size) {
        this.size = size;
        bitArray = new int[getWordPosition((size + 31))];
    }

    public void set(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        int arrayIndex = getWordPosition(index);
        int bitPosition = getBitPosition(index);
        bitArray[arrayIndex] |= (1 << bitPosition);
    }

    public boolean get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        int arrayIndex = getWordPosition(index);
        int bitPosition = getBitPosition(index);
        return (bitArray[arrayIndex] & (1 << bitPosition)) != 0;
    }

    public void flip(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        int arrayIndex = getWordPosition(index);
        int bitPosition = getBitPosition(index);
        bitArray[arrayIndex] ^= (1 << bitPosition);
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int index = size - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public Integer next() {
                Integer bit = get(index) ? 1 : 0;
                index--;
                return bit;
            }
        };
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("", "[", "]");
        for (Integer i : this) {
            sj.add(i.toString());
        }
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSet other = (BitSet) o;
        return size == other.size && Objects.deepEquals(bitArray, other.bitArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(bitArray), size);
    }

    private int getBitPosition(int index) {
        return index % 32;
    }

    private int getWordPosition(int index) {
        return index / 32;
    }
}

