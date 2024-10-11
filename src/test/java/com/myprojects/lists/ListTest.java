package com.myprojects.lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTest {

    private final List list = new List();

    @Test
    void addElement() {
        list.addElement(5, 1);    //first element
        list.addElement(8, 2);    //at the end
        list.addElement(3, 1);    //at the top
        list.addElement(4, 3);    //in the middle

        assertArrayEquals(
            new int[] {3, 5, 4, 8},
            list.printList()
        );
    }

    @Test
    void removeElement() {
        list.addElement(10, 1);
        list.addElement(8, 1);
        list.addElement(5, 1);

        list.removeElement(5);
        assertArrayEquals(
            new int[] {8, 10},
            list.printList()
        );
        list.removeElement(8);
        assertArrayEquals(
            new int[] {10},
            list.printList()
        );
        list.removeElement(10);
        assertArrayEquals(
            new int[] {},
            list.printList()
        );
    }

    @Test
    void removeElementAt() {
        list.addElement(10, 1);
        list.addElement(8, 1);
        list.addElement(5, 1);

        list.removeElementAt(2);
        assertArrayEquals(
            new int[] {5, 10},
            list.printList()
        );
        list.removeElementAt(1);
        assertArrayEquals(
            new int[] {10},
            list.printList()
        );
        list.removeElementAt(1);
        assertArrayEquals(
            new int[] {},
            list.printList()
        );
    }

    @Test
    void search() {
        list.addElement(10, 1);
        list.addElement(8, 1);
        list.addElement(5, 1);

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