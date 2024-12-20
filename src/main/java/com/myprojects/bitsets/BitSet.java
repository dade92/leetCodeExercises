package com.myprojects.bitsets;

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
        Arrays.fill(bitArray, 0);
    }

    public void set(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        int arrayIndex = getWordPosition(index);
        int bitPosition = getBitPosition(index);
        bitArray[arrayIndex] |= (1 << bitPosition);
    }

    public void set(int from, int to) {
        if (from < 0 || from >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (to < from || to >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        for (int i = from; i <= to; i++) {
            this.set(i);
        }
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

    public void clear() {
        for (int i = 0; i < size; i++) {
            bitArray[getWordPosition(i)] &= 0;
        }
    }

    public boolean isEmpty() {
        for (int i : this) {
            if (i == 1) return false;
        }
        return true;
    }

    public int cardinality() {
        int cardinality = 0;
        for (int i : this) {
            cardinality += i;
        }
        return cardinality;
    }

    public int size() {
        return size;
    }

    public int length() {
        int length = 0;
        int index = 0;
        for (int i : this) {
            if (i == 1) length = index + 1;
            index++;
        }
        return length;
    }

    public void or(BitSet bitSet) {
        for (int i = 0; i < size; i++) {
            int arrayIndex = getWordPosition(i);
            int bitPosition = getBitPosition(i);
            int other = bitSet.get(i) ? 1 : 0;
            bitArray[arrayIndex] |= (other << bitPosition);
        }
    }

    public void xor(BitSet bitSet) {
        for (int i = 0; i < size; i++) {
            int arrayIndex = getWordPosition(i);
            int bitPosition = getBitPosition(i);
            int other = bitSet.get(i) ? 1 : 0;
            bitArray[arrayIndex] ^= (other << bitPosition);
        }
    }

    public void and(BitSet bitSet) {
        for (int i = 0; i < size; i++) {
            int arrayIndex = getWordPosition(i);
            int bitPosition = getBitPosition(i);
            int other = bitSet.get(i) ? 0 : 1;
            bitArray[arrayIndex] &= ~(other << bitPosition);
        }
    }

    public boolean intersects(BitSet bitSet) {
        int index = 0;
        for (int i : this) {
            if (i == 1 && bitSet.get(index)) return true;
            index++;
        }
        return false;
    }

    public int[] toArray() {
        return bitArray;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Integer next() {
                Integer bit = get(index) ? 1 : 0;
                index++;
                return bit;
            }
        };
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("", "[", "]");
        for (int i = size - 1; i >= 0; i--) {
            sj.add(get(i) ? "1" : "0");
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

