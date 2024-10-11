package com.myprojects.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTest {

    private List list;

    @BeforeEach
    void setUp() {
        list = new List(5, 8, 10);
    }

    @Test
    void init() {
        assertArrayEquals(
            new int[]{5, 8, 10},
            list.printList()
        );
    }

    @Test
    void firstElement() {
        assertEquals(5, list.first());
        assertArrayEquals(
            new int[]{5, 8, 10},
            list.printList()
        );
    }

    @Test
    void pushElement() {
        list.push(88);
        list.push(72);

        assertArrayEquals(
            new int[]{72, 88, 5, 8, 10},
            list.printList()
        );
    }

    @Test
    void addElement() {
        list.addElement(5, 1);    //first element
        list.addElement(8, 2);    //at the end
        list.addElement(3, 1);    //at the top
        list.addElement(4, 3);    //in the middle

        assertArrayEquals(
            new int[]{3, 5, 4, 8, 5, 8, 10},
            list.printList()
        );
    }

    @Test
    void removeElement() {
        list.removeElement(5);
        assertArrayEquals(
            new int[]{8, 10},
            list.printList()
        );
        list.removeElement(8);
        assertArrayEquals(
            new int[]{10},
            list.printList()
        );
        list.removeElement(10);
        assertArrayEquals(
            new int[]{},
            list.printList()
        );
    }

    @Test
    void removeElementAt() {
        list.removeElementAt(2);
        assertArrayEquals(
            new int[]{5, 10},
            list.printList()
        );
        list.removeElementAt(1);
        assertArrayEquals(
            new int[]{10},
            list.printList()
        );
        list.removeElementAt(1);
        assertArrayEquals(
            new int[]{},
            list.printList()
        );
    }

    @Test
    void search() {
        assertEquals(
            2,
            list.searchElement(8)
        );
        assertEquals(
            -1,
            list.searchElement(69)
        );
    }
}