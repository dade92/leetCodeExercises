package com.myprojects.lists;

import com.myprojects.lists.exceptions.EmptyListException;
import com.myprojects.lists.exceptions.InvalidPositionException;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class List<T> implements Iterable<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public List(T... elements) {
        for (T element : elements) {
            this.add(element);
        }
    }

    public void add(T val) {
        Pair<ListNode<T>, ListNode<T>> outcome = ListUtils.enqueue(head, tail, val);
        movePointers(outcome);
        size++;
    }

    public void add(T val, int position) {
        if (position < 0 || position > size) {
            throw new InvalidPositionException();
        }
        Pair<ListNode<T>, ListNode<T>> outcome = ListUtils.addElement(head, tail, val, position);
        movePointers(outcome);
        size++;
    }

    public void addAll(List<T> another) {
        for (T e : another.toArray()) {
            this.add(e);
        }
    }

    public void addFirst(T val) {
        Pair<ListNode<T>, ListNode<T>> outcome = ListUtils.push(head, tail, val);
        movePointers(outcome);
        size++;
    }

    public T first() {
        if (head != null) {
            return head.val;
        }
        throw new EmptyListException();
    }

    public T last() {
        if (tail != null) {
            return tail.val;
        }
        throw new EmptyListException();
    }

    public T getAt(int position) {
        if (position < 0 || position >= size) {
            throw new InvalidPositionException();
        }
        if (position == 0) {
            return head.val;
        } else if (position == size - 1) {
            return tail.val;
        } else {
            return ListUtils.getAt(head, position);
        }
    }

    public void removeElement(T val) {
        if (head != null) {
            Pair<ListNode<T>, ListNode<T>> outcome = ListUtils.removeElement(head, tail, val);
            movePointers(outcome);
            size--;
        } else {
            throw new EmptyListException();
        }
    }

    public void removeElementAt(int n) {
        if (n < 0 || n >= size) {
            throw new InvalidPositionException();
        }

        Pair<ListNode<T>, ListNode<T>> outcome = ListUtils.removeElementAt(head, tail, n);
        movePointers(outcome);
        size--;
    }

    public T removeFromTop() {
        T poppedValue = first();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
        return poppedValue;
    }

    public int indexOf(T val) {
        return ListUtils.indexOf(head, val);
    }

    public ListNode<T> search(T val) {
        return ListUtils.search(head, val);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T[] toArray() {
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

    public boolean contains(T val) {
        return this.indexOf(val) != -1;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private ListNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T next = current.val;
                current = current.next;
                return next;
            }
        };
    }

    public List<T> subList(int startingIndex, int endingIndex) {
        if(startingIndex > endingIndex ||
            startingIndex < 0 ||
            startingIndex > size - 1 ||
            endingIndex > size - 1
        ) {
            throw new InvalidPositionException();
        }

        List<T> subList = new List<>();
        int index = 0;
        ListNode<T> temp = head;

        while(index < startingIndex) {
            temp = temp.next;
            index++;
        }

        while(index <= endingIndex) {
            subList.add(temp.val);
            temp = temp.next;
            index++;
        }

        return subList;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[ ", " ]");

        for (T element : toArray()) {
            sj.add(element.toString());
        }

        return sj.toString();
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
        int index = 0;
        while (index < size) {
            if (!this.getAt(index).equals(list.getAt(index))) return false;
            index++;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }

    private void movePointers(Pair<ListNode<T>, ListNode<T>> outcome) {
        head = outcome.getLeft();
        tail = outcome.getRight();
    }
}
