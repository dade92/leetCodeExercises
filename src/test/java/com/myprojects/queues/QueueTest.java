package com.myprojects.queues;

import com.myprojects.Shared;
import com.myprojects.lists.ListNode;
import org.junit.jupiter.api.Test;

import static com.myprojects.Shared.checkListStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QueueTest {

    @Test
    void enqueue() {
        ListNode head = null;

        head = Utils.enqueue(head, 3);
        checkListStatus(head, new int[] {3});
        head = Utils.enqueue(head, 1);
        checkListStatus(head, new int[] {3, 1});
    }

    @Test
    void dequeue() {
        ListNode head = null;

        head = Utils.enqueue(head, 3);
        checkListStatus(head, new int[] {3});
        head = Utils.enqueue(head, 1);
        checkListStatus(head, new int[] {3, 1});

        QueueStatus status = Utils.dequeue(head);
        head = status.newHead();
        assertEquals(3, status.removedValue());
        checkListStatus(head, new int[] {1});
    }

}
