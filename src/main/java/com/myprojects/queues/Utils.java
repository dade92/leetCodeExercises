package com.myprojects.queues;

import com.myprojects.lists.ListNode;

public class Utils {

    public static ListNode enqueue(ListNode head, int val) {
        if (head == null) {
            head = new ListNode(val);
            return head;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(val);
        }
        return head;
    }

    public static QueueStatus dequeue(ListNode head) {
        int removed = -1;
        if (head != null) {
            removed = head.val;
            head = head.next;
        }
        return new QueueStatus(head, removed);
    }

}
