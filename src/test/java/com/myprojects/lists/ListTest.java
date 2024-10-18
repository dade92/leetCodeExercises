package com.myprojects.lists;

import com.myprojects.lists.exceptions.EmptyListException;
import com.myprojects.lists.exceptions.InvalidPositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertArrayEquals(
            new Integer[]{5, 8, 10},
            list.printList()
        );
    }

    @Test
    void getAt() {
        assertEquals(8, list.getAt(2));
        assertEquals(5, list.getAt(1));
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
    }

    @Test
    void removeFromTop() {
        int popped = list.removeFromTop();
        assertArrayEquals(
            new Integer[]{8, 10},
            list.printList()
        );
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
    void addElement() {
        list.addElement(5, 1);    //first element
        list.addElement(8, 2);    //at the end
        list.addElement(3, 1);    //at the top
        list.addElement(4, 3);    //in the middle

        assertArrayEquals(
            new Integer[]{3, 5, 4, 8, 5, 8, 10},
            list.printList()
        );
    }

    @Test
    void addElementAsLast() {
        list.addLast(55);

        assertArrayEquals(
            new Integer[]{5, 8, 10, 55},
            list.printList()
        );

        empty.addLast(12);

        assertArrayEquals(
            new Integer[]{12},
            empty.printList()
        );
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
    void removeElement() {
        list.removeElement(5);
        assertArrayEquals(
            new Integer[]{8, 10},
            list.printList()
        );
        list.removeElement(8);
        assertArrayEquals(
            new Integer[]{10},
            list.printList()
        );
        list.removeElement(10);
        assertArrayEquals(
            new Integer[]{},
            list.printList()
        );
    }

    @Test
    void removingElementFromEmptyListThrowsException() {
        assertThrows(
            EmptyListException.class,
            () -> empty.removeElement(1)
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
        list.removeElementAt(1);
        assertArrayEquals(
            new Integer[]{},
            list.printList()
        );
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