package com.myprojects.lists;

import java.util.Objects;

public class ListNode<T> {
    public T val;
    public ListNode<T> next;

    public ListNode(T val) {
        this.val = val;
        this.next = null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        return "ListNode[" +
            "val=" + val + ", " +
            "next=" + next + ']';
    }

}
