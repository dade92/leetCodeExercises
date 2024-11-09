package com.myprojects.lists;

import com.myprojects.lists.exceptions.EmptyListException;
import com.myprojects.lists.exceptions.InvalidPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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
            list.toArray()
        );
        assertArrayEquals(
            new Integer[]{},
            empty.toArray()
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
    void contains() {
        assertTrue(list.contains(8));
        assertFalse(list.contains(9));
    }

    @Test
    void search() {
        int searched = 10;

        assertEquals(
            searched,
            list.search(searched).val
        );
        assertNull(list.search(69));
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
        assertEquals(5, list.getAt(0));
        assertEquals(8, list.getAt(1));
        assertEquals(10, list.getAt(2));
    }

    @Test
    void getAtThrowsExceptionIfPositionIsInvalid() {
        assertThrows(
            InvalidPositionException.class,
            () -> list.getAt(-1)
        );
        assertThrows(
            InvalidPositionException.class,
            () -> list.getAt(3)
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
            list.toArray()
        );
        assertEquals(72, list.first());
        assertEquals(10, list.last());
    }

    @Test
    void addElement() {
        list.addElement(5, 0);
        list.addElement(8, 1);
        list.addElement(3, 0);
        list.addElement(4, 2);

        assertArrayEquals(
            new Integer[]{3, 5, 4, 8, 5, 8, 10},
            list.toArray()
        );
        assertEquals(3, list.first());
        assertEquals(10, list.last());
    }

    @Test
    void addElementAsLast() {
        list.addElement(8, 3);

        assertArrayEquals(
            new Integer[]{5, 8, 10, 8},
            list.toArray()
        );
        assertEquals(5, list.first());
        assertEquals(8, list.last());
        assertEquals(8, list.getAt(3));
    }

    @Test
    void add() {
        list.add(55);

        assertArrayEquals(
            new Integer[]{5, 8, 10, 55},
            list.toArray()
        );
        assertEquals(55, list.last());
        assertEquals(5, list.first());
    }

    @Test
    void addInEmptyList() {
        empty.add(12);

        assertArrayEquals(
            new Integer[]{12},
            empty.toArray()
        );
        assertEquals(12, empty.last());
        assertEquals(12, empty.first());
        assertEquals(12, empty.getAt(0));
    }

    @Test
    void addElementInInvalidPositionThrowsException() {
        int element = 11;
        assertThrows(
            InvalidPositionException.class,
            () -> empty.addElement(element, 1)
        );
        assertThrows(
            InvalidPositionException.class,
            () -> list.addElement(element, 4)
        );
    }

    @Test
    void removeElementWhenItIsInFirstPosition() {
        list.removeElement(5);

        assertArrayEquals(
            new Integer[]{8, 10},
            list.toArray()
        );
        assertEquals(8, list.first());
        assertEquals(10, list.last());

        list.removeElement(8);

        assertArrayEquals(
            new Integer[]{10},
            list.toArray()
        );
        assertEquals(10, list.first());
        assertEquals(10, list.last());

        list.removeElement(10);

        assertArrayEquals(
            new Integer[]{},
            list.toArray()
        );
    }

    @Test
    void removeElementWhenItIsInLastPosition() {
        list.removeElement(10);

        assertArrayEquals(
            new Integer[]{5, 8},
            list.toArray()
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
            list.toArray()
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
        list.removeElementAt(1);
        assertArrayEquals(
            new Integer[]{5, 10},
            list.toArray()
        );
        assertEquals(10, list.getAt(1));

        list.removeElementAt(0);
        assertArrayEquals(
            new Integer[]{10},
            list.toArray()
        );
        assertEquals(10, list.first());
        assertEquals(10, list.last());
        assertEquals(10, list.getAt(0));

        list.removeElementAt(0);
        assertArrayEquals(
            new Integer[]{},
            list.toArray()
        );

        assertThrows(
            InvalidPositionException.class,
            () -> list.removeElementAt(0)
        );
        assertThrows(
            InvalidPositionException.class,
            () -> list.getAt(0)
        );
    }

    @Test
    void removeElementAtLastPosition() {
        list.removeElementAt(2);

        assertArrayEquals(
            new Integer[]{5, 8},
            list.toArray()
        );
        assertEquals(5, list.first());
        assertEquals(8, list.last());

        list.removeElementAt(1);

        assertArrayEquals(
            new Integer[]{5},
            list.toArray()
        );
        assertEquals(5, list.first());
        assertEquals(5, list.last());
        assertEquals(5, list.getAt(0));
    }

    @Test
    void removeElementAtInvalidPosition() {
        assertThrows(
            InvalidPositionException.class,
            () -> list.removeElementAt(3)
        );
        assertThrows(
            InvalidPositionException.class,
            () -> list.removeElementAt(-1)
        );
    }

    @Test
    void indexOf() {
        assertEquals(
            1,
            list.indexOf(8)
        );
        assertEquals(
            0,
            list.indexOf(5)
        );
        assertEquals(
            -1,
            list.indexOf(69)
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
            list.toArray()
        );
    }

    @Test
    void iterator() {
        Iterator<Integer> iterator = list.iterator();
        Integer[] expected = {5, 8, 10};
        int i = 0;

        while (iterator.hasNext()) {
            assertEquals(
                expected[i],
                iterator.next()
            );
            i++;
        }
    }

    @Test
    void printingWithToString() {
        assertEquals(
            "[ 5,8,10 ]"
            ,
            list.toString()
        );
    }

    @Test
    void equalsAmongListOfStrings() {
        List<String> first = new List<>("hey", "my", "friend");
        List<String> second = new List<>("hey", "my", "friend");

        assertEquals(first, second);
    }
}