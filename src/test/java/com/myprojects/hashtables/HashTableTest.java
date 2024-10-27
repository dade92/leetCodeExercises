package com.myprojects.hashtables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private final HashTable<String, String> hashTable = new HashTable<>();

    @Test
    void put() {
        hashTable.put("ciccio", "1");
        hashTable.put("pasticcio", "2");

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
        hashTable.put("ciccio", "1");

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
        hashTable.put("ciccio", "1");

        String actual = hashTable.get("ciccio");

        assertEquals(
            "1",
            actual
        );
    }

    @Test
    void getNonExistingKey() {
        assertNull(hashTable.get("ciccio"));
    }

    @Test
    void removeNonExistingKey() {
        hashTable.put("ciccio", "1");
        hashTable.remove("NON_EXISTING");

        assertArrayEquals(
            new String[] {"ciccio"},
            hashTable.keys()
        );
    }

    @Test
    void remove() {
        hashTable.put("ciccio", "1");

        hashTable.remove("ciccio");

        assertNull(hashTable.get("ciccio"));
    }

    @Test
    void size() {
        assertEquals(
            0,
            hashTable.size()
        );

        hashTable.put("Giorgio", "1");

        assertEquals(
            1,
            hashTable.size()
        );
    }

    @Test
    void shouldHandleCollisions() {
        hashTable.put("ciccio", "1");
        hashTable.put("pasticcio", "1");
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
        hashTable.put("pasticcio", "1");
        hashTable.put("cicce", "2");

        assertArrayEquals(
            new String[]{"2", "1"},
            hashTable.values()
        );
        assertArrayEquals(
            new String[]{"cicce", "pasticcio"},
            hashTable.keys()
        );
        assertEquals(
            2,
            hashTable.size()
        );
    }

    @Test
    void rehashingShouldNotRemoveElements() {
        hashTable.put("ciccio", "1");
        hashTable.put("ciccia", "1");
        hashTable.put("cicci", "1");
        hashTable.put("cicce", "1");
        hashTable.put("cicciu", "1");
        hashTable.put("cico", "1");
        hashTable.put("chico", "1");
        hashTable.put("chicco", "1");
        hashTable.put("cervo", "1");

        assertArrayEquals(
            new String[]{"ciccio", "cervo", "chico", "chicco", "cicce", "cicciu", "cicci", "cico", "ciccia"},
            hashTable.keys()
        );
        assertEquals(
            9, hashTable.size()
        );
    }
}