package com.myprojects.bitsets;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BitSetTest {

    private final BitSet bitSet = new BitSet(10);

    @Test
    void initializedCorrectly() {
        int counter = 0;
        for (int i : bitSet) {
            assertEquals(i, 0);
            counter++;
        }

        assertEquals(10, counter);
    }

    @Test
    void getASetValue() {
        bitSet.set(5);
        assertTrue(bitSet.get(5));
    }

    @Test
    void setRange() {
        bitSet.set(5, 8);

        assertTrue(bitSet.get(5));
        assertTrue(bitSet.get(6));
        assertTrue(bitSet.get(7));
        assertTrue(bitSet.get(8));

        assertEquals("[0111100000]", bitSet.toString());
    }

    @Test
    void flip() {
        bitSet.set(3);
        assertTrue(bitSet.get(3));

        bitSet.flip(3);
        assertFalse(bitSet.get(3));

        bitSet.flip(2);
        assertTrue(bitSet.get(2));
    }

    @Test
    void iterator() {
        bitSet.set(5);
        bitSet.set(7);
        bitSet.set(0);
        int[] expectedResult = new int[]{0, 0, 1, 0, 1, 0, 0, 0, 0, 1};
        Iterator<Integer> iterator = bitSet.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            assertEquals(expectedResult[index], iterator.next());
            index++;
        }
    }

    @Test
    void iterationWithFor() {
        bitSet.set(5);
        bitSet.set(9);
        bitSet.set(0);
        int[] expectedResult = new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 1};
        int index = 0;

        for (int i : bitSet) {
            assertEquals(i, expectedResult[index]);
            index++;
        }
    }

    @Test
    void stringRepresentation() {
        bitSet.set(3);
        bitSet.set(5);

        assertEquals("[0000101000]", bitSet.toString());
    }

    @Test
    void clear() {
        BitSet another = new BitSet(10);
        another.set(9);
        another.set(8);
        another.set(2);
        another.set(3);

        another.clear();

        for (int i : another) {
            assertEquals(0, i);
        }
    }

    @Test
    void isEmpty() {
        assertTrue(bitSet.isEmpty());
        bitSet.set(5);
        assertFalse(bitSet.isEmpty());
    }

    @Test
    void cardinality() {
        assertEquals(0, bitSet.cardinality());
        bitSet.set(5);
        assertEquals(1, bitSet.cardinality());
        bitSet.set(7);
        assertEquals(2, bitSet.cardinality());
    }

    @Test
    void or() {
        bitSet.set(1);
        bitSet.set(5);
        // 0000100010
        BitSet another = new BitSet(10);
        another.set(5);
        another.set(8);
        // 0100100000
        bitSet.or(another);

        assertEquals("[0100100010]", bitSet.toString());
    }

    @Test
    void xor() {
        bitSet.set(1);
        bitSet.set(5);
        // 0000100010
        BitSet another = new BitSet(10);
        another.set(5);
        another.set(8);
        // 0100100000
        bitSet.xor(another);

        assertEquals("[0100000010]", bitSet.toString());
    }

    @Test
    void and() {
        bitSet.set(1);
        bitSet.set(5);
        // 0000100010
        BitSet another = new BitSet(10);
        another.set(5);
        another.set(8);
        // 0100100000
        bitSet.and(another);

        assertEquals("[0000100000]", bitSet.toString());
    }

    @Test
    void intersects() {
        bitSet.set(1);
        bitSet.set(5);
        // 0000100010
        BitSet another = new BitSet(10);
        another.set(5);
        another.set(8);
        BitSet third = new BitSet(10);

        assertTrue(bitSet.intersects(another));
        assertFalse(bitSet.intersects(third));
    }

    @Test
    void size() {
        assertEquals(10, bitSet.size());
    }

    @Test
    void equality() {
        BitSet anotherBitSet = new BitSet(10);
        anotherBitSet.set(3);
        bitSet.set(3);

        assertEquals(anotherBitSet, bitSet);
    }

    @Test
    void toArray() {
        bitSet.set(0);
        bitSet.set(8);

        assertArrayEquals(
            new int[]{257},
            bitSet.toArray()
        );
    }
}