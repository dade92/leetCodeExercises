package com.myprojects.queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueTest {

    private final Queue queue = new Queue(5, 8, 10);

    @Test
    void initCorrectly() {
        assertArrayEquals(
            new int[]{5, 8, 10},
            queue.printQueue()
        );
    }

    @Test
    void enqueue() {
        queue.enqueue(62);
        queue.enqueue(64);

        assertArrayEquals(
            new int[]{5, 8, 10, 62, 64},
            queue.printQueue()
        );
    }

    @Test
    void dequeue() {
        int result = queue.dequeue();

        assertEquals(5, result);

        assertArrayEquals(
            new int[]{8, 10},
            queue.printQueue()
        );
    }

}
