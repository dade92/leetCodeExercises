package com.myprojects.bitsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BitSetTest {

    private final BitSet bitSet = new BitSet(10);

    @Test
    void getASetValue() {
        bitSet.set(5);
        assertTrue(bitSet.get(5));
    }

    @Test
    void stringRepresentation() {
        bitSet.set(3);

        Assertions.assertEquals("[0000001000]", bitSet.toString());
    }
}