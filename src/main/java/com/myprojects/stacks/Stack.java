package com.myprojects.stacks;

import com.myprojects.lists.List;

import java.util.Iterator;
import java.util.Objects;

public class Stack<T> implements Iterable<T> {

    private final List<T> list;

    public Stack() {
        list = new List<>();
    }

    public Stack(T... elements) {
        list = new List<>(elements);
    }

    public void push(T val) {
        list.addFirst(val);
    }

    public T pop() {
        return list.removeFromTop();
    }

    public void addAll(List<T> elements) {
        for (T e : elements.toArray()) {
            push(e);
        }
    }

    public T top() {
        return list.first();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T[] toArray() {
        return list.toArray();
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
        Stack<T> stack = (Stack) o;
        return Objects.equals(list, stack.list);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(list);
    }
}
