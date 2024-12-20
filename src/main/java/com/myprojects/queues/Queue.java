package com.myprojects.queues;

import com.myprojects.lists.List;

import java.util.Iterator;
import java.util.Objects;

public class Queue<T> implements Iterable<T> {

    private final List<T> list;

    public Queue() {
        list = new List<>();
    }

    public Queue(T... elements) {
        list = new List<>(elements);
    }

    public void enqueue(T val) {
        list.add(val);
    }

    public T dequeue() {
        return list.removeFromTop();
    }

    public void addAll(List<T> elements) {
        list.addAll(elements);
    }

    public T top() {
        return list.first();
    }

    public T[] toArray() {
        return list.toArray();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queue queue = (Queue) o;
        return Objects.equals(list, queue.list);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(list);
    }
}
