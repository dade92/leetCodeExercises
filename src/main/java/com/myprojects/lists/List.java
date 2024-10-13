package com.myprojects.lists;

import com.myprojects.lists.exceptions.EmptyListException;
import com.myprojects.lists.exceptions.InvalidPositionException;

import java.lang.reflect.Array;
import java.util.Objects;

public class List<T> {

    private ListNode<T> head;
    private int size;

    public List() {
        this.head = null;
        this.size = 0;
    }

    public List(T... elements) {
        this.size = elements.length;
        for (T element : elements) {
            head = ListUtils.enqueue(head, element);
        }
    }

    public void addElement(T val, int position) {
        if (position < 1 || position > size + 1) {
            throw new InvalidPositionException();
        }
        head = ListUtils.addElement(head, val, position);
        size++;
    }

    public void addAll(List<T> another) {
        for (T e : another.toArray()) {
            this.addLast(e);
        }
    }

    public T[] toArray() {
        return printList();
    }

    public void addLast(T val) {
        head = ListUtils.enqueue(head, val);
        size++;
    }

    public void addFirst(T val) {
        head = ListUtils.push(head, val);
        size++;
    }

    public T removeFromTop() {
        T poppedValue = first();
        head = head.next;
        size--;
        return poppedValue;
    }

    public T first() {
        if (head != null) {
            return head.val;
        }
        throw new EmptyListException();
    }

    public T getAt(int position) {
        if (position < 0 || position > size) {
            throw new InvalidPositionException();
        }
        return ListUtils.getAt(head, position);
    }

    public void removeElement(T val) {
        if (head != null) {
            head = ListUtils.removeElement(head, val);
            size--;
        } else {
            throw new EmptyListException();
        }
    }

    public void removeElementAt(int n) {
        if (n < 1 || n > size) {
            throw new InvalidPositionException();
        }
        head = ListUtils.removeElementAt(head, n);
        size--;
    }

    public int searchElement(int val) {
        return ListUtils.searchElement(head, val);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T[] printList() {
        T[] result = (T[]) new Object[size];
        int index = 0;
        ListNode<T> temp = head;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        List list = (List) o;
        boolean sizeAreEquals = size == list.size;
        if (!sizeAreEquals) {
            return false;
        }
        int index = 1;
        while (index <= size) {
            if (this.getAt(index) != list.getAt(index)) return false;
            index++;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }
}
