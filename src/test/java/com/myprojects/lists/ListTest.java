package com.myprojects.lists;

import com.myprojects.lists.exceptions.EmptyListException;
import com.myprojects.lists.exceptions.InvalidPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListTest {
    private List<Integer> list;
    private final List<Integer> empty = new List<>();

    @BeforeEach
    void setUp() {
        list = new List<>(5, 8, 10);
    }

    @Test
    void initListCorrectlyPassingListOfParams() {
        assertArrayEquals(
            new Integer[]{5, 8, 10},
            list.printList()
        );
        assertArrayEquals(
            new Integer[]{},
            empty.printList()
        );
    }

    @Test
    void size() {
        assertEquals(3, list.size());
        assertEquals(0, empty.size());
    }

    @Test
    void isEmpty() {
        assertFalse(list.isEmpty());
        assertTrue(empty.isEmpty());
    }

    @Test
    void returnFirstElementCorrectly() {
        assertEquals(5, list.first());
    }

    @Test
    void returnLastElementCorrectly() {
        assertEquals(10, list.last());
    }

    @Test
    void getAt() {
        assertEquals(5, list.getAt(1));
        assertEquals(8, list.getAt(2));
        assertEquals(10, list.getAt(3));
    }

    @Test
    void getAtThrowsExceptionIfPositionIsInvalid() {
        assertThrows(
            InvalidPositionException.class,
            () -> list.getAt(4)
        );
    }

    @Test
    void requestingFirstElementIfEmptyThrowsError() {
        assertThrows(
            EmptyListException.class,
            empty::first
        );
    }

    @Test
    void addFirstElement() {
        list.addFirst(88);
        list.addFirst(72);

        assertArrayEquals(
            new Integer[]{72, 88, 5, 8, 10},
            list.printList()
        );
        assertEquals(72, list.first());
        assertEquals(10, list.last());
    }

    @Test
    void addElement() {
        list.addElement(5, 1);
        list.addElement(8, 2);
        list.addElement(3, 1);
        list.addElement(4, 3);

        assertArrayEquals(
            new Integer[]{3, 5, 4, 8, 5, 8, 10},
            list.printList()
        );
        assertEquals(3, list.first());
        assertEquals(10, list.last());
    }

    @Test
    void addElementAsLast() {
        list.addElement(8, 4);

        assertArrayEquals(
            new Integer[]{5, 8, 10, 8},
            list.printList()
        );
        assertEquals(5, list.first());
        assertEquals(8, list.last());
    }

    @Test
    void addLast() {
        list.addLast(55);

        assertArrayEquals(
            new Integer[]{5, 8, 10, 55},
            list.printList()
        );
        assertEquals(55, list.last());
        assertEquals(5, list.first());

        empty.addLast(12);

        assertArrayEquals(
            new Integer[]{12},
            empty.printList()
        );
        assertEquals(12, empty.last());
        assertEquals(12, empty.first());
    }

    @Test
    void addElementInInvalidPositionThrowsException() {
        assertThrows(
            InvalidPositionException.class,
            () -> empty.addElement(11, 2)
        );
        assertThrows(
            InvalidPositionException.class,
            () -> list.addElement(11, 5)
        );
    }

    @Test
    void removeElementInFirstPosition() {
        list.removeElement(5);

        assertArrayEquals(
            new Integer[]{8, 10},
            list.printList()
        );
        assertEquals(8, list.first());
        assertEquals(10, list.last());

        list.removeElement(8);

        assertArrayEquals(
            new Integer[]{10},
            list.printList()
        );
        assertEquals(10, list.first());
        assertEquals(10, list.last());

        list.removeElement(10);

        assertArrayEquals(
            new Integer[]{},
            list.printList()
        );
    }

    @Test
    void removeElementInLastPosition() {
        list.removeElement(10);

        assertArrayEquals(
            new Integer[]{5, 8},
            list.printList()
        );
        assertEquals(5, list.first());
        assertEquals(8, list.last());
    }

    @Test
    void removingElementFromEmptyListThrowsException() {
        assertThrows(
            EmptyListException.class,
            () -> empty.removeElement(1)
        );
    }

    @Test
    void removeFromTop() {
        int popped = list.removeFromTop();

        assertArrayEquals(
            new Integer[]{8, 10},
            list.printList()
        );
        assertEquals(8, list.first());
        assertEquals(10, list.last());
        assertEquals(5, popped);
    }

    @Test
    void removeFromTopElementFromEmptyListThrowsException() {
        assertThrows(
            EmptyListException.class,
            empty::removeFromTop
        );
    }

    @Test
    void removeElementAt() {
        list.removeElementAt(2);
        assertArrayEquals(
            new Integer[]{5, 10},
            list.printList()
        );

        list.removeElementAt(1);
        assertArrayEquals(
            new Integer[]{10},
            list.printList()
        );
        assertEquals(10, list.first());
        assertEquals(10, list.last());

        list.removeElementAt(1);
        assertArrayEquals(
            new Integer[]{},
            list.printList()
        );

        assertThrows(
            InvalidPositionException.class,
            () -> list.removeElementAt(1)
        );
    }

    @Test
    void removeElementAtLastPosition() {
        list.removeElementAt(3);

        assertArrayEquals(
            new Integer[]{5, 8},
            list.printList()
        );
        assertEquals(5, list.first());
        assertEquals(8, list.last());

        list.removeElementAt(2);

        assertArrayEquals(
            new Integer[]{5},
            list.printList()
        );
        assertEquals(5, list.first());
        assertEquals(5, list.last());
    }

    @Test
    void removeElementAtInvalidPosition() {
        assertThrows(
            InvalidPositionException.class,
            () -> list.removeElementAt(4)
        );
        assertThrows(
            InvalidPositionException.class,
            () -> list.removeElementAt(0)
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

    @Test
    void toArray() {
        assertArrayEquals(
            new Integer[]{5, 8, 10},
            list.toArray()
        );
    }

    @Test
    void equals() {
        List<Integer> shorter = new List<>(5, 8);
        List<Integer> equal = new List<>(5, 8, 10);
        List<Integer> otherElements = new List<>(5, 8, 11);

        assertNotEquals(list, shorter);
        assertNotEquals(list, otherElements);
        assertEquals(list, equal);
    }

    @Test
    void addAll() {
        list.addAll(new List<>(1, 2));

        assertArrayEquals(
            new Integer[]{5, 8, 10, 1, 2},
            list.printList()
        );
    }

    @Test
    void printingWithToString() {
        assertEquals(
            "5,8,10"
            ,
            list.toString()
        );
    }
}