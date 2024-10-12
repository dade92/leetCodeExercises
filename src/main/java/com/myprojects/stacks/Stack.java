package com.myprojects.stacks;

import com.myprojects.lists.List;

public class Stack {

    private final List list;

    public Stack() {
        this.list = new List();
    }

    public Stack(int... elements) {
        this.list = new List(elements);
    }

    public void push(int val) {
        list.addFirst(val);
    }

    public int pop() {
        return list.removeFromTop();
    }

    public int top() {
        return list.first();
    }

    public int[] printStack() {
        return list.printList();
    }
}