package com.myprojects.lists;

final class ListUtils {

    public static ListNode addElement(ListNode head, int val, int position) {
        ListNode newNode = new ListNode(val);

        if (position == 1) {
            newNode.next = head;
            head = newNode;
        } else {
            ListNode current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        return head;
    }

    public static <T> ListNode<T> enqueue(ListNode head, T val) {
        ListNode newNode = new ListNode(val);

        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        return head;
    }

    public static <T> ListNode<T> push(ListNode head, T val) {
        ListNode newNode = new ListNode(val);

        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    public static <T> int  searchElement(ListNode current, T val) {
        int index = 1;
        while (current != null) {
            if (current.val == val) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public static <T> T getAt(ListNode<T> head, int position) {
        int index = 1;
        ListNode<T> current = head;
        while(index < position) {
            current = current.next;
            index++;
        }

        return current.val;
    }

    public static <T> ListNode<T> removeElement(ListNode head, T val) {
        ListNode<T> previous = null;
        ListNode<T> current = head;
        while (current != null && current.val != val) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            if (previous == null) {
                head = head.next;
            } else {
                previous.next = current.next;
            }
        }
        return head;
    }

    public static ListNode removeElementAt(ListNode head, int n) {
        int i = 1;
        ListNode previous = null;
        ListNode current = head;
        while (current != null && i < n) {
            previous = current;
            current = current.next;
            i++;
        }

        if (current != null) {
            if (previous == null) {
                head = head.next;
            } else {
                previous.next = current.next;
            }
        }
        return head;
    }

}
