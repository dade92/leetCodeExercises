package com.myprojects.hashtables;

import com.myprojects.lists.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.tuple.Pair.of;
import static org.junit.jupiter.api.Assertions.*;

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
        List<String> expectedValues = new List<>("1", "2");
        List<String> expectedKeys = new List<>("ciccio", "pasticcio");

        assertEquals(
            expectedValues,
            hashTable.values()
        );
        assertEquals(
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

        assertEquals(
            new List<>("ciccio", "pasticcio"),
            hashTable.keys()
        );
    }

    @Test
    void remove() {
        hashTable.remove("ciccio");

        assertNull(hashTable.get("ciccio"));

        assertEquals(
            new List<>("pasticcio"),
            hashTable.keys()
        );
        assertEquals(
            new List<>("2"),
            hashTable.values()
        );
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


        assertEquals(
            new List<>("cicciu", "casa", "cicci", "ciccio", "cervo", "cicce", "pasticcio"),
            hashTable.keys()
        );
    }

    @Test
    void insertInSamePosition() {
        hashTable.put("cicce", "3");

        assertEquals(
            new List<>("1", "3", "2"),
            hashTable.values()
        );
        assertEquals(
            new List<>("ciccio", "cicce", "pasticcio"),
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

        assertEquals(
            new List<>("ciccio", "cervo", "pasticcio", "cicce", "chicco", "cicciu", "cicci", "ciccia", "cico", "chico"),
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

    @Test
    void equality() {
        HashTable<String, String> anotherHashTable = new HashTable<>(
            of("ciccio", "1"),
            of("pasticcio", "2")
        );

        assertEquals(hashTable, anotherHashTable);
    }

    @Test
    void containsKey() {
        assertTrue(hashTable.containsKey("ciccio"));
        assertFalse(hashTable.containsKey("ciccia"));
    }

    @Test
    void containsValue() {
        assertTrue(hashTable.containsValue("1"));
        assertFalse(hashTable.containsValue("3"));
    }

    @Test
    void replace() {
        String newValue = "3";
        String oldValue = hashTable.replace("ciccio", newValue);
        assertEquals(
            "1",
            oldValue
        );
        assertEquals(
            newValue,
            hashTable.get("ciccio")
        );

        assertNull(hashTable.replace("ciccia", "5"));
        assertNull(hashTable.get("ciccia"));
    }
}