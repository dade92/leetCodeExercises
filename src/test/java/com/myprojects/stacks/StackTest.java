package com.myprojects.stacks;

import com.myprojects.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StackTest {

    @Test
    void push() {
        int[] expectedFinalResult = {5, 4, 8};

        ListNode head = null;

        head = Utils.push(head, 8);
        checkListStatus(head, new int[]{8});
        head = Utils.push(head, 4);
        head = Utils.push(head, 5);
        checkListStatus(head, expectedFinalResult);
    }

    @Test
    void pop() {
        int[] expectedFinalResult = {};

        ListNode head = null;

        head = Utils.push(head, 8);
        checkListStatus(head, new int[]{8});
        StackStatus status = Utils.pop(head);

        head = status.newHead();
        assertEquals(8, status.removedValue());
        checkListStatus(head, expectedFinalResult);
    }

    private void checkListStatus(ListNode head, int[] expectedElements) {
        if (expectedElements.length == 0) {
            assertNull(head);
        }
        int index = 0;
        while (head != null) {
            assertEquals(expectedElements[index], head.val);
            head = head.next;
            index++;
        }
    }
}