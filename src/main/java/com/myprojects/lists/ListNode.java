package com.myprojects.lists;

import java.util.Objects;

public final class ListNode<T> {
    public T val;
    public ListNode<T> next;

    public ListNode(T val) {
        this.val = val;
        this.next = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ListNode) obj;
        return this.val == that.val &&
            Objects.equals(this.next, that.next);
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
