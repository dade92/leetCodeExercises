package com.myprojects.lists;

public class Utils {

    public static void addElementAfterHead(ListNode head, int val) {
        ListNode newNode = new ListNode(val);

        newNode.next = head.next;
        head.next = newNode;
    }

    //TODO how to remove the first element?
    public static void removeElement(ListNode head, int val) {
        ListNode previous = null;
        while (head != null && head.val != val) {
            previous = head;
            head = head.next;
        }
        if (head != null && previous != null) {
            previous.next = head.next;
        }
    }

    public static void removeNthElement(ListNode head, int n) {
        int i = 1;
        ListNode previous = null;
        while(head != null && i<n) {
            previous = head;
            head = head.next;
            i++;
        }

        if(head != null && previous != null) {
            previous.next = head.next;
        }
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

}
