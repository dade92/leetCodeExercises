package com.myprojects.lists;

import org.apache.commons.lang3.tuple.Pair;

final class ListUtils {

    public static <T> Pair<ListNode<T>, ListNode<T>> addElement(ListNode<T> head, ListNode<T> tail, T val, int position) {
        ListNode<T> newNode = new ListNode<>(val);

        if (position == 0) {
            newNode.next = head;
            if (head == null) {
                tail = newNode;
            }
            head = newNode;
        } else {
            ListNode<T> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
            if (current == tail) {
                tail = newNode;
            }
        }

        return Pair.of(head, tail);
    }

    public static <T>  ListNode<T> search(ListNode<T> current, T val) {
        while (current != null) {
            if (current.val.equals(val)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public static <T> Pair<ListNode<T>, ListNode<T>> removeElement(ListNode<T> head, ListNode<T> tail, T val) {
        ListNode<T> previous = null;
        ListNode<T> current = head;
        while (current != null && current.val != val) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            if (previous == null) {
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                }
            } else {
                previous.next = current.next;
                if (tail == current) {
                    tail = previous;
                }
            }
        }
        return Pair.of(head, tail);
    }

    public static <T> Pair<ListNode<T>, ListNode<T>> removeElementAt(ListNode<T> head, ListNode<T> tail, int position) {
        int i = 0;
        ListNode<T> previous = null;
        ListNode<T> current = head;
        while (current != null && i < position) {
            previous = current;
            current = current.next;
            i++;
        }

        if (current != null) {
            if (previous == null) {
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                }
            } else {
                previous.next = current.next;
                if (tail == current) {
                    tail = previous;
                }
            }
        }
        return Pair.of(head, tail);
    }
}
