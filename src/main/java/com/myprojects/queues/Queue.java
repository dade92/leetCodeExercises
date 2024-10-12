package com.myprojects.queues;

import com.myprojects.lists.List;

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

}
