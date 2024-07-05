package com.myprojects;

import com.myprojects.lists.ListNode;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);

        ListNode head = node1;
        head.next = node2;

        traverse(head);
        traverse(head);
    }

    private static void traverse(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        System.out.println();
    }
}
