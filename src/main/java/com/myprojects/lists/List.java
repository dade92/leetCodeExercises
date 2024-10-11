package com.myprojects.lists;

public class List {

    private ListNode head;
    private int size;

    public List() {
        this.head = null;
        this.size = 0;
    }

    public void addElement(int val, int position) {
        head = Utils.addElement(head, val, position);
        size++;
    }

    public void removeElement(int val) {
        head = Utils.removeElement(head, val);
        size--;
    }

    public void removeElementAt(int n) {
        head = Utils.removeElementAt(head, n);
        size--;
    }

    public int searchElement(int val) {
        return Utils.search(head, val);
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
