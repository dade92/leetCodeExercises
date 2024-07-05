package com.myprojects.lists;

public class Utils {

    public static ListNode addElementAfterHead(ListNode head, int val) {
        ListNode newNode = new ListNode(val);

        newNode.next = head.next;
        head.next = newNode;
        return head;
    }

    public static ListNode removeElement(ListNode head, int val) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null && current.val != val) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            if(previous == null) {
                head = head.next;
            } else {
                previous.next = current.next;
            }
        }
        return head;
    }

    public static ListNode removeNthElement(ListNode head, int n) {
        int i = 1;
        ListNode previous = null;
        ListNode current = head;
        while(current != null && i<n) {
            previous = current;
            current = current.next;
            i++;
        }

        if(current != null) {
            if(previous == null) {
                head = head.next;
            } else {
                previous.next = current.next;
            }
        }
        return head;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

}
