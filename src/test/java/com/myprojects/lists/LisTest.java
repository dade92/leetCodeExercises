package com.myprojects.lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LisTest {

    @Test
    void addElement() {
        int[] expectedFinalResult = {3, 5, 4, 8};

        ListNode head = new ListNode(5);

        head = Utils.addElement(head, 8, 2);    //at the end
        checkListStatus(head, new int[]{5, 8});
        head = Utils.addElement(head, 3, 1);    //at the top
        checkListStatus(head, new int[]{3, 5, 8});
        head = Utils.addElement(head, 4, 3);    //in the middle

        checkListStatus(head, expectedFinalResult);
    }

    @Test
    void removeElement() {
        int[] expectedFinalResult = {3, 5, 4, 8};

        ListNode head = new ListNode(5);

        head = Utils.addElement(head, 8, 2);
        head = Utils.addElement(head, 10, 3);
        checkListStatus(head, new int[]{5, 8, 10});
        head = Utils.removeElement(head, 5);            //at the top
        checkListStatus(head, new int[]{8, 10});
    }

    private void checkListStatus(ListNode head, int[] expectedElements) {
        int index = 0;
        while(head != null) {
            assertEquals(expectedElements[index], head.val);
            head = head.next;
            index++;
        }
    }
}
