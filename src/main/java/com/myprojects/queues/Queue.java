package com.myprojects.queues;

import com.myprojects.lists.List;

import java.util.Objects;

public class Queue<T> {

    private final List<T> list;

    public Queue() {
        this.list = new List<>();
    }

    public Queue(T... elements) {
        this.list = new List<>(elements);
    }

    public void enqueue(T val) {
        list.addLast(val);
    }

    public T dequeue() {
        return list.removeFromTop();
    }

    public T top() {
        return list.first();
    }

    public T[] printQueue() {
        return list.printList();
    }

    public boolean isEmpty() {
        return list.isEmpty();
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
