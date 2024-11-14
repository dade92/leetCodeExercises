package com.myprojects.bitsets;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BitSetTest {

    private final BitSet bitSet = new BitSet(10);

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
    }

    @Test
    void iterator() {
        bitSet.set(5);
        bitSet.set(7);
        bitSet.set(0);
        int[] expectedResult = new int[]{0,0,1,0,1,0,0,0,0,1};
        Iterator<Integer> iterator = bitSet.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            assertEquals(expectedResult[index], iterator.next());
            index++;
        }
    }

    @Test
    void stringRepresentation() {
        bitSet.set(3);

        assertEquals("[0000001000]", bitSet.toString());
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