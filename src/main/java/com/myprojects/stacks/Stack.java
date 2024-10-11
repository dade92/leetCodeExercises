package com.myprojects.stacks;

import com.myprojects.lists.List;

public class Stack {

    private final List list;

    public Stack() {
        list = new List();
    }

    public void push(int val) {
        list.push(val);
    }

    public int pop() {
        return list.pop();
    }
}
