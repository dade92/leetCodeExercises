package com.myprojects.bitsets;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

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

        for(int i : another) {
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
}