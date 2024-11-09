package com.myprojects.sets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {

    private Set<String> set;

    @BeforeEach
    void setUp() {
        set = new Set<>("Davide", "Sergio", "Paola");
    }

    @Test
    void add() {
        set.add("Elena");

        assertArrayEquals(
            new String[]{"Elena", "Paola", "Davide", "Sergio"},
            set.toArray()
        );
        assertEquals(
            4,
            set.size()
        );
    }

    @Test
    void addAll() {
        Set<String> set2 = new Set<>("Elena", "Stefania");

        set.addAll(set2);

        assertArrayEquals(
            new String[]{"Elena", "Paola", "Stefania", "Davide", "Sergio"},
            set.toArray()
        );
    }

    @Test
    void addsTheSameElement() {
        set.add("Davide");

        assertArrayEquals(
            new String[]{"Paola", "Davide", "Sergio"},
            set.toArray()
        );
        assertEquals(
            3,
            set.size()
        );
    }

    @Test
    void remove() {
        set.remove("Davide");

        assertArrayEquals(
            new String[]{"Paola", "Sergio"},
            set.toArray()
        );
        assertEquals(
            2,
            set.size()
        );
    }

    @Test
    void removeAll() {
        set.removeAll("Davide", "Paola", "Elena");

        assertArrayEquals(
            new String[]{"Sergio"},
            set.toArray()
        );
    }

    @Test
    void contains() {
        assertTrue(set.contains("Davide"));
        assertFalse(set.contains("Elena"));
    }

    @Test
    void size() {
        assertEquals(
            3, set.size()
        );
    }

    @Test
    void emptySet() {
        Set empty = new Set();

        assertEquals(
            0,
            empty.size()
        );
    }

    @Test
    void stringRepresentation() {
        assertEquals(
            "[ Paola,Davide,Sergio ]",
            set.toString()
        );
    }

    @Test
    void equality() {
        Set<String> anotherSet = new Set<>("Davide", "Sergio", "Paola");

        assertEquals(anotherSet, set);
    }

    @Test
    void intersect() {
        Set<String> anotherSet = new Set<>("Elena", "Sergio", "Stefania");

        assertTrue(set.intersect(anotherSet));

        assertArrayEquals(
            new String[]{"Sergio"},
            set.toArray()
        );
    }
}