package com.myprojects.queues;

import com.myprojects.lists.List;

public class Queue {

    private List list;

    public Queue(int... elements) {
        this.list = new List(elements);
    }

    public void enqueue(int val) {
        list.addLast(val);
    }

    public int dequeue() {
        return list.pop();
    }

    public int[] printQueue() {
        return list.printList();
    }

}
