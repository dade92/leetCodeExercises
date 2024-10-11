package com.myprojects.queues;

import com.myprojects.lists.List;

public class Queue {

    private List list;

    public void enqueue(int val) {
        list.addLast(val);
    }

    public int dequeue() {
        return list.pop();
    }

}
