package com.myprojects.lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListTest {

    @Test
    void addElement() {
        int[] expectedFinalResult = {3, 5, 4, 8};

        ListNode head = null;
        head = Utils.addElement(head, 5, 1);    //first element
        checkListStatus(head, new int[]{5});
        head = Utils.addElement(head, 8, 2);    //at the end
        checkListStatus(head, new int[]{5, 8});
        head = Utils.addElement(head, 3, 1);    //at the top
        checkListStatus(head, new int[]{3, 5, 8});
        head = Utils.addElement(head, 4, 3);    //in the middle

        checkListStatus(head, expectedFinalResult);
    }

    @Test
    void removeElement() {
        int[] expectedFinalResult = {};

        ListNode head = new ListNode(5);

        head = Utils.addElement(head, 8, 2);
        head = Utils.addElement(head, 10, 3);
        checkListStatus(head, new int[]{5, 8, 10});
        head = Utils.removeElement(head, 5);            //at the top
        checkListStatus(head, new int[]{8, 10});
        head = Utils.removeElement(head, 10);            //at the end
        checkListStatus(head, new int[]{8});
        head = Utils.removeElement(head, 8);            //the last one
        checkListStatus(head, expectedFinalResult);
    }

    @Test
    void removeElementAt() {
        int[] expectedFinalResult = {8};

        ListNode head = new ListNode(5);

        head = Utils.addElement(head, 8, 2);
        head = Utils.addElement(head, 10, 3);
        checkListStatus(head, new int[]{5, 8, 10});
        head = Utils.removeElementAt(head, 1);            //at the top
        checkListStatus(head, new int[]{8, 10});
        head = Utils.removeElementAt(head, 2);            //at the end
        checkListStatus(head, new int[]{8});
        head = Utils.removeElementAt(head, 8);            //Non existing position
        checkListStatus(head, expectedFinalResult);
    }

    private void checkListStatus(ListNode head, int[] expectedElements) {
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
