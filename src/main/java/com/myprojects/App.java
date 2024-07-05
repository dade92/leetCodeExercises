package com.myprojects;

import com.myprojects.lists.ListNode;
import com.myprojects.lists.Utils;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ListNode head = new ListNode(1);

        Utils.addElementAfterHead(head, 2);
        Utils.addElementAfterHead(head, 3);
        Utils.addElementAfterHead(head, 4);

        Utils.printList(head);

        Utils.removeElement(head, 2);

        Utils.printList(head);

        Utils.removeNthElement(head, 2);

        Utils.printList(head);
    }

}
