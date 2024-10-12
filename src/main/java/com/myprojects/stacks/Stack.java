package com.myprojects.stacks;

import com.myprojects.lists.List;

public class Stack {

    private final List list;

    public Stack() {
        list = new List();
    }

    public Stack(int... elements) {
        this.list = new List(elements);
    }

    public void push(int val) {
        list.push(val);
    }

    public int pop() {
        return list.pop();
    }

    public int top() {
        return list.first();
    }

    public int[] printStack() {
        return list.printList();
    }
}
