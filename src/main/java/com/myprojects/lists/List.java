package com.myprojects.lists;

import com.myprojects.lists.exceptions.EmptyListException;
import com.myprojects.lists.exceptions.InvalidPositionException;

public class List {

    private ListNode head;
    private int size;

    public List() {
        this.head = null;
        this.size = 0;
    }

    public List(int... elements) {
        this.size = elements.length;
        for (int element : elements) {
            head = ListUtils.enqueue(head, element);
        }
    }

    public void addElement(int val, int position) {
        if(position < 1 || position > size + 1) {
            throw new InvalidPositionException();
        }
        head = ListUtils.addElement(head, val, position);
        size++;
    }

    public void addLast(int val) {
        head = ListUtils.enqueue(head, val);
        size++;
    }

    public void addFirst(int val) {
        head = ListUtils.push(head, val);
        size++;
    }

    public int removeFromTop() {
        int poppedValue = first();
        head = head.next;
        size--;
        return poppedValue;
    }

    public int first() {
        if(head != null) {
            return head.val;
        }
        throw new EmptyListException();
    }

    public void removeElement(int val) {
        if(head != null) {
            head = ListUtils.removeElement(head, val);
            size--;
        } else {
            throw new EmptyListException();
        }
    }

    public void removeElementAt(int n) {
        if(n < 1 || n > size) {
            throw new InvalidPositionException();
        }
        head = ListUtils.removeElementAt(head, n);
        size--;
    }

    public int searchElement(int val) {
        return ListUtils.searchElement(head, val);
    }

    public int[] printList() {
        int[] result = new int[size];
        int index = 0;
        ListNode temp = head;
        while (temp != null) {
            result[index] = temp.val;
            temp = temp.next;
            index++;
        }
        return result;
    }

    public int size() {
        return size;
    }
}
