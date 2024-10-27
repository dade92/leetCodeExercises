package com.myprojects.hashtables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HashTableTest {

    private HashTable<String, String> hashTable = new HashTable<>();

    @Test
    void put() {
        hashTable.put("ciccio", "1");
        hashTable.put("pasticcio", "2");

        String[] expectedValues = new String[] {"1", "2"};
        String[] expectedKeys = new String[] {"ciccio", "pasticcio"};

        assertArrayEquals(
            expectedValues,
            hashTable.values()
        );
        assertArrayEquals(
            expectedKeys,
            hashTable.keys()
        );
    }

    @Test
    void get() {
        hashTable.put("ciccio", "1");

        String actual = hashTable.get("ciccio");

        assertEquals(
            "1",
            actual
        );
    }
}