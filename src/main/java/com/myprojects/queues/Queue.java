package com.myprojects.queues;

import com.myprojects.lists.List;

import java.util.Objects;

public class Queue {

    private final List list;

    public Queue() {
        this.list = new List();
    }

    public Queue(int... elements) {
        this.list = new List(elements);
    }

    public void enqueue(int val) {
        list.addLast(val);
    }

    public int dequeue() {
        return list.removeFromTop();
    }

    public int top() {
        return list.first();
    }

    public int[] printQueue() {
        return list.printList();
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
