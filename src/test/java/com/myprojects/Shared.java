package com.myprojects;

import com.myprojects.lists.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Shared {

    public static void checkListStatus(ListNode<Integer> head, int[] expectedElements) {
        if(expectedElements.length == 0) {
            assertNull(head);
        }
        int index = 0;
        while(head != null) {
            assertEquals(expectedElements[index], head.val);
            head = head.next;
            index++;
        }
    }

}
