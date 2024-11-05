package com.myprojects.sets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
            new String[] {"Elena", "Paola", "Davide", "Sergio"},
            set.toArray()
        );
    }

    @Test
    void addsTheSameElement() {
        set.add("Davide");

        assertArrayEquals(
            new String[] {"Paola", "Davide", "Sergio"},
            set.toArray()
        );
    }

    @Test
    void size() {
        assertEquals(
            3, set.size()
        );
    }

    @Test
    void stringRepresentation() {
        assertEquals(
            "[ Paola,Davide,Sergio ]",
            set.toString()
        );
    }
}