package com.myprojects.bitsets;

import java.util.StringJoiner;

public class BitSet {
    private final int[] bitArray;
    private final int size;

    public BitSet(int size) {
        this.size = size;
        bitArray = new int[(size + 31) / 32];
    }

    public void set(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int arrayIndex = index / 32;
        int bitPosition = index % 32;
        bitArray[arrayIndex] |= (1 << bitPosition);
    }

    public boolean get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int arrayIndex = index / 32;
        int bitPosition = index % 32;
        return (bitArray[arrayIndex] & (1 << bitPosition)) != 0;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("", "[", "]");
        for (int i = size - 1; i >= 0; i--) {
            sj.add(get(i) ? "1" : "0");
        }
        return sj.toString();
    }
}

