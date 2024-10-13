package com.myprojects.queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    private final Queue<Integer> queue = new Queue<>(5, 8, 10);

    @Test
    void initCorrectly() {
        assertArrayEquals(
            new Integer[]{5, 8, 10},
            queue.printQueue()
        );
    }

    @Test
    void enqueue() {
        queue.enqueue(62);
        queue.enqueue(64);

        assertArrayEquals(
            new Integer[]{5, 8, 10, 62, 64},
            queue.printQueue()
        );
    }

    @Test
    void dequeue() {
        int result = queue.dequeue();

        assertEquals(5, result);

        assertArrayEquals(
            new Integer[]{8, 10},
            queue.printQueue()
        );
    }

    @Test
    void top() {
        int top = queue.top();

        assertEquals(5, top);

        assertArrayEquals(
            new Integer[]{5, 8, 10},
            queue.printQueue()
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
}
