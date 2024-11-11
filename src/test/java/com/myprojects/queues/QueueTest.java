package com.myprojects.queues;

import com.myprojects.lists.List;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QueueTest {

    private final Queue<Integer> queue = new Queue<>(5, 8, 10);

    @Test
    void initCorrectly() {
        assertArrayEquals(
            new Integer[]{5, 8, 10},
            queue.toArray()
        );
    }

    @Test
    void enqueue() {
        queue.enqueue(62);
        queue.enqueue(64);

        assertArrayEquals(
            new Integer[]{5, 8, 10, 62, 64},
            queue.toArray()
        );
    }

    @Test
    void dequeue() {
        int result = queue.dequeue();

        assertEquals(5, result);

        assertArrayEquals(
            new Integer[]{8, 10},
            queue.toArray()
        );
    }

    @Test
    void top() {
        int top = queue.top();

        assertEquals(5, top);

        assertArrayEquals(
            new Integer[]{5, 8, 10},
            queue.toArray()
        );
    }

    @Test
    void addAll() {
        queue.addAll(new List<>(1, 2, 3));

        assertArrayEquals(
            new Integer[]{5, 8, 10, 1, 2, 3},
            queue.toArray()
        );
    }

    @Test
    void equals() {
        Queue another = new Queue(5, 8, 10);
        Queue notEqual = new Queue(5, 8, 11);
        Queue notEqual2 = new Queue(5, 8);

        assertEquals(queue, another);
        assertNotEquals(queue, notEqual);
        assertNotEquals(queue, notEqual2);
    }

    @Test
    void iterator() {
        Iterator<Integer> iterator = queue.iterator();
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
    void stringRepresentation() {
        assertEquals("[5,8,10]", queue.toString());
    }
}
