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

        head = Utils.addElement(head, 2, 2);
        head = Utils.addElement(head, 3, 3);

        Utils.printList(head);

//        head = Utils.removeElement(head, 1);
//
//        Utils.printList(head);
//
//        head = Utils.removeNthElement(head, 1);
//
//        Utils.printList(head);
    }

}
