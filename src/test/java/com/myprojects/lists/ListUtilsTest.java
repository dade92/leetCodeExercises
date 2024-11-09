package com.myprojects.lists;

import org.junit.jupiter.api.Test;

import static com.myprojects.Shared.checkListStatus;

public class ListUtilsTest {

    @Test
    void add() {
        int[] expectedFinalResult = {3, 5, 4, 8};

        ListNode<Integer> head = null;
        ListNode<Integer> tail = null;
        head = ListUtils.addElement(head, tail, 5, 0).getLeft();    //first element
        checkListStatus(head,  new int[]{5});
        head = ListUtils.addElement(head, tail, 8, 1).getLeft();    //at the end
        checkListStatus(head, new int[]{5, 8});
        head = ListUtils.addElement(head, tail, 3, 0).getLeft();    //at the top
        checkListStatus(head, new int[]{3, 5, 8});
        head = ListUtils.addElement(head, tail, 4, 2).getLeft();    //in the middle

        checkListStatus(head, expectedFinalResult);
    }

    @Test
    void removeElement() {
        int[] expectedFinalResult = {};

        ListNode<Integer> head;
        ListNode<Integer> tail;

        head = tail = new ListNode<>(5);

        head = ListUtils.addElement(head, tail, 8, 1).getLeft();
        head = ListUtils.addElement(head, tail,10, 2).getLeft();
        checkListStatus(head, new int[]{5, 8, 10});
        head = ListUtils.removeElement(head, tail, 5).getLeft();            //at the top
        checkListStatus(head, new int[]{8, 10});
        head = ListUtils.removeElement(head, tail, 10).getLeft();            //at the end
        checkListStatus(head, new int[]{8});
        head = ListUtils.removeElement(head, tail, 8).getLeft();            //the last one
        checkListStatus(head, expectedFinalResult);
    }

    @Test
    void removeElementAt() {
        int[] expectedFinalResult = {8};

        ListNode<Integer> head;
        ListNode<Integer> tail;
        head = tail = new ListNode<>(5);

        head = ListUtils.addElement(head, tail, 8, 1).getLeft();
        head = ListUtils.addElement(head, tail, 10, 2).getLeft();
        checkListStatus(head, new int[]{5, 8, 10});
        head = ListUtils.removeElementAt(head,tail, 0).getLeft();            //at the top
        checkListStatus(head, new int[]{8, 10});
        head = ListUtils.removeElementAt(head,tail, 1).getLeft();            //at the end
        checkListStatus(head, new int[]{8});
        head = ListUtils.removeElementAt(head,tail, 7).getLeft();            //Non existing position
        checkListStatus(head, expectedFinalResult);
    }

}
