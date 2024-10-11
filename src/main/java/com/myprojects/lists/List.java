package com.myprojects.lists;

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
        head = ListUtils.addElement(head, val, position);
        size++;
    }

    public void push(int val) {
        head = ListUtils.push(head, val);
        size++;
    }

    public int pop() {
        int poppedValue = first();
        head = head.next;
        size--;
        return poppedValue;
    }

    public int first() {
        return head != null ? head.val : -1;
    }

    public void removeElement(int val) {
        head = ListUtils.removeElement(head, val);
        size--;
    }

    public void removeElementAt(int n) {
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
}
