package com.myprojects.lists;

import org.junit.jupiter.api.Test;

import static com.myprojects.Shared.checkListStatus;

public class ListUtilsTest {

    @Test
    void addElement() {
        int[] expectedFinalResult = {3, 5, 4, 8};

        ListNode<Integer> head = null;
        head = ListUtils.addElement(head, 5, 1);    //first element
        checkListStatus(head, new int[]{5});
        head = ListUtils.addElement(head, 8, 2);    //at the end
        checkListStatus(head, new int[]{5, 8});
        head = ListUtils.addElement(head, 3, 1);    //at the top
        checkListStatus(head, new int[]{3, 5, 8});
        head = ListUtils.addElement(head, 4, 3);    //in the middle

        checkListStatus(head, expectedFinalResult);
    }

    @Test
    void removeElement() {
        int[] expectedFinalResult = {};

        ListNode<Integer> head = new ListNode<>(5);

        head = ListUtils.addElement(head, 8, 2);
        head = ListUtils.addElement(head, 10, 3);
        checkListStatus(head, new int[]{5, 8, 10});
        head = ListUtils.removeElement(head, 5);            //at the top
        checkListStatus(head, new int[]{8, 10});
        head = ListUtils.removeElement(head, 10);            //at the end
        checkListStatus(head, new int[]{8});
        head = ListUtils.removeElement(head, 8);            //the last one
        checkListStatus(head, expectedFinalResult);
    }

    @Test
    void removeElementAt() {
        int[] expectedFinalResult = {8};

        ListNode<Integer> head = new ListNode<>(5);

        head = ListUtils.addElement(head, 8, 2);
        head = ListUtils.addElement(head, 10, 3);
        checkListStatus(head, new int[]{5, 8, 10});
        head = ListUtils.removeElementAt(head, 1);            //at the top
        checkListStatus(head, new int[]{8, 10});
        head = ListUtils.removeElementAt(head, 2);            //at the end
        checkListStatus(head, new int[]{8});
        head = ListUtils.removeElementAt(head, 8);            //Non existing position
        checkListStatus(head, expectedFinalResult);
    }

}
