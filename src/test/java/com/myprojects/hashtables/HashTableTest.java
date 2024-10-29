package com.myprojects.hashtables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.tuple.Pair.of;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HashTableTest {

    private HashTable<String, String> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>(
            of("ciccio", "1"),
            of("pasticcio", "2")
        );
    }

    @Test
    void put() {
        String[] expectedValues = new String[]{"1", "2"};
        String[] expectedKeys = new String[]{"ciccio", "pasticcio"};

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
    void putAlreadyExistingKey() {
        assertEquals(
            "1",
            hashTable.get("ciccio")
        );

        hashTable.put("ciccio", "2");

        assertEquals(
            "2",
            hashTable.get("ciccio")
        );
    }

    @Test
    void get() {
        String actual = hashTable.get("ciccio");

        assertEquals(
            "1",
            actual
        );
    }

    @Test
    void getNonExistingKey() {
        assertNull(hashTable.get("ciccia"));
    }

    @Test
    void removeNonExistingKey() {
        hashTable.remove("NON_EXISTING");

        assertArrayEquals(
            new String[]{"ciccio", "pasticcio"},
            hashTable.keys()
        );
    }

    @Test
    void remove() {
        hashTable.remove("ciccio");

        assertNull(hashTable.get("ciccio"));
    }

    @Test
    void size() {
        assertEquals(
            2,
            hashTable.size()
        );

        hashTable.remove("ciccio");

        assertEquals(
            1,
            hashTable.size()
        );
    }

    @Test
    void shouldHandleCollisions() {
        hashTable.put("cicci", "1");
        hashTable.put("cicce", "1");
        hashTable.put("cicciu", "1");
        hashTable.put("cervo", "1");
        hashTable.put("casa", "1");


        assertArrayEquals(
            new String[]{"cicciu", "casa", "cicci", "ciccio", "cervo", "cicce", "pasticcio"},
            hashTable.keys()
        );
    }

    @Test
    void insertInSamePosition() {
        hashTable.put("cicce", "3");

        assertArrayEquals(
            new String[]{"1", "3", "2"},
            hashTable.values()
        );
        assertArrayEquals(
            new String[]{"ciccio", "cicce", "pasticcio"},
            hashTable.keys()
        );
        assertEquals(
            3,
            hashTable.size()
        );
    }

    @Test
    void rehashingShouldNotRemoveElements() {
        hashTable.put("ciccia", "1");
        hashTable.put("cicci", "1");
        hashTable.put("cicce", "1");
        hashTable.put("cicciu", "1");
        hashTable.put("cico", "1");
        hashTable.put("chico", "1");
        hashTable.put("chicco", "1");
        hashTable.put("cervo", "1");

        assertArrayEquals(
            new String[]{"ciccio", "cervo", "pasticcio", "cicce", "chicco", "cicciu", "cicci", "ciccia", "cico", "chico"},
            hashTable.keys()
        );
        assertEquals(
            10,
            hashTable.size()
        );
        System.out.println(hashTable);
    }

    @Test
    void stringRepresentation() {
        assertEquals(
            "[{ciccio,1},{pasticcio,2}]",
            hashTable.toString()
        );
    }
}