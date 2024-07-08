package com.myprojects.stacks;

import com.myprojects.lists.ListNode;

public class Utils {

    public static ListNode push(ListNode head, int value) {
        ListNode newHead = new ListNode(value);
        newHead.next = head;
        return newHead;
    }

    public static StackStatus pop(ListNode head) {
        ListNode newHead = head.next;
        head.next = null;
        return new StackStatus(newHead, head.val);
    }

}
