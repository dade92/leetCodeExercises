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

        head = Utils.addElementAfterHead(head, 2);
        head = Utils.addElementAfterHead(head, 3);
        head = Utils.addElementAfterHead(head, 4);

        head = Utils.removeElement(head, 1);

        Utils.printList(head);

        head = Utils.removeNthElement(head, 1);

        Utils.printList(head);
    }

}
