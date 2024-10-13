package com.myprojects.stacks;

import com.myprojects.lists.List;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stack stack = (Stack) o;
        return Objects.equals(list, stack.list);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(list);
    }
}
